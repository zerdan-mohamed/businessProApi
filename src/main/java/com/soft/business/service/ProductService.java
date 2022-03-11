package com.soft.business.service;

import com.soft.business.dto.ProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> findProducts();

    List<ProductDto> findProductsByName(String name);

    Optional<ProductDto> findProductByUuid(String id);

    void deleteProductByUuid(String uuid);

    ResponseEntity<?>  createProduct(ProductDto productRequest);

    ResponseEntity<?> updateProductByUuid(String uuid, ProductDto productDto);

}
