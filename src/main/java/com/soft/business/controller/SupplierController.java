package com.soft.business.controller;

import com.soft.business.dto.SupplierDto;
import com.soft.business.service.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> retrieveUniqueOrganisationByUuid(@PathVariable("uuid") String uuid) {
        SupplierDto supplierDto = this.supplierService.retrieveSupplierByUuid(uuid);
        if(supplierDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(supplierDto);
    }

    @PostMapping()
    public ResponseEntity<?> CreateSupplier(@Valid @RequestBody SupplierDto supplierDto) {
        return supplierService.registerUser(supplierDto);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteSupplierByUuid(@PathVariable("uuid") String uuid) {
        supplierService.deleteSupplierByUuid(uuid);
        return ResponseEntity.ok().build();
    }

}
