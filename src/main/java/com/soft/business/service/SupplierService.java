package com.soft.business.service;

import com.soft.business.dto.SupplierDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface SupplierService {
    SupplierDto createSupplier(SupplierDto supplierDto, Authentication authentication);

    SupplierDto updateSupplier(String uuid, SupplierDto supplierDto);

    void deleteSupplierByUuid(String uuid);

    SupplierDto findSupplierByUuid(String uuid);

    List<SupplierDto> findAllSuppliers(Authentication authentication);
}
