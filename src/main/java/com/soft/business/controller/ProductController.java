package com.soft.business.controller;

import com.soft.business.dto.ProductDto;
import com.soft.business.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // TODO: Refactor findProducts method to implement SPRING SPECIFICATION behavior
    @GetMapping
    public ResponseEntity<?> findProducts(@RequestParam(required = false) String name) {
        List<ProductDto> productsDto = productService.findProducts();
        return new ResponseEntity<>(productsDto, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getProductByUuid(@PathVariable("uuid") String uuid) {
        return productService.findProductByUuid(uuid);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteProductByUuid(@PathVariable("uuid") String uuid) {
        return productService.deleteProductByUuid(uuid);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto) throws ParseException {
        return productService.createProduct(productDto);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<?> updateProductByUuid(
            @PathVariable("uuid") String uuid, @Valid @RequestBody ProductDto productDto) {
        return productService.updateProductByUuid(uuid, productDto);
    }

}
