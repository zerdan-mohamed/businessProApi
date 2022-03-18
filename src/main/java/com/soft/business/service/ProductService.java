package com.soft.business.service;

import com.soft.business.dto.ProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    List<ProductDto> findProducts();

    ResponseEntity<?> findProductByUuid(String uuid);

    ResponseEntity<?> deleteProductByUuid(String uuid);

    ResponseEntity<?>  createProduct(ProductDto productDto);

    ResponseEntity<?> updateProductByUuid(String uuid, ProductDto productDto);

}
