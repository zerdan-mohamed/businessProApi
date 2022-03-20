package com.soft.business.service;

import com.soft.business.dto.ProductDto;

import java.text.ParseException;
import java.util.List;

public interface ProductService {

    List<ProductDto> findProducts();

    ProductDto findProductByUuid(String uuid);

    void deleteProductByUuid(String uuid);

    ProductDto  createProduct(ProductDto productDto) throws ParseException;

    ProductDto updateProductByUuid(String uuid, ProductDto productDto);

}
