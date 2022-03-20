package com.soft.business.controller;

import com.soft.business.dto.ProductFamilyDto;
import com.soft.business.service.ProductFamilyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/productsFamilies")
public class ProductFamilyController {

    private ProductFamilyService productFamilyService;

    public ProductFamilyController(ProductFamilyService productFamilyService) {
        this.productFamilyService = productFamilyService;
    }

    @GetMapping
    public ResponseEntity<?> findProductFamilies() {
            List<ProductFamilyDto> productFamilies = productFamilyService.findProductFamilies();

            return new ResponseEntity<>(productFamilies, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getProductFamilyByUuid(@PathVariable("uuid") String uuid) {
        ProductFamilyDto productFamily = productFamilyService.findProductFamilyByUuid(uuid);

        return new ResponseEntity<>(productFamily, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteProductFamilyByUuid(@PathVariable("uuid") String uuid) {
        productFamilyService.deleteProductFamilyByUuid(uuid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createProductFamily(@Valid @RequestBody ProductFamilyDto productFamilyDto) {
        productFamilyService.createProductFamily(productFamilyDto);

        return new ResponseEntity<>(productFamilyDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<?> updateProductFamilyByUuid(
            @PathVariable("uuid") String uuid,
            @Valid @RequestBody ProductFamilyDto productFamilyDto
    ) {
        productFamilyService.updateProductFamilyByUuid(uuid, productFamilyDto);

        return new ResponseEntity<>(productFamilyDto, HttpStatus.OK);
    }

}
