package com.soft.business.controller;

import com.soft.business.dto.SupplierDto;
import com.soft.business.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/{uuid}")
    @PreAuthorize("hasAuthority('FUNC_SUPPLIER_RETRIEVE')")
    public ResponseEntity<SupplierDto> findSupplierByUuid(Authentication authentication,
                                                @PathVariable("uuid") String uuid) {
        SupplierDto supplierDto = this.supplierService.findSupplierByUuid(authentication, uuid);
        return ResponseEntity.ok(supplierDto);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('FUNC_SUPPLIER_RETRIEVE')")
    public ResponseEntity<List<SupplierDto>> findAllSuppliers(Authentication authentication) {
        List<SupplierDto> suppliers = this.supplierService.findAllSuppliers(authentication);
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('FUNC_SUPPLIER_CREATE')")
    public ResponseEntity<SupplierDto> createSupplier(@Valid @RequestBody SupplierDto supplierDto,
                                            Authentication authentication) {
        return new ResponseEntity<>(supplierService.createSupplier(authentication, supplierDto), HttpStatus.OK);
    }

    @PatchMapping("/{uuid}")
    @PreAuthorize("hasAuthority('FUNC_SUPPLIER_UPDATE')")
    public ResponseEntity<SupplierDto> updateSupplier(Authentication authentication,
                                            @PathVariable("uuid") String uuid,
                                            @Valid @RequestBody SupplierDto supplierDto) {
        return new ResponseEntity<>(supplierService.updateSupplier(authentication, uuid, supplierDto), HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasAuthority('FUNC_SUPPLIER_DELETE')")
    public ResponseEntity deleteSupplierByUuid(Authentication authentication,
                                                    @PathVariable("uuid") String uuid) {
        supplierService.deleteSupplierByUuid(authentication, uuid);
        return ResponseEntity.ok().build();
    }

}
