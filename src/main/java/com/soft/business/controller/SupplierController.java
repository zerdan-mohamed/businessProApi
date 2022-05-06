package com.soft.business.controller;

import com.soft.business.dto.SupplierDto;
import com.soft.business.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/{orgUuid}/{uuid}")
    public ResponseEntity<?> findSupplierByUuid(@PathVariable("orgUuid") String orgUuid,
                                                @PathVariable("uuid") String uuid) {
        SupplierDto supplierDto = this.supplierService.findSupplierByUuid(uuid);
        return ResponseEntity.ok(supplierDto);
    }

    @GetMapping()
    public ResponseEntity<?> findAllSuppliers(Authentication authentication) {
        List<SupplierDto> suppliers = this.supplierService.findAllSuppliers(authentication);
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createSupplier(@Valid @RequestBody SupplierDto supplierDto,
                                            Authentication authentication) {
        return new ResponseEntity<>(supplierService.createSupplier(supplierDto, authentication), HttpStatus.OK);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<?> updateSupplier(@PathVariable("uuid") String uuid, @Valid @RequestBody SupplierDto supplierDto) {
        return new ResponseEntity<>(supplierService.updateSupplier(uuid, supplierDto), HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteSupplierByUuid(@PathVariable("uuid") String uuid) {
        supplierService.deleteSupplierByUuid(uuid);
        return ResponseEntity.ok().build();
    }

}
