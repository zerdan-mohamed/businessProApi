package com.soft.business.mapper;

import com.soft.business.dto.SupplierDto;
import com.soft.business.model.Supplier;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class SupplierMapper {

    public Supplier makeSupplierFromDto(SupplierDto supplierDto) {
        Supplier supplier = new Supplier();
        supplier.setName(supplierDto.getName());
        supplier.setEmail(supplierDto.getEmail());
        supplier.setCreationDate(new Date());
        supplier.setUuid(UUID.randomUUID().toString());
        supplier.setAddress(supplierDto.getAddress());
        supplier.setCity(supplierDto.getCity());
        supplier.setMobileNumber(supplierDto.getMobileNumber());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplier.setFaxNumber(supplierDto.getFaxNumber());
        supplier.setWebsite(supplierDto.getWebsite());
        supplier.setContact(supplierDto.getContact());
        supplier.setCappedBalance(supplierDto.getCappedBalance());
        supplier.setInitialBalance(supplierDto.getInitialBalance());
        supplier.setPatent(supplierDto.getPatent());
        supplier.setIce(supplierDto.getIce());

        return supplier;
    }

    public SupplierDto makeSupplierDtoFromSupplier(Supplier supplier) {
        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setName(supplier.getName());
        supplierDto.setEmail(supplier.getEmail());
        supplierDto.setCreationDate(supplier.getCreationDate().toString());
        supplierDto.setUuid(supplier.getUuid());
        supplierDto.setAddress(supplier.getAddress());
        supplierDto.setCity(supplier.getCity());
        supplierDto.setMobileNumber(supplier.getMobileNumber());
        supplierDto.setPhoneNumber(supplier.getPhoneNumber());
        supplierDto.setFaxNumber(supplier.getFaxNumber());
        supplierDto.setWebsite(supplier.getWebsite());
        supplierDto.setContact(supplier.getContact());
        supplierDto.setCappedBalance(supplier.getCappedBalance());
        supplierDto.setInitialBalance(supplier.getInitialBalance());
        supplierDto.setPatent(supplier.getPatent());
        supplierDto.setIce(supplier.getIce());

        return supplierDto;
    }
}
