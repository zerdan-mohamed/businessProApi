package com.soft.business.controller;

import com.soft.business.dto.SupplierOrderItemDto;
import com.soft.business.service.SupplierOrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api/v1/supplierOrderItems")
public class SupplierOrderItemController {

    private SupplierOrderItemService supplierOrderItemService;

    public SupplierOrderItemController(SupplierOrderItemService supplierOrderItemService) {
        this.supplierOrderItemService = supplierOrderItemService;
    }

    @GetMapping
    public ResponseEntity<?> findSupplierOrderItems(
            Authentication authentication,
            @PathVariable("uuid") String supplierOrderUuid
    ) {
        List<SupplierOrderItemDto> supplierOrderItems = supplierOrderItemService.findSupplierOrderItems(authentication, supplierOrderUuid);

        return new ResponseEntity<>(supplierOrderItems, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getSupplierOrderItemByUuid(
            Authentication authentication,
            @PathVariable("uuid") String uuid
    ) {
        SupplierOrderItemDto supplierOrderItem = supplierOrderItemService.findSupplierOrderItemByUuid(authentication, uuid);

        return new ResponseEntity<>(supplierOrderItem, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteSupplierOrderItemByUuid(
            Authentication authentication,
            @PathVariable("uuid") String uuid
    ) {
        supplierOrderItemService.deleteSupplierOrderItemByUuid(authentication, uuid);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // TODO:  add supplierOrderUuid param
    @PostMapping
    public ResponseEntity<?> createSupplierOrderItem(
            Authentication authentication,
            @Valid @RequestBody SupplierOrderItemDto supplierOrderItemDto
    ) throws ParseException {
        supplierOrderItemService.createSupplierOrderItem(authentication, supplierOrderItemDto);

        return new ResponseEntity<>(supplierOrderItemDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<?> updateSupplierOrderItemByUuid(
            Authentication authentication,
            @PathVariable("uuid") String uuid,
            @Valid @RequestBody SupplierOrderItemDto supplierOrderItemDto
    ) {
        supplierOrderItemService.updateSupplierOrderItem(authentication, uuid, supplierOrderItemDto);

        return new ResponseEntity<>(supplierOrderItemDto, HttpStatus.OK);
    }


}
