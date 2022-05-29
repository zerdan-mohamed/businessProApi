package com.soft.business.service;

import com.soft.business.dto.ProductFamilyDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ProductFamilyService {

    List<ProductFamilyDto> findProductFamilies(Authentication authentication);

    ProductFamilyDto findProductFamilyByUuid(Authentication authentication, String uuid);

    void deleteProductFamilyByUuid(Authentication authentication, String uuid);

    ProductFamilyDto createProductFamily(Authentication authentication, ProductFamilyDto productFamilyDto);

    ProductFamilyDto updateProductFamilyByUuid(Authentication authentication, String uuid, ProductFamilyDto productFamilyDto);
}
