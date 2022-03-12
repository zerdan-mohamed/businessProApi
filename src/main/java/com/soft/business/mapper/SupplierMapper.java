package com.soft.business.mapper;

import com.soft.business.dto.SupplierDto;
import com.soft.business.model.Supplier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
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

    public SupplierDto makeDtoFromSupplier(Supplier supplier) {
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

    public Supplier updateSupplierMapper(SupplierDto supplierDto, Supplier dbSupplier) {
        Supplier supplier = new Supplier();

        if(null != supplierDto.getName()) supplier.setName(supplierDto.getName());
        else supplier.setName(dbSupplier.getName());

        if(null != supplierDto.getAddress()) supplier.setAddress(supplierDto.getAddress());
        else supplier.setAddress(dbSupplier.getAddress());

        if(null != supplierDto.getIce()) supplier.setIce(supplierDto.getIce());
        else supplier.setIce(dbSupplier.getIce());

        if(null != supplierDto.getContact()) supplier.setContact(supplierDto.getContact());
        else supplier.setContact(dbSupplier.getContact());

        if(null != supplierDto.getCity()) supplier.setCity(supplierDto.getCity());
        else supplier.setCity(dbSupplier.getCity());

        if(null != supplierDto.getEmail()) supplier.setEmail(supplierDto.getEmail());
        else supplier.setEmail(dbSupplier.getEmail());

        if(null != supplierDto.getPhoneNumber()) supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        else supplier.setPhoneNumber(dbSupplier.getPhoneNumber());

        if(null != supplierDto.getMobileNumber()) supplier.setMobileNumber(supplierDto.getMobileNumber());
        else supplier.setMobileNumber(dbSupplier.getMobileNumber());

        if(null != supplierDto.getFaxNumber()) supplier.setFaxNumber(supplierDto.getFaxNumber());
        else supplier.setFaxNumber(dbSupplier.getFaxNumber());

        if(null != supplierDto.getPatent()) supplier.setPatent(supplierDto.getPatent());
        else supplier.setPatent(dbSupplier.getPatent());

        if(null != supplierDto.getWebsite()) supplier.setWebsite(supplierDto.getWebsite());
        else supplier.setWebsite(dbSupplier.getWebsite());

        if(null != supplierDto.getInitialBalance()) supplier.setInitialBalance(supplierDto.getInitialBalance());
        else supplier.setInitialBalance(dbSupplier.getInitialBalance());

        if(null != supplierDto.getCappedBalance()) supplier.setCappedBalance(supplierDto.getCappedBalance());
        else supplier.setCappedBalance(dbSupplier.getCappedBalance());

        supplier.setUuid(dbSupplier.getUuid());

        supplier.setId(dbSupplier.getId());

        supplier.setCreationDate(dbSupplier.getCreationDate());

        return supplier;
    }
}
