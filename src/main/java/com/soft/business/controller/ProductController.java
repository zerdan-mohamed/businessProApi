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

    @GetMapping
    public ResponseEntity<?> findProducts(@RequestParam(required = false) String name) {
        List<ProductDto> productsDto = productService.findProducts();

        return new ResponseEntity<>(productsDto, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getProductByUuid(@PathVariable("uuid") String uuid) {
        ProductDto product = productService.findProductByUuid(uuid);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteProductByUuid(@PathVariable("uuid") String uuid) {
        productService.deleteProductByUuid(uuid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto) throws ParseException {
        productService.createProduct(productDto);

        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<?> updateProductByUuid(
            @PathVariable("uuid") String uuid, @Valid @RequestBody ProductDto productDto) {
        productService.updateProductByUuid(uuid, productDto);

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
}
