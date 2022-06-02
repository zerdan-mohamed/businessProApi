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
import java.util.Set;

@RestController
@RequestMapping("api/v1/supplierOrderItems")
public class SupplierOrderItemController {

    private final SupplierOrderItemService supplierOrderItemService;

    public SupplierOrderItemController(SupplierOrderItemService supplierOrderItemService) {
        this.supplierOrderItemService = supplierOrderItemService;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Set<SupplierOrderItemDto>> findSupplierOrderItems(
            Authentication authentication,
            @PathVariable("uuid") String supplierOrderUuid
    ) {
        Set<SupplierOrderItemDto> supplierOrderItems = supplierOrderItemService.findSupplierOrderItems(authentication, supplierOrderUuid);

        return new ResponseEntity<>(supplierOrderItems, HttpStatus.OK);
    }

    //@GetMapping("/{uuid}")
    //public ResponseEntity<?> getSupplierOrderItemByUuid(
      //      Authentication authentication,
        //    @PathVariable("uuid") String uuid
    //) {
      //  SupplierOrderItemDto supplierOrderItem = supplierOrderItemService.findSupplierOrderItemByUuid(authentication, uuid);

        //return new ResponseEntity<>(supplierOrderItem, HttpStatus.OK);
    //}

    @DeleteMapping("/{uuid}")
    public ResponseEntity deleteSupplierOrderItemByUuid(
            Authentication authentication,
            @PathVariable("uuid") String uuid
    ) {
        supplierOrderItemService.deleteSupplierOrderItemByUuid(authentication, uuid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Set<SupplierOrderItemDto>> createSupplierOrderItem(
            Authentication authentication,
            @Valid @RequestBody List<SupplierOrderItemDto> supplierOrderItemDto
    ) throws ParseException {
        return new ResponseEntity<>(supplierOrderItemService.createSupplierOrderItem(authentication, supplierOrderItemDto)
                , HttpStatus.CREATED);
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
