package com.soft.business.controller;

import com.soft.business.dto.SupplierOrderDto;
import com.soft.business.service.SupplierOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api/v1/supplierOrders")
public class SupplierOrderController {

    private SupplierOrderService supplierOrderService;

    public SupplierOrderController(SupplierOrderService supplierOrderService) {
        this.supplierOrderService = supplierOrderService;
    }

    // TODO: add organisation
    @GetMapping
    public ResponseEntity<?> findSupplierOrders() {
        List<SupplierOrderDto> supplierOrders = supplierOrderService.findSupplierOrders();

        return new ResponseEntity<>(supplierOrders, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getSupplierOrderByUuid(@PathVariable("uuid") String uuid) {
        SupplierOrderDto supplierOrder = supplierOrderService.findSupplierOrderByUuid(uuid);

        return new ResponseEntity<>(supplierOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteSupplierOrderByUuid(@PathVariable("uuid") String uuid) {
        supplierOrderService.deleteSupplierOrderByUuid(uuid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createSupplierOrder(@Valid @RequestBody SupplierOrderDto supplierOrderDto) throws ParseException {
        supplierOrderService.createSupplierOrder(supplierOrderDto);

        return new ResponseEntity<>(supplierOrderDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<?> updateSupplierOrderByUuid(
            @PathVariable("uuid") String uuid, @Valid @RequestBody SupplierOrderDto supplierOrderDto) {
        supplierOrderService.updateSupplierOrder(uuid, supplierOrderDto);

        return new ResponseEntity<>(supplierOrderDto, HttpStatus.OK);
    }

}
