package com.soft.business.mapper;

import com.soft.business.dto.SupplierDto;
import com.soft.business.dto.SupplierOrderDto;
import com.soft.business.model.Supplier;
import com.soft.business.model.SupplierOrder;
import com.soft.business.repository.SupplierRepository;
import com.soft.business.service.organization.OrganizationService;
import com.soft.business.util.FunctionalUtils;
import com.soft.business.util.SupplierOrderConstants;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupplierOrderMapper {

    private final SupplierRepository supplierRepository;
    private final OrganizationService organizationService;

    public SupplierOrderMapper(SupplierRepository supplierRepository,
                               OrganizationService organizationService) {
        this.supplierRepository = supplierRepository;
        this.organizationService = organizationService;
    }

    public SupplierOrderDto makeDtoFromSupplierOrder(SupplierOrder supplierOrder) {
        SupplierOrderDto supplierOrderDto = new SupplierOrderDto();

        supplierOrderDto.setUuid(supplierOrder.getUuid());
        supplierOrderDto.setComment(supplierOrder.getComment());
        supplierOrderDto.setSupplierOrderStatus(supplierOrder.getSupplierOrderStatus());
        LocalDateTime creationDate = supplierOrder.getCreationDate();
        supplierOrderDto.setCreationDate(creationDate.toString());
        supplierOrderDto.setSupplierOrderNumber(supplierOrder.getSupplierOrderNumber());

        if (supplierOrder.getSupplier() != null) {
            SupplierDto supplierDto = new SupplierDto();
            supplierDto.setUuid(supplierOrder.getSupplier().getUuid());
            supplierOrderDto.setSupplier(supplierDto);
        }

        return supplierOrderDto;
    }


    public SupplierOrder makeSupplierOrderFromDto(int orgId, SupplierOrderDto supplierOrderDto) {
        SupplierOrder supplierOrder = new SupplierOrder();

        supplierOrder.setOrgId(orgId);
        supplierOrder.setUuid(UUID.randomUUID().toString());
        supplierOrder.setComment(supplierOrderDto.getComment());
        supplierOrder.setSupplierOrderStatus(SupplierOrderConstants.CREATED);
        supplierOrder.setCreationDate(LocalDateTime.now());

        if (supplierOrderDto.getSupplier() != null) {
           Optional<Supplier> supplier = supplierRepository
                   .findByUuidAndOrgId(supplierOrderDto.getSupplier().getUuid(), orgId);

            if (supplier.isPresent()) supplierOrder.setSupplier(supplier.get());
        }

        String prefix = this.organizationService.makeSupplierOrderNumber(orgId, supplierOrder);
        supplierOrder.setSupplierOrderNumber(prefix);

        return supplierOrder;
    }

    public SupplierOrder updateSupplierOrder(
            int orgId, SupplierOrderDto supplierOrderDto, SupplierOrder supplierOrderDb) {
        SupplierOrder supplierOrder = new SupplierOrder();

        supplierOrder.setIdSupplierOrder(supplierOrderDb.getIdSupplierOrder());
        supplierOrder.setUuid(supplierOrderDb.getUuid());
        supplierOrder.setCreationDate(supplierOrderDb.getCreationDate());


        if (supplierOrderDto.getSupplierOrderNumber() != null)
            supplierOrder.setSupplierOrderNumber(supplierOrderDto.getSupplierOrderNumber());
        else supplierOrder.setSupplierOrderNumber(supplierOrderDb.getSupplierOrderNumber());

        Integer orderStatus = supplierOrderDto.getSupplierOrderStatus();
        boolean StatusExists = FunctionalUtils.checkItemStatusExists(orderStatus);
        boolean validOrderStatus = FunctionalUtils.checkValidOrderStatus(orderStatus);

        if (orderStatus != null && StatusExists && validOrderStatus)
            supplierOrder.setSupplierOrderStatus(orderStatus);
        else
            supplierOrder.setSupplierOrderStatus(supplierOrderDb.getSupplierOrderStatus());

        if (supplierOrderDto.getComment() != null)
            supplierOrder.setComment(supplierOrderDto.getComment());
        else
            supplierOrder.setComment(supplierOrderDb.getComment());

        if (supplierOrderDto.getSupplier() != null) {
            Optional<Supplier> supplier = supplierRepository.findByUuidAndOrgId(
                    supplierOrderDto.getSupplier().getUuid(), orgId);

            if (supplier.isPresent()) supplierOrder.setSupplier(supplier.get());
        } else if (supplierOrderDb.getSupplier() != null) {
            supplierOrder.setSupplier(supplierOrder.getSupplier());
        }

        return supplierOrder;
    }
}
