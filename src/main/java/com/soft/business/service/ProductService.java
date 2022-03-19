package com.soft.business.service;

import com.soft.business.dto.ProductDto;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

public interface ProductService {

    List<ProductDto> findProducts();

    ResponseEntity<?> findProductByUuid(String uuid);

    ResponseEntity<?> deleteProductByUuid(String uuid);

    ResponseEntity<?>  createProduct(ProductDto productDto) throws ParseException;

    ResponseEntity<?> updateProductByUuid(String uuid, ProductDto productDto);

}
