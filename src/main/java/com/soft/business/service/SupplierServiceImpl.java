package com.soft.business.service;

import com.soft.business.dto.SupplierDto;
import com.soft.business.mapper.SupplierMapper;
import com.soft.business.model.Supplier;
import com.soft.business.repository.SupplierRepository;
import com.soft.business.util.validator.SupplierValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService{

    private SupplierMapper supplierMapper;
    private SupplierRepository supplierRepository;
    SupplierValidator supplierValidator;

    public SupplierServiceImpl(SupplierMapper supplierMapper,
                               SupplierRepository supplierRepository,
                               SupplierValidator supplierValidator) {
        this.supplierMapper = supplierMapper;
        this.supplierRepository = supplierRepository;
        this.supplierValidator = supplierValidator;
    }

    @Override
    public ResponseEntity<?> registerUser(SupplierDto supplierDto) {
        supplierValidator.createSupplierValidator(supplierDto);
        Supplier supplier = supplierMapper.makeSupplierFromDto(supplierDto);

        supplierRepository.save(supplier);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/suppliers/{name}")
                .buildAndExpand(supplier.getName()).toUri();

        return ResponseEntity.created(location).body("User registered successfully");
    }

    @Override
    @Transactional
    public void deleteSupplierByUuid(String uuid) {
        long isDeleted = this.supplierRepository.deleteByUuid(uuid);
    }

    @Override
    public SupplierDto retrieveSupplierByUuid(String uuid) {
        Optional<Supplier> oSupplier = this.supplierRepository.findByUuid(uuid);
        if(!oSupplier.isEmpty()) {
            Supplier supplier = oSupplier.get();
            return this.supplierMapper.makeSupplierDtoFromSupplier(supplier);
        }
        return null;
    }
}
