package com.soft.business.service;

import com.soft.business.dto.SupplierDto;
import com.soft.business.mapper.SupplierMapper;
import com.soft.business.model.Supplier;
import com.soft.business.repository.SupplierRepository;
import com.soft.business.security.providers.JwtTokenProvider;
import com.soft.business.util.validator.SupplierValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    private SupplierMapper supplierMapper;
    private SupplierRepository supplierRepository;
    private SupplierValidator supplierValidator;
    private static final Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);

    public SupplierServiceImpl(SupplierMapper supplierMapper,
                               SupplierRepository supplierRepository,
                               SupplierValidator supplierValidator) {
        this.supplierMapper = supplierMapper;
        this.supplierRepository = supplierRepository;
        this.supplierValidator = supplierValidator;
    }

    @Override
    public SupplierDto createSupplier(SupplierDto supplierDto, Authentication authentication) {
        supplierValidator.createSupplierValidator(supplierDto);
        Supplier savedSupplier = supplierRepository.save(supplierMapper.makeSupplierFromDto(supplierDto));
        return supplierMapper.makeDtoFromSupplier(savedSupplier);
    }

    @Override
    public SupplierDto updateSupplier(String uuid, SupplierDto supplierDto) {
        Optional<Supplier> oSupplier = this.supplierRepository.findByUuid(uuid);
        if(oSupplier.isEmpty()) throw new NoSuchElementException();

        Supplier supplier = supplierMapper.updateSupplierMapper(supplierDto, oSupplier.get());
        Supplier savedSupplier = supplierRepository.save(supplier);
        return supplierMapper.makeDtoFromSupplier(savedSupplier);
    }

    @Override
    @Transactional
    public void deleteSupplierByUuid(String uuid) {
        long isDeleted = this.supplierRepository.deleteByUuid(uuid);
        if(isDeleted == 0) throw new NoSuchElementException();
    }

    @Override
    public SupplierDto findSupplierByUuid(String uuid) {
        Optional<Supplier> oSupplier = this.supplierRepository.findByUuid(uuid);
        Supplier supplier = oSupplier.get();
        return this.supplierMapper.makeDtoFromSupplier(supplier);
    }

    @Override
    public List<SupplierDto> findAllSuppliers(Authentication authentication) {

        List<Supplier> suppliers = this.supplierRepository.findByOrgId(2);
        List<SupplierDto> supplierDtos = new ArrayList<>();
        suppliers.forEach(supplier -> supplierDtos.add(this.supplierMapper.makeDtoFromSupplier(supplier)));
        return supplierDtos;
    }
}
