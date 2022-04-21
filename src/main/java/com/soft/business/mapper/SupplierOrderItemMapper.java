package com.soft.business.mapper;

import com.soft.business.dto.ProductDto;
import com.soft.business.dto.SupplierOrderItemDto;
import com.soft.business.model.Product;
import com.soft.business.model.SupplierOrderItem;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SupplierOrderItemMapper {

    public SupplierOrderItem makeSupplierOrderItemFromDto(SupplierOrderItemDto supplierOrderItemDto) {
        SupplierOrderItem supplierOrderItem = new SupplierOrderItem();

        supplierOrderItem.setUuid(UUID.randomUUID().toString());
        supplierOrderItem.setMeasureUnite(supplierOrderItemDto.getMeasureUnite());
        supplierOrderItem.setQuantity(supplierOrderItemDto.getQuantity());
        supplierOrderItem.setPriceHT(supplierOrderItemDto.getPriceHT());
        supplierOrderItem.setVatRate(supplierOrderItem.getVatRate());
        supplierOrderItem.setProduct(new Product());
        supplierOrderItem.setDiscount(supplierOrderItemDto.getDiscount());

        return supplierOrderItem;
    }

    public SupplierOrderItemDto makeDtoFromSupplierOrderItem(SupplierOrderItem supplierOrderItem) {
        SupplierOrderItemDto supplierOrderItemDto = new SupplierOrderItemDto();

        supplierOrderItemDto.setUuid(supplierOrderItem.getUuid());
        supplierOrderItemDto.setMeasureUnite(supplierOrderItem.getMeasureUnite());
        supplierOrderItemDto.setQuantity(supplierOrderItem.getQuantity());
        supplierOrderItemDto.setPriceHT(supplierOrderItem.getPriceHT());
        supplierOrderItemDto.setVatRate(supplierOrderItem.getVatRate());
        supplierOrderItemDto.setProduct(new ProductDto());
        supplierOrderItemDto.setDiscount(supplierOrderItem.getDiscount());

        return supplierOrderItemDto;
    }




}
