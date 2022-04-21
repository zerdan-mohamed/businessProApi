package com.soft.business.mapper;

import com.soft.business.dto.SupplierDto;
import com.soft.business.dto.SupplierOrderDto;
import com.soft.business.model.Supplier;
import com.soft.business.model.SupplierOrder;
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

    public String generateOrderCode(String prefix) {
        // TODO: reset code iteration when the next year started
        // TODO: use id is not a solution, think to search another solution
        // String prefix = "CF-"; String currentDate = "202203"; String suffix = "00001";
        String generatedCode = "CF-20220400001";

        String initialCodeSuffix = "0000001";
        SupplierOrder lastSupplierOrder = supplierOrderRepository.findTopByOrderByCreationDateDesc();

        if (lastSupplierOrder != null &&
                lastSupplierOrder.getCreationDate().getYear() == LocalDate.now().getYear()) {
            /*
                ## generate orderCode sequential ##
                 - split lastSupplierOrder.getOrderCode();
                 - suffix[1] = suffix[1].next()
                 - suffix[0].concat(suffix[1])
            */
        } else {
            // setOrResetOrderCode with initialCodeSuffix
        }

        return generatedCode;
    }

    public SupplierOrder makeSupplierOrderFromDto(SupplierOrderDto supplierOrderDto) {
        SupplierOrder supplierOrder = new SupplierOrder();

        supplierOrder.setUuid(UUID.randomUUID().toString());
        supplierOrder.setComment(supplierOrderDto.getComment());
        supplierOrder.setSupplierOrderStatus(supplierOrderDto.getSupplierOrderStatus());
        supplierOrder.setCreationDate(new Date());

        supplierOrder.setSupplierOrderNumber(
                supplierOrderParamsRepository.findOrderParams().get().getCounter()+1
        );

        if (supplierOrderDto.getSupplier() != null) {
            Optional<Supplier> supplier = supplierRepository.findByUuid(supplierOrderDto.getSupplier().getUuid());

            if (supplier.isPresent()) supplierOrder.setSupplier(supplier.get());
        }

        return supplierOrder;
    }

    public SupplierOrderDto makeDtoFromSupplierOrder(SupplierOrder supplierOrder) {
        SupplierOrderDto supplierOrderDto = new SupplierOrderDto();

        supplierOrderDto.setUuid(supplierOrder.getUuid());
        supplierOrderDto.setComment(supplierOrder.getComment());
        supplierOrderDto.setSupplierOrderStatus(supplierOrder.getSupplierOrderStatus());
        supplierOrderDto.setCreationDate(supplierOrder.getCreationDate().toString());

        // TODO: refactor generateOrderCode function to match our request
        supplierOrderDto.setSupplierOrderNumber(generateOrderCode("CF"));

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

    public SupplierOrder updateSupplierOrder(SupplierOrderDto supplierOrderDto, SupplierOrder supplierOrderDb) {
        SupplierOrder supplierOrder = new SupplierOrder();

        supplierOrder.setSupplierOrderNumber(supplierOrderDb.getSupplierOrderNumber());

        supplierOrder.setIdSupplierOrder(supplierOrderDb.getIdSupplierOrder());
        supplierOrder.setUuid(supplierOrderDb.getUuid());
        supplierOrder.setCreationDate(supplierOrderDb.getCreationDate());

        if (supplierOrderDto.getSupplierOrderStatus() != null)
            supplierOrder.setSupplierOrderStatus(supplierOrderDto.getSupplierOrderStatus());
        else
            supplierOrder.setSupplierOrderStatus(supplierOrderDb.getSupplierOrderStatus());

        if (supplierOrderDto.getComment() != null)
            supplierOrder.setComment(supplierOrderDto.getComment());
        else
            supplierOrder.setComment(supplierOrderDb.getComment());

        return supplierOrder;
    }
}
