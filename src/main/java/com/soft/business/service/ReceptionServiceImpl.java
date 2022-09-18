package com.soft.business.service;

import com.soft.business.dto.ItemReceptionDto;
import com.soft.business.dto.ReceptionDto;
import com.soft.business.dto.SupplierOrderItemDto;
import com.soft.business.exception.FunctionalException;
import com.soft.business.mapper.ItemReceptionMapper;
import com.soft.business.mapper.ReceptionMapper;
import com.soft.business.mapper.SupplierOrderItemMapper;
import com.soft.business.model.ItemReception;
import com.soft.business.model.Reception;
import com.soft.business.model.SupplierOrder;
import com.soft.business.model.SupplierOrderItem;
import com.soft.business.repository.ItemReceptionRepository;
import com.soft.business.repository.ReceptionRepository;
import com.soft.business.repository.SupplierOrderItemRepository;
import com.soft.business.repository.SupplierOrderRepository;
import com.soft.business.service.organization.OrganizationService;
import com.soft.business.util.ApiErrorCodesConstantes;
import com.soft.business.util.FunctionalUtils;
import com.soft.business.util.validator.ReceptionValidator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ReceptionServiceImpl implements ReceptionService {

    private static Logger _logger = Logger.getLogger(ReceptionServiceImpl.class.getName());

    private final ReceptionRepository receptionRepository;
    private final ItemReceptionRepository itemReceptionRepository;
    private final SupplierOrderItemRepository supplierOrderItemRepository;
    private final SupplierOrderRepository supplierOrderRepository;
    private final ReceptionValidator receptionValidator;
    private final ReceptionMapper receptionMapper;
    private final ItemReceptionMapper itemReceptionMapper;
    private final SupplierOrderItemMapper supplierOrderItemMapper;

    public ReceptionServiceImpl(
            ReceptionRepository receptionRepository,
            ItemReceptionRepository itemReceptionRepository,
            SupplierOrderItemRepository supplierOrderItemRepository,
            SupplierOrderRepository supplierOrderRepository, ReceptionValidator receptionValidator,
            ReceptionMapper receptionMapper,
            ItemReceptionMapper itemReceptionMapper,
            SupplierOrderItemMapper supplierOrderItemMapper) {
        this.receptionRepository = receptionRepository;
        this.itemReceptionRepository = itemReceptionRepository;
        this.supplierOrderItemRepository = supplierOrderItemRepository;
        this.supplierOrderRepository = supplierOrderRepository;
        this.receptionValidator = receptionValidator;
        this.receptionMapper = receptionMapper;
        this.itemReceptionMapper = itemReceptionMapper;
        this.supplierOrderItemMapper = supplierOrderItemMapper;
    }

    @Override
    @Transactional
    public Set<ReceptionDto> findReceptions(Authentication authentication) {

        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);
        Set<Reception> receptions = receptionRepository.findByOrgId(orgId);
        Set<ReceptionDto> receptionsDto = new HashSet<>();

        for (Reception reception : receptions) {
            Set<ItemReception> itemReceptions = itemReceptionRepository.findAllByReceptionIdAndOrgId(
                    reception.getReceptionId(), orgId
            );

            Set<ItemReceptionDto> itemsReceptionDto =
                itemReceptions
                    .stream()
                    .map(itemReceptionMapper::makeDtoFromItemReception).collect(Collectors.toSet());

            ReceptionDto receptionDto = receptionMapper.makeDtoFromReception(reception, itemsReceptionDto);
            receptionsDto.add(receptionDto);
        }

        return receptionsDto;
    }

    @Override
    @Transactional
    public ReceptionDto findReceptionByUuid(Authentication authentication, String uuid) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);

        Reception reception = receptionRepository.findByUuidAndOrgId(uuid, orgId);
        Set<ItemReception> itemReceptions = itemReceptionRepository.findAllByReceptionIdAndOrgId(reception.getReceptionId(), orgId);

        Set<ItemReceptionDto> itemsReceptionDto =
                itemReceptions
                        .stream()
                        .map(itemReceptionMapper::makeDtoFromItemReception).collect(Collectors.toSet());

        return receptionMapper.makeDtoFromReception(reception, itemsReceptionDto);
    }

    // ## This service has not been completed
    @Override
    @Transactional
    public void deleteReceptionByUuid(Authentication authentication, String uuid) {
        ReceptionDto receptionDto = findReceptionByUuid(authentication, uuid);

        if (FunctionalUtils.checkReceptionDeleteRules("define params !!")) {
            // reset remainingQuantity of all orderItems
            // change order & orderItems status
            // delete itemReceptions
            // delete reception
        }
    }

    @Override
    @Transactional
    public ReceptionDto createReception(
            Authentication authentication, ReceptionDto receptionDto
    ) throws ParseException {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);

        Set<ItemReceptionDto> itemsReceptionDto = receptionDto.getReceptionItems();
        Reception reception = receptionMapper.makeReceptionFromDto(orgId, receptionDto);
        receptionValidator.createReceptionvalidator(reception, itemsReceptionDto);

        Reception savedReception = receptionRepository.save(reception);
        Long receptionId = savedReception.getReceptionId();

        Set<ItemReceptionDto> savedItems = new HashSet<>();
        if (savedReception.getReceptionId() != null)
            savedItems = createItemsReception(itemsReceptionDto, orgId, receptionId);

        return receptionMapper.makeDtoFromReception(savedReception, savedItems);
    }

    // TODO : create reception util class
    // TODO : DB trick : use view
    Set<ItemReceptionDto> createItemsReception(
            Set<ItemReceptionDto> itemReceptionsDto, int orgId, Long receptionId
    ) {
        List<Long> orderIds = new ArrayList<>();
        Set<ItemReception> itemsReception = new HashSet<>();
        
        for (ItemReceptionDto item : itemReceptionsDto) {
            item.setReceptionId(receptionId);

            // FIXME : Db query !!!
            SupplierOrderItem relatedOrderItem  = supplierOrderItemRepository
                    .findByIdSupplierOrderItemAndOrgId(
                            item.getSupplierOrderItemId(),
                            orgId
                    );

            if(item.getQuantity() > relatedOrderItem.getRemainingQuantity())
                throw new FunctionalException(
                    ApiErrorCodesConstantes.RECEPTION_ITEM_WRONG_QUANTITY_EXCEPTION_CODE,
                    ApiErrorCodesConstantes.RECEPTION_ITEM_WRONG_QUANTITY_EXCEPTION_MESSAGE
                );

            ItemReception itemReception = itemReceptionMapper.makeItemReceptionFromDto(orgId, item);

            itemsReception.add(itemReception);
            updateSupplierOrderItem(itemReception);

            if (!orderIds.contains(itemReception.getSupplierOrderId()))
                orderIds.add(itemReception.getSupplierOrderId());
        }

        // FIXME : Db query !!!
        itemReceptionRepository.saveAll(itemsReception);

        updateSupplierOrderStatus(orderIds, orgId);

        return itemsReception
                .stream()
                .map(itemReceptionMapper::makeDtoFromItemReception).collect(Collectors.toSet());
    }

    void updateSupplierOrderItem(ItemReception item) {
        Long orderItemId = item.getSupplierOrderItemId();
        int orgId = item.getOrgId();

        // !! Db query
        SupplierOrderItem supplierOrderItemDb =
                supplierOrderItemRepository.findByIdSupplierOrderItemAndOrgId(orderItemId, orgId);

        SupplierOrderItemDto supplierOrderItemDto = changeRemainingQuantity(supplierOrderItemDb, item);

        SupplierOrderItem supplierOrderItem =
                supplierOrderItemMapper.updateSupplierOrderItem(
                        item.getOrgId(), supplierOrderItemDto, supplierOrderItemDb
                );

        // !! Db query
        supplierOrderItemRepository.save(supplierOrderItem);
    }

    SupplierOrderItemDto changeRemainingQuantity(SupplierOrderItem supplierOrderItemDb, ItemReception item) {
        SupplierOrderItemDto supplierOrderItemDto = new SupplierOrderItemDto();

        Double quantity = supplierOrderItemDb.getQuantity();
        Double remainingQuantity = supplierOrderItemDb.getRemainingQuantity() - item.getQuantity();
        supplierOrderItemDto.setRemainingQuantity(remainingQuantity);
        supplierOrderItemDto = changeOrderItemStatus(supplierOrderItemDto, remainingQuantity, quantity);

        return supplierOrderItemDto;
    }

    SupplierOrderItemDto changeOrderItemStatus(
            SupplierOrderItemDto supplierOrderItemDto, Double remainingQuantity, Double quantity
    ) {
        // FIXME : check status table before set orderItem status
        if (remainingQuantity == 0) {
            supplierOrderItemDto.setSupplierOrderItemStatus(3);
        } else if (!remainingQuantity.equals(quantity)) {
            supplierOrderItemDto.setSupplierOrderItemStatus(2);
        } else {
            supplierOrderItemDto.setSupplierOrderItemStatus(1);
        }

        return supplierOrderItemDto;
    }

    void updateSupplierOrderStatus(List<Long> orderIds, int orgId) {

        // TODO : get supplierOrderItem also
        // FIXME : Db query !!!
        List<SupplierOrder> supplierOrders = supplierOrderRepository.findByOrgIdAndIdSupplierOrder(orgId, orderIds);

        for (int i = 0; i < orderIds.size(); i++) {
            // FIXME : Db query !!!
            Set<SupplierOrderItem> supplierOrderItems =
                    supplierOrderItemRepository.findBySupplierOrderAndOrgId(
                            supplierOrders.get(i),
                            orgId
                    );

            Map<Integer, Integer> itemsStatus = new HashMap<>();

            for (SupplierOrderItem orderItem : supplierOrderItems) {
                Integer itemStatus = orderItem.getSupplierOrderItemStatus();

                Integer value = itemsStatus.get(itemStatus);

                if(value != null && itemsStatus.containsKey(itemStatus)) {
                    itemsStatus.put(itemStatus, itemsStatus.get(itemStatus)+1);
                } else {
                    itemsStatus.putIfAbsent(itemStatus, 1);
                }
            }

            Integer supplierOrder = supplierOrders.get(i).getSupplierOrderStatus();

            // if (itemsStatus.get(supplierOrder) != itemsStatus.size()) {
                for (Map.Entry<Integer, Integer> entry : itemsStatus.entrySet()) {
                    System.out.println(entry.getKey() + ":" + entry.getValue());

                    if (itemsStatus.get(entry.getKey()) == itemsStatus.size()) {
                        // FIXME : Db query !!!
                        supplierOrderRepository.updateSupplierOrderStatus(
                                supplierOrders.get(i).getIdSupplierOrder(),
                                entry.getKey(),
                                orgId
                        );
                    }

                }
        }
    }

    // ## This service has not been completed
    @Override
    @Transactional
    public ReceptionDto updateReceptionByUuid(
            Authentication authentication, String uuid, ReceptionDto receptionDto
    ) {
        // TODO : sprint3 | check invoice status

        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);

        Reception receptionDb = receptionRepository.findByUuidAndOrgId(uuid, orgId);
        Reception reception = receptionMapper.updateReception(orgId, receptionDto, receptionDb);
        receptionRepository.save(reception);

        // FIXME : to delete !!
        Set<ItemReception> itemReceptions = itemReceptionRepository.findAllByReceptionIdAndOrgId(reception.getReceptionId(), orgId);

        Set<ItemReceptionDto> itemsReceptionDto =
                itemReceptions
                        .stream()
                        .map(itemReceptionMapper::makeDtoFromItemReception).collect(Collectors.toSet());

        // TODO : change order and order item status.

        return receptionMapper.makeDtoFromReception(reception, itemsReceptionDto);
    }
}
