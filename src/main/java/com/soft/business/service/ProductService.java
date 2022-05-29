package com.soft.business.service;

import com.soft.business.dto.ProductDto;
import org.springframework.security.core.Authentication;

import java.text.ParseException;
import java.util.List;

public interface ProductService {

    List<ProductDto> findProducts(Authentication authentication);

    ProductDto findProductByUuid(Authentication authentication, String uuid);

    void deleteProductByUuid(Authentication authentication, String uuid);

    ProductDto  createProduct(Authentication authentication, ProductDto productDto) throws ParseException;

    ProductDto updateProductByUuid(Authentication authentication, String uuid, ProductDto productDto);

}
