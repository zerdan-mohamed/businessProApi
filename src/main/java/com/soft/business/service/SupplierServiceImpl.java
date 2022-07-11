package com.soft.business.service;

import com.soft.business.dto.SupplierDto;
import com.soft.business.mapper.SupplierMapper;
import com.soft.business.model.Supplier;
import com.soft.business.repository.SupplierRepository;
import com.soft.business.service.organization.OrganizationService;
import com.soft.business.util.validator.SupplierValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public SupplierDto createSupplier(Authentication authentication,
                                      SupplierDto supplierDto) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);
        supplierValidator.createSupplierValidator(supplierDto);
        Supplier savedSupplier = supplierRepository.save(supplierMapper.makeSupplierFromDto(orgId, supplierDto));
        return supplierMapper.makeDtoFromSupplier(savedSupplier);
    }

    @Override
    public SupplierDto updateSupplier(
            Authentication authentication, String uuid, SupplierDto supplierDto) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);
        Optional<Supplier> oSupplier = this.supplierRepository.findByUuidAndOrgId(uuid, orgId);
        if(oSupplier.isEmpty()) throw new NoSuchElementException();

        Supplier supplier = supplierMapper.updateSupplierMapper(supplierDto, oSupplier.get());
        Supplier savedSupplier = supplierRepository.save(supplier);
        return supplierMapper.makeDtoFromSupplier(savedSupplier);
    }

    @Override
    @Transactional
    public void deleteSupplierByUuid(Authentication authentication, String uuid) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);
        long isDeleted = this.supplierRepository.deleteByUuidAndOrgId(uuid, orgId);
        if(isDeleted == 0) throw new NoSuchElementException();
    }

    @Override
    public SupplierDto findSupplierByUuid(Authentication authentication,
                                          String uuid) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);
        Optional<Supplier> oSupplier = this.supplierRepository.findByUuidAndOrgId(uuid, orgId);
        Supplier supplier = oSupplier.get();
        return this.supplierMapper.makeDtoFromSupplier(supplier);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierDto> findAllSuppliers(Authentication authentication) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);
        List<Supplier> suppliers = this.supplierRepository.findByOrgId(orgId);
        List<SupplierDto> supplierDtos = new ArrayList<>();
        suppliers.forEach(supplier -> supplierDtos.add(this.supplierMapper.makeDtoFromSupplier(supplier)));
        return supplierDtos;
    }
}
