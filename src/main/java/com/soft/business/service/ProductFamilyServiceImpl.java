package com.soft.business.service;

import com.soft.business.dto.ProductFamilyDto;
import com.soft.business.mapper.ProductFamilyMapper;
import com.soft.business.model.ProductFamily;
import com.soft.business.repository.ProductFamilyRepository;
import com.soft.business.service.organization.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductFamilyServiceImpl implements ProductFamilyService {

    private final ProductFamilyRepository productFamilyRepository;
    private final ProductFamilyMapper productFamilyMapper;

    @Override
    public List<ProductFamilyDto> findProductFamilies(Authentication authentication) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);
        List<ProductFamily> productFamilies = productFamilyRepository.findByOrgId(orgId);

        return productFamilies.stream().map(productFamily -> productFamilyMapper.modelToDto(productFamily))
                .collect(Collectors.toList());
    }

    @Override
    public ProductFamilyDto findProductFamilyByUuid(Authentication authentication, String uuid) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);
        Optional<ProductFamily>  productFamily = productFamilyRepository.findByUuidAndOrgId(uuid, orgId);

        return productFamilyMapper.modelToDto(productFamily.get());
    }

    @Override
    public void deleteProductFamilyByUuid(Authentication authentication, String uuid) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);
        productFamilyRepository.deleteByUuidAndOrgId(uuid, orgId);
    }

    @Override
    public ProductFamilyDto createProductFamily(
            Authentication authentication, ProductFamilyDto productFamilyDto
    ) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);
        ProductFamily productFamily = productFamilyRepository.save(productFamilyMapper.dtoToModelCreate(productFamilyDto, orgId));

        return productFamilyMapper.modelToDto(productFamily);
    }

    @Override
    public ProductFamilyDto updateProductFamilyByUuid(
            Authentication authentication, String uuid, ProductFamilyDto productFamilyDto) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);
        Optional<ProductFamily> productFamilyDb = this.productFamilyRepository.findByUuidAndOrgId(uuid, orgId);

        if (productFamilyDb.isEmpty()) throw new NoSuchElementException();

        productFamilyDb.get().setName(productFamilyDto.getName());
        productFamilyRepository.save(productFamilyDb.get());

        return productFamilyMapper.modelToDto(productFamilyDb.get());
    }
}
