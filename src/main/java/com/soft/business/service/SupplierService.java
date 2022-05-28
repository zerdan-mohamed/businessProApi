package com.soft.business.service;

import com.soft.business.dto.SupplierDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface SupplierService {
    SupplierDto createSupplier(Authentication authentication, SupplierDto supplierDto);

    SupplierDto updateSupplier(Authentication authentication, String uuid, SupplierDto supplierDto);

    void deleteSupplierByUuid(Authentication authentication, String uuid);

    SupplierDto findSupplierByUuid(Authentication authentication, String uuid);

    List<SupplierDto> findAllSuppliers(Authentication authentication);
}
