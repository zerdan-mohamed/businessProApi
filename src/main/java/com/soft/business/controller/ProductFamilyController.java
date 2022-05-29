package com.soft.business.controller;

import com.soft.business.dto.ProductFamilyDto;
import com.soft.business.service.ProductFamilyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/productsFamilies")
public class ProductFamilyController {

    private ProductFamilyService productFamilyService;

    public ProductFamilyController(ProductFamilyService productFamilyService) {
        this.productFamilyService = productFamilyService;
    }

    @GetMapping
    public ResponseEntity<?> findProductFamilies(Authentication authentication) {
            List<ProductFamilyDto> productFamilies = productFamilyService.findProductFamilies(authentication);

            return new ResponseEntity<>(productFamilies, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getProductFamilyByUuid(
            Authentication authentication, @PathVariable("uuid") String uuid
    ) {
        ProductFamilyDto productFamily = productFamilyService.findProductFamilyByUuid(authentication, uuid);

        return new ResponseEntity<>(productFamily, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteProductFamilyByUuid(
            Authentication authentication, @PathVariable("uuid") String uuid
    ) {
        productFamilyService.deleteProductFamilyByUuid(authentication, uuid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createProductFamily(
            Authentication authentication, @Valid @RequestBody ProductFamilyDto productFamilyDto
    ) {
        productFamilyService.createProductFamily(authentication, productFamilyDto);

        return new ResponseEntity<>(productFamilyDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<?> updateProductFamilyByUuid(
            Authentication authentication,
            @PathVariable("uuid") String uuid,
            @Valid @RequestBody ProductFamilyDto productFamilyDto
    ) {
        productFamilyService.updateProductFamilyByUuid(authentication, uuid, productFamilyDto);

        return new ResponseEntity<>(productFamilyDto, HttpStatus.OK);
    }

}
