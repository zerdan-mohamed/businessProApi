package com.soft.business.service;

import com.soft.business.dto.SupplierDto;
import org.springframework.http.ResponseEntity;

public interface SupplierService {
    ResponseEntity<?> registerUser(SupplierDto supplierDto);

    void deleteSupplierByUuid(String uuid);

    SupplierDto retrieveSupplierByUuid(String uuid);
}
