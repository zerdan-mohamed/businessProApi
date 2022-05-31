package com.soft.business.controller;

import com.soft.business.dto.ProductDto;
import com.soft.business.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findProducts(Authentication authentication) {
        List<ProductDto> productsDto = productService.findProducts(authentication);

        return new ResponseEntity<>(productsDto, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProductDto> getProductByUuid(
            Authentication authentication, @PathVariable("uuid") String uuid
    ) {
        ProductDto product = productService.findProductByUuid(authentication, uuid);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity deleteProductByUuid(
            Authentication authentication, @PathVariable("uuid") String uuid
    ) {
        productService.deleteProductByUuid(authentication, uuid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            Authentication authentication, @Valid @RequestBody ProductDto productDto
    ) throws ParseException {
        return new ResponseEntity<>(productService.createProduct(authentication, productDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<ProductDto> updateProductByUuid(
            Authentication authentication,
            @PathVariable("uuid") String uuid,
            @Valid @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.updateProductByUuid(authentication, uuid, productDto), HttpStatus.OK);
    }
}
