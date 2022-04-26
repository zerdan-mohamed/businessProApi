package com.soft.business.mapper;

import com.soft.business.dto.SupplierDto;
import com.soft.business.dto.SupplierOrderDto;
import com.soft.business.model.Supplier;
import com.soft.business.model.SupplierOrder;
import com.soft.business.model.SupplierOrderParams;
import com.soft.business.repository.SupplierOrderParamsRepository;
import com.soft.business.repository.SupplierOrderRepository;
import com.soft.business.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
@Service
public class SupplierOrderMapper {

    private SupplierRepository supplierRepository;
    private SupplierOrderRepository supplierOrderRepository;
    private SupplierOrderParamsRepository supplierOrderParamsRepository;

    public SupplierOrderMapper(SupplierRepository supplierRepository, SupplierOrderRepository supplierOrderRepository) {
        this.supplierRepository = supplierRepository;
        this.supplierOrderRepository = supplierOrderRepository;
    }

    public String generateOrderCode(Date creationDate, Integer supplierOrderNumber) {

        // TODO: we need to change this var to get prefix of specific org
        String prefix = supplierOrderParamsRepository.findOrderParams().get().getPrefix();
        String orderYear = String.valueOf(creationDate.getYear());

        String generatedOrderCode = prefix + orderYear + "/" + supplierOrderNumber;

        return generatedOrderCode;
    }

    // post
    public SupplierOrder makeSupplierOrderFromDto(SupplierOrderDto supplierOrderDto) {
        SupplierOrder supplierOrder = new SupplierOrder();

        supplierOrder.setUuid(UUID.randomUUID().toString());
        supplierOrder.setComment(supplierOrderDto.getComment());
        supplierOrder.setSupplierOrderStatus(supplierOrderDto.getSupplierOrderStatus());
        supplierOrder.setCreationDate(new Date());

        SupplierOrderParams supplierOrderParams = supplierOrderParamsRepository.findOrderParams().get();
        supplierOrder.setSupplierOrderNumber(supplierOrderParams.getCounter()+1);

        supplierOrderParams.setCounter(supplierOrder.getSupplierOrderNumber());
        supplierOrderParamsRepository.save(supplierOrderParams);

        if (supplierOrderDto.getSupplier() != null) {
            Optional<Supplier> supplier = supplierRepository.findByUuid(supplierOrderDto.getSupplier().getUuid());

            if (supplier.isPresent()) supplierOrder.setSupplier(supplier.get());
        }

        return supplierOrder;
    }


    // get
    public SupplierOrderDto makeDtoFromSupplierOrder(SupplierOrder supplierOrder) {
        SupplierOrderDto supplierOrderDto = new SupplierOrderDto();

        Date  creationDate = supplierOrder.getCreationDate();
        Integer supplierOrderNumber = supplierOrder.getSupplierOrderNumber();

        supplierOrderDto.setUuid(supplierOrder.getUuid());
        supplierOrderDto.setComment(supplierOrder.getComment());
        supplierOrderDto.setSupplierOrderStatus(supplierOrder.getSupplierOrderStatus());
        supplierOrderDto.setCreationDate(creationDate.toString());

        // TODO: refactor generateOrderCode function to match our request
        supplierOrderDto.setSupplierOrderNumber(generateOrderCode(creationDate, supplierOrderNumber));

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

    // post
    public SupplierOrder updateSupplierOrder(SupplierOrderDto supplierOrderDto, SupplierOrder supplierOrderDb) {
        SupplierOrder supplierOrder = new SupplierOrder();

        supplierOrder.setIdSupplierOrder(supplierOrderDb.getIdSupplierOrder());
        supplierOrder.setUuid(supplierOrderDb.getUuid());
        supplierOrder.setCreationDate(supplierOrderDb.getCreationDate());
        supplierOrder.setSupplierOrderNumber(supplierOrderDb.getSupplierOrderNumber());


        // setSupplier

        if (supplierOrderDto.getSupplierOrderStatus() != null)
            supplierOrder.setSupplierOrderStatus(supplierOrderDto.getSupplierOrderStatus());
        else
            supplierOrder.setSupplierOrderStatus(supplierOrderDb.getSupplierOrderStatus());

        if (supplierOrderDto.getComment() != null)
            supplierOrder.setComment(supplierOrderDto.getComment());
        else
            supplierOrder.setComment(supplierOrderDb.getComment());


        if (supplierOrderDto.getSupplier() != null) {
            Optional<Supplier> supplier = supplierRepository.findByUuid(supplierOrderDto.getSupplier().getUuid());

            if (supplier.isPresent()) supplierOrder.setSupplier(supplier.get());
        }

        return supplierOrder;
    }
}
