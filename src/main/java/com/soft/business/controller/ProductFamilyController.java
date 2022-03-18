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
@RequestMapping("/v1/productsFamilies")
public class ProductFamilyController {

    private ProductFamilyService productFamilyService;

    public ProductFamilyController(ProductFamilyService productFamilyService) {
        this.productFamilyService = productFamilyService;
    }

    // TODO: Refactor findProducts method to implement SPRING SPECIFICATION behavior
    @GetMapping
    public ResponseEntity<?> findProductFamilies(@RequestParam(required = false) String name) {
        try {
            List<ProductFamilyDto> productFamiliesDto = productFamilyService.findProductFamilies();

            if (productFamiliesDto.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(productFamiliesDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getProductFamilyByUuid(@PathVariable("uuid") String uuid) {
        return productFamilyService.findProductFamilyByUuid(uuid);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteProductFamilyByUuid(@PathVariable("uuid") String uuid) {
        return productFamilyService.deleteProductFamilyByUuid(uuid);
    }

    @PostMapping
    public ResponseEntity<?> createProductFamily(@Valid @RequestBody ProductFamilyDto productFamilyDto) {
        return productFamilyService.createProductFamily(productFamilyDto);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<?> updateProductFamilyByUuid(
            @PathVariable("uuid") String uuid,
            @Valid @RequestBody ProductFamilyDto productFamilyDto) {

        return productFamilyService.updateProductFamilyByUuid(uuid, productFamilyDto);
    }

}
