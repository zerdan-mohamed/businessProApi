package com.soft.business.service;

import com.soft.business.dto.SupplierDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SupplierService {
    ResponseEntity<?> createSupplier(SupplierDto supplierDto);

    ResponseEntity<?> updateSupplier(String uuid, SupplierDto supplierDto);

    void deleteSupplierByUuid(String uuid);

    SupplierDto retrieveSupplierByUuid(String uuid);

    List<SupplierDto> getAllSuppliersByOrganisationId();
}
