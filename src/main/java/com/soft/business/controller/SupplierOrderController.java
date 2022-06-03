package com.soft.business.controller;

import com.soft.business.dto.SupplierOrderDto;
import com.soft.business.service.SupplierOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("api/v1/supplierOrders")
public class SupplierOrderController {

    private SupplierOrderService supplierOrderService;

    public SupplierOrderController(SupplierOrderService supplierOrderService) {
        this.supplierOrderService = supplierOrderService;
    }

    @GetMapping
    public ResponseEntity<Set<SupplierOrderDto>> findSupplierOrders(Authentication authentication) {
        Set<SupplierOrderDto> supplierOrders = supplierOrderService.findSupplierOrders(authentication);

        return new ResponseEntity<>(supplierOrders, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<SupplierOrderDto> getSupplierOrderByUuid(
            Authentication authentication, @PathVariable("uuid") String uuid
    ) {
        SupplierOrderDto supplierOrder = supplierOrderService.findSupplierOrderByUuid(authentication, uuid);

        return new ResponseEntity<>(supplierOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity deleteSupplierOrderByUuid(
            Authentication authentication, @PathVariable("uuid") String uuid
    ) {
        supplierOrderService.deleteSupplierOrderByUuid(authentication, uuid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SupplierOrderDto> createSupplierOrder(
            Authentication authentication, @Valid @RequestBody SupplierOrderDto supplierOrderDto
    ) {
        return new ResponseEntity<>(supplierOrderService.createSupplierOrder(authentication, supplierOrderDto),
                                    HttpStatus.CREATED);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<SupplierOrderDto> updateSupplierOrderByUuid(
            Authentication authentication,
            @PathVariable("uuid") String uuid,
            @Valid @RequestBody SupplierOrderDto supplierOrderDto
    ) {
        return new ResponseEntity<>(supplierOrderService.updateSupplierOrder(
                                                                authentication,
                                                                uuid,
                                                                supplierOrderDto),
                                    HttpStatus.OK);
    }

}
