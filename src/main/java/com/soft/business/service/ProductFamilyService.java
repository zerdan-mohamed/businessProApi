package com.soft.business.service;

import com.soft.business.dto.ProductFamilyDto;

import java.util.List;

public interface ProductFamilyService {

    List<ProductFamilyDto> findProductFamilies();

    ProductFamilyDto findProductFamilyByUuid(String uuid);

    void deleteProductFamilyByUuid(String uuid);

    ProductFamilyDto createProductFamily(ProductFamilyDto productFamilyDto);

    ProductFamilyDto updateProductFamilyByUuid(String uuid, ProductFamilyDto productFamilyDto);
}
