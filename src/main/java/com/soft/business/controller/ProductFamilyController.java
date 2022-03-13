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
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getProductFamilyByUuid(@PathVariable("uuid") String uuid) {
        ProductFamilyDto productFamilyDto = productFamilyService.findProductFamilyByUuid(uuid);

        if (productFamilyDto != null) {
            return new ResponseEntity<>(productFamilyDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteProductFamilyByUuid(@PathVariable("uuid") String uuid) {
        productFamilyService.deleteProductFamilyByUuid(uuid);
        return ResponseEntity.ok().build();
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
