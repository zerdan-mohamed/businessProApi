package com.soft.business.mapper;

import com.soft.business.dto.SupplierDto;
import com.soft.business.dto.SupplierOrderDto;
import com.soft.business.model.Supplier;
import com.soft.business.model.SupplierOrder;
import com.soft.business.model.SupplierOrderParams;
import com.soft.business.repository.SupplierOrderParamsRepository;
import com.soft.business.repository.SupplierRepository;
import static com.soft.business.util.StringUtils.generateOrderCode;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;



@Service
public class SupplierOrderMapper {

    private SupplierRepository supplierRepository;
    private SupplierOrderParamsRepository supplierOrderParamsRepository;

    public SupplierOrderMapper(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public SupplierOrderDto makeDtoFromSupplierOrder(int orgId, SupplierOrder supplierOrder) {
        Date  creationDate = supplierOrder.getCreationDate();
        SupplierOrderDto supplierOrderDto = new SupplierOrderDto();

        supplierOrderDto.setUuid(supplierOrder.getUuid());
        supplierOrderDto.setComment(supplierOrder.getComment());
        supplierOrderDto.setSupplierOrderStatus(supplierOrder.getSupplierOrderStatus());
        supplierOrderDto.setCreationDate(creationDate.toString());

        Optional<SupplierOrderParams> supplierOrderParams = supplierOrderParamsRepository.findSupplierOrderParamsByOrgId(orgId);
        String prefix = supplierOrderParams.get().getPrefix();

        String generateOrderCode = generateOrderCode(prefix, supplierOrder);

        supplierOrderDto.setSupplierOrderNumber(generateOrderCode);

        if (supplierOrder.getSupplier() != null) {
            SupplierDto supplierDto = new SupplierDto();
            supplierDto.setName(supplierOrder.getSupplier().getName());
            supplierDto.setUuid(supplierOrder.getSupplier().getUuid());
            supplierDto.setCity(supplierOrder.getSupplier().getCity());
            supplierDto.setPhoneNumber(supplierOrder.getSupplier().getPhoneNumber());
            supplierDto.setMobileNumber(supplierOrder.getSupplier().getMobileNumber());
            supplierDto.setFaxNumber(supplierOrder.getSupplier().getFaxNumber());
            supplierDto.setAddress(supplierOrder.getSupplier().getAddress());
            supplierDto.setEmail(supplierOrder.getSupplier().getEmail());
            supplierDto.setWebsite(supplierOrder.getSupplier().getWebsite());
            supplierDto.setContact(supplierOrder.getSupplier().getContact());
            supplierDto.setCappedBalance(supplierOrder.getSupplier().getCappedBalance());
            supplierDto.setInitialBalance(supplierOrder.getSupplier().getInitialBalance());
            supplierDto.setPatent(supplierOrder.getSupplier().getPatent());
            supplierDto.setIce(supplierOrder.getSupplier().getIce());
            supplierDto.setCreationDate(supplierOrder.getSupplier().getCreationDate().toString());

            supplierOrderDto.setSupplier(supplierDto);
        }

        return supplierOrderDto;
    }

    public SupplierOrder makeSupplierOrderFromDto(int orgId, SupplierOrderDto supplierOrderDto) {
        SupplierOrder supplierOrder = new SupplierOrder();

        supplierOrder.setOrgId(orgId);
        supplierOrder.setUuid(UUID.randomUUID().toString());
        supplierOrder.setComment(supplierOrderDto.getComment());
        supplierOrder.setSupplierOrderStatus(supplierOrderDto.getSupplierOrderStatus());
        supplierOrder.setCreationDate(new Date());

        SupplierOrderParams supplierOrderParams =
                supplierOrderParamsRepository.findSupplierOrderParamsByOrgId(orgId).get();
        supplierOrder.setSupplierOrderNumber(supplierOrderParams.getCounter()+1);

        supplierOrderParams.setCounter(supplierOrder.getSupplierOrderNumber());
        supplierOrderParamsRepository.save(supplierOrderParams);

        if (supplierOrderDto.getSupplier() != null) {
           Optional<Supplier> supplier = supplierRepository
                   .findByUuidAndOrgId(supplierOrderDto.getSupplier().getUuid(), orgId);

            if (supplier.isPresent()) supplierOrder.setSupplier(supplier.get());
        }

        return supplierOrder;
    }

    public SupplierOrder updateSupplierOrder(
            int orgId, SupplierOrderDto supplierOrderDto, SupplierOrder supplierOrderDb) {
        SupplierOrder supplierOrder = new SupplierOrder();

        supplierOrder.setIdSupplierOrder(supplierOrderDb.getIdSupplierOrder());
        supplierOrder.setUuid(supplierOrderDb.getUuid());
        supplierOrder.setCreationDate(supplierOrderDb.getCreationDate());
        supplierOrder.setSupplierOrderNumber(supplierOrderDb.getSupplierOrderNumber());

        if (supplierOrderDto.getSupplierOrderStatus() != null)
            supplierOrder.setSupplierOrderStatus(supplierOrderDto.getSupplierOrderStatus());
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
