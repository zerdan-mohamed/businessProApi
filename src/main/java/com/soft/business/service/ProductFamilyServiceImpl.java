package com.soft.business.service;

import com.soft.business.dto.ProductFamilyDto;
import com.soft.business.mapper.ProductFamilyMapper;
import com.soft.business.model.ProductFamily;
import com.soft.business.repository.ProductFamilyRepository;
import com.soft.business.service.organization.OrganizationServiceImpl;
import com.soft.business.util.validator.ProductFamilyValidator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional
public class ProductFamilyServiceImpl implements ProductFamilyService {

    private ProductFamilyRepository productFamilyRepository;

    private ProductFamilyMapper productFamilyMapper;

    private ProductFamilyValidator productFamilyValidator;

    public ProductFamilyServiceImpl(
            ProductFamilyRepository productFamilyRepository,
            ProductFamilyMapper productFamilyMapper,
            ProductFamilyValidator productFamilyValidator
    ) {
        this.productFamilyRepository = productFamilyRepository;
        this.productFamilyMapper = productFamilyMapper;
        this.productFamilyValidator = productFamilyValidator;
    }

    @Override
    public List<ProductFamilyDto> findProductFamilies(Authentication authentication) {
        int orgId = OrganizationServiceImpl.getOrgIdFromPrincipal(authentication);
        List<ProductFamily> productFamilies = productFamilyRepository.findByOrgId(orgId);

        List<ProductFamilyDto> productFamiliesDto = new ArrayList<>();
        productFamilies.forEach(
                product -> productFamiliesDto.add(productFamilyMapper.makeDtoFromProductFamily(product))
        );

        return productFamiliesDto;
    }

    @Override
    public ProductFamilyDto findProductFamilyByUuid(Authentication authentication, String uuid) {
        int orgId = OrganizationServiceImpl.getOrgIdFromPrincipal(authentication);
        Optional<ProductFamily>  productFamily = productFamilyRepository.findByUuidAndOrgId(uuid, orgId);

        return productFamilyMapper.makeDtoFromProductFamily(productFamily.get());
    }

    @Override
    @Transactional
    public void deleteProductFamilyByUuid(Authentication authentication, String uuid) {
        int orgId = OrganizationServiceImpl.getOrgIdFromPrincipal(authentication);
        long isDeleted = productFamilyRepository.deleteByUuidAndOrgId(uuid, orgId);
        if(isDeleted == 0) throw new NoSuchElementException();
    }

    @Override
    public ProductFamilyDto createProductFamily(
            Authentication authentication, ProductFamilyDto productFamilyDto
    ) {
        int orgId = OrganizationServiceImpl.getOrgIdFromPrincipal(authentication);
        productFamilyValidator.createProductFamilyValidator (productFamilyDto);

        productFamilyRepository.save(productFamilyMapper.makeProductFamilyFromDto(orgId, productFamilyDto));

        return productFamilyDto;
    }

    @Override
    public ProductFamilyDto updateProductFamilyByUuid(
            Authentication authentication, String uuid, ProductFamilyDto productFamilyDto) {
        int orgId = OrganizationServiceImpl.getOrgIdFromPrincipal(authentication);
        Optional<ProductFamily> productFamilyDb = this.productFamilyRepository.findByUuidAndOrgId(uuid, orgId);

        if (productFamilyDb.isEmpty()) throw new NoSuchElementException();

        ProductFamily productFamily = productFamilyMapper.updateProductFamily(productFamilyDto, productFamilyDb.get());
        productFamilyRepository.save(productFamily);

        return productFamilyDto;
    }
}
