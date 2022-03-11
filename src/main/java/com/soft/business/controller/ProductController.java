package com.soft.business.controller;

import com.soft.business.dto.ProductDto;
import com.soft.business.model.Product;
import com.soft.business.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/v1/products")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // TODO: Refactor findProducts method to implement SPRING SPECIFICATION behavior
    @GetMapping
    public ResponseEntity<?> findProducts(@RequestParam(required = false) String name) {
        try {
            List<ProductDto> products;

            if (name == null)
                products = productService.findProducts();
            else
                products = productService.findProductsByName(name);

            if (products.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getProductByUuid(@PathVariable("uuid") String uuid) {
        Optional<ProductDto> product = productService.findProductByUuid(uuid);

        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteProductByUuid(@PathVariable("uuid") String uuid) {
        productService.deleteProductByUuid(uuid);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    // #########################################################

    @PatchMapping("/{uuid}")
    public ResponseEntity<?> updateProductByUuid(
            @PathVariable("uuid") String uuid,
            @Valid @RequestBody ProductDto productDto) {

        return productService.updateProductByUuid(uuid, productDto);
    }

}
