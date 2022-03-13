package com.soft.business.service;

import com.soft.business.dto.ProductFamilyDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductFamilyService {

    List<ProductFamilyDto> findProductFamilies();

    ProductFamilyDto findProductFamilyByUuid(String uuid);

    void deleteProductFamilyByUuid(String uuid);

    ResponseEntity<?> createProductFamily(ProductFamilyDto productFamilyDto);

    ResponseEntity<?> updateProductFamilyByUuid(String uuid, ProductFamilyDto productFamilyDto);
}
