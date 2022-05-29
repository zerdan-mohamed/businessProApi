package com.soft.business.service;

import com.soft.business.dto.SupplierOrderItemDto;
import com.soft.business.mapper.SupplierOrderItemMapper;
import com.soft.business.model.SupplierOrder;
import com.soft.business.model.SupplierOrderItem;
import com.soft.business.model.SupplierOrderStatus;
import com.soft.business.repository.SupplierOrderItemRepository;
import com.soft.business.repository.SupplierOrderRepository;
import com.soft.business.service.organization.OrganizationServiceImpl;
import com.soft.business.util.validator.SupplierOrderItemValidator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SupplierOrderItemServiceImpl implements SupplierOrderItemService{

    private SupplierOrderItemRepository supplierOrderItemRepository;

    private SupplierOrderRepository supplierOrderRepository;

    private SupplierOrderItemValidator supplierOrderItemValidator;

    private SupplierOrderItemMapper supplierOrderItemMapper;

    public SupplierOrderItemServiceImpl(
            SupplierOrderItemRepository supplierOrderItemRepository,
            SupplierOrderItemValidator supplierOrderItemValidator,
            SupplierOrderItemMapper supplierOrderItemMapper) {
        this.supplierOrderItemRepository = supplierOrderItemRepository;
        this.supplierOrderItemValidator = supplierOrderItemValidator;
        this.supplierOrderItemMapper = supplierOrderItemMapper;
    }

    @Override
    public List<SupplierOrderItemDto> findSupplierOrderItems(Authentication authentication, String supplierOrderUuid) {
        int orgId = OrganizationServiceImpl.getOrgIdFromPrincipal(authentication);
        List<SupplierOrderItemDto> supplierOrderItemsDto = new ArrayList<>();

        Optional<SupplierOrder> supplierOrder = supplierOrderRepository.findByUuidAndOrgId(supplierOrderUuid, orgId);
        List<SupplierOrderItem> supplierOrderItems = supplierOrderItemRepository.findBySupplierOrderAndOrgId(supplierOrder.get(), orgId);

        supplierOrderItems.forEach(
                supplierOrderItem -> supplierOrderItemsDto
                        .add(supplierOrderItemMapper.makeDtoFromSupplierOrderItem(orgId, supplierOrderItem))
        );

        return supplierOrderItemsDto;
    }

    @Override
    public SupplierOrderItemDto findSupplierOrderItemByUuid(Authentication authentication, String uuid) {
        int orgId = OrganizationServiceImpl.getOrgIdFromPrincipal(authentication);

        Optional<SupplierOrderItem> supplierOrderItem = supplierOrderItemRepository.findByUuidAndOrgId(uuid, orgId);

        return supplierOrderItemMapper.makeDtoFromSupplierOrderItem(orgId, supplierOrderItem.get());
    }

    @Override
    public void deleteSupplierOrderItemByUuid(Authentication authentication, String uuid) {
        int orgId = OrganizationServiceImpl.getOrgIdFromPrincipal(authentication);
        SupplierOrderItemDto supplierOrderItem = findSupplierOrderItemByUuid(authentication, uuid);

        if (!supplierOrderItem.getSupplierOrder()
                .getSupplierOrderStatus().equals(SupplierOrderStatus.PAID)) {

            long isDeleted = supplierOrderItemRepository.deleteByUuidAndOrgId(uuid, orgId);
            if(isDeleted == 0) throw new NoSuchElementException();
        }
    }

    @Override
    public SupplierOrderItemDto createSupplierOrderItem(
            Authentication authentication, SupplierOrderItemDto supplierOrderItemDto) {
        int orgId = OrganizationServiceImpl.getOrgIdFromPrincipal(authentication);
        supplierOrderItemValidator.createSupplierOrderItemValidator(supplierOrderItemDto);

        supplierOrderItemRepository.save(
                supplierOrderItemMapper.makeSupplierOrderItemFromDto(orgId, supplierOrderItemDto)
        );

        return supplierOrderItemDto;
    }

    @Override
    public SupplierOrderItemDto updateSupplierOrderItem(
            Authentication authentication, String uuid, SupplierOrderItemDto supplierOrderItemDto) {
        int orgId = OrganizationServiceImpl.getOrgIdFromPrincipal(authentication);

        Optional<SupplierOrderItem> supplierOrderItemDb = this.supplierOrderItemRepository.findByUuidAndOrgId(uuid, orgId);

        SupplierOrderItem supplierOrderItem =
                supplierOrderItemMapper.updateSupplierOrderItem(
                        orgId, supplierOrderItemDto, supplierOrderItemDb.get()
                );
        supplierOrderItemRepository.save(supplierOrderItem);

        return supplierOrderItemDto;
    }
}
