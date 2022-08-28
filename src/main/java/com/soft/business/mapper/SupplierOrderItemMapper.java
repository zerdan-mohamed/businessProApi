package com.soft.business.mapper;

import com.soft.business.dto.ProductDto;
import com.soft.business.dto.SupplierOrderDto;
import com.soft.business.dto.SupplierOrderItemDto;
import com.soft.business.model.*;
import com.soft.business.repository.ProductRepository;
import com.soft.business.repository.SupplierOrderRepository;
import com.soft.business.util.FunctionalUtils;
import com.soft.business.util.SupplierOrderConstants;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupplierOrderItemMapper {

    private final ProductRepository productRepository;
    private final SupplierOrderRepository supplierOrderRepository;

    public SupplierOrderItemMapper(ProductRepository productRepository,
                                   SupplierOrderRepository supplierOrderRepository) {
        this.productRepository = productRepository;
        this.supplierOrderRepository = supplierOrderRepository;
    }

    public SupplierOrderItemDto makeDtoFromSupplierOrderItem(int orgId, SupplierOrderItem supplierOrderItem) {
        SupplierOrderItemDto supplierOrderItemDto = new SupplierOrderItemDto();

        supplierOrderItemDto.setUuid(supplierOrderItem.getUuid());
        supplierOrderItemDto.setQuantity(supplierOrderItem.getQuantity());
        supplierOrderItemDto.setRemainingQuantity(supplierOrderItem.getRemainingQuantity());
        supplierOrderItemDto.setMeasureUnite(supplierOrderItem.getMeasureUnite());
        supplierOrderItemDto.setVatRate(supplierOrderItem.getVatRate());
        supplierOrderItemDto.setPriceHT(supplierOrderItem.getPriceHT());
        supplierOrderItemDto.setDiscount(supplierOrderItem.getDiscount());
        supplierOrderItemDto.setSupplierOrderItemStatus(supplierOrderItem.getSupplierOrderItemStatus());

        if (supplierOrderItem.getProduct() != null) {
            ProductDto productDto = new ProductDto();
            productDto.setUuid(supplierOrderItem.getProduct().getUuid());
            productDto.setName(supplierOrderItem.getProduct().getName());

            supplierOrderItemDto.setProduct(productDto);
        }

        if (supplierOrderItem.getSupplierOrder() != null) {
            SupplierOrderDto supplierOrderDto = new SupplierOrderDto();
            supplierOrderDto.setUuid(supplierOrderItem.getSupplierOrder().getUuid());
            supplierOrderDto.setSupplierOrderStatus(supplierOrderItem.getSupplierOrder().getSupplierOrderStatus());

            supplierOrderItemDto.setSupplierOrder(supplierOrderDto);
        }

        return supplierOrderItemDto;
    }

    public SupplierOrderItem makeSupplierOrderItemFromDto(
            int orgId, SupplierOrderItemDto supplierOrderItemDto, SupplierOrder supplierOrder) {
        SupplierOrderItem supplierOrderItem = new SupplierOrderItem();

        supplierOrderItem.setUuid(UUID.randomUUID().toString());
        supplierOrderItem.setQuantity(supplierOrderItemDto.getQuantity());
        supplierOrderItem.setRemainingQuantity(supplierOrderItemDto.getQuantity());
        supplierOrderItem.setMeasureUnite(supplierOrderItemDto.getMeasureUnite());
        supplierOrderItem.setPriceHT(supplierOrderItemDto.getPriceHT());
        supplierOrderItem.setVatRate(supplierOrderItemDto.getVatRate());
        supplierOrderItem.setDiscount(supplierOrderItemDto.getDiscount());
        supplierOrderItem.setSupplierOrderItemStatus(SupplierOrderConstants.CREATED);
        supplierOrderItem.setOrgId(orgId);
        supplierOrderItem.setSupplierOrder(supplierOrder);
        if (supplierOrderItemDto.getProduct() != null) {
            Optional<Product> optionalProduct =
                    productRepository.findByUuidAndOrgId(
                            supplierOrderItemDto.getProduct().getUuid(), orgId
                    );

            if (optionalProduct.isPresent()) supplierOrderItem.setProduct(optionalProduct.get());;
        }
        return supplierOrderItem;
    }

    public SupplierOrderItem updateSupplierOrderItem(
            int orgId,
            SupplierOrderItemDto supplierOrderItemDto,
            SupplierOrderItem supplierOrderItemDb) {
        SupplierOrderItem supplierOrderItem = new SupplierOrderItem();

        Integer itemStatus = supplierOrderItemDto.getSupplierOrderItemStatus();

        // FIXME : re-analyse
        Integer orderStatus = supplierOrderItemDb.getSupplierOrder().getSupplierOrderStatus();
        boolean StatusExists = FunctionalUtils.checkItemStatusExists(itemStatus);
        boolean validOrderStatus = FunctionalUtils.checkValidOrderStatus(orderStatus);

        if(supplierOrderItemDto.getQuantity() != null)
            supplierOrderItem.setQuantity(supplierOrderItemDto.getQuantity());
        else
            supplierOrderItem.setQuantity(supplierOrderItemDb.getQuantity());

        if(supplierOrderItemDto.getRemainingQuantity() != null)
            supplierOrderItem.setRemainingQuantity(supplierOrderItemDto.getRemainingQuantity());
        else
            supplierOrderItem.setRemainingQuantity(supplierOrderItemDb.getRemainingQuantity());

        if(supplierOrderItemDto.getPriceHT() != null)
            supplierOrderItem.setPriceHT(supplierOrderItemDto.getPriceHT());
        else
            supplierOrderItem.setPriceHT(supplierOrderItemDb.getPriceHT());

        if(supplierOrderItemDto.getMeasureUnite() != null)
            supplierOrderItem.setMeasureUnite(supplierOrderItemDto.getMeasureUnite());
        else
            supplierOrderItem.setMeasureUnite(supplierOrderItemDb.getMeasureUnite());

        if(supplierOrderItemDto.getVatRate() != null)
            supplierOrderItem.setVatRate(supplierOrderItemDto.getVatRate());
        else
            supplierOrderItem.setVatRate(supplierOrderItemDb.getVatRate());

        if(supplierOrderItemDto.getDiscount() != null)
            supplierOrderItem.setDiscount(supplierOrderItemDto.getDiscount());
        else
            supplierOrderItem.setDiscount(supplierOrderItemDb.getDiscount());

        // TODO:  check is item related to a reception (receipt form)
        if (itemStatus != null && StatusExists && validOrderStatus)
            supplierOrderItem.setSupplierOrderItemStatus(itemStatus);
        else
            supplierOrderItem.setSupplierOrderItemStatus(supplierOrderItemDb.getSupplierOrderItemStatus());

        if (supplierOrderItemDto.getProduct() != null) {
            Optional<Product> product =
                    productRepository.findByUuidAndOrgId(
                            supplierOrderItemDto.getProduct().getUuid(), orgId
                    );

            if (product.isPresent()) supplierOrderItem.setProduct(product.get());
        } else if (supplierOrderItemDb.getProduct() != null) {
            supplierOrderItem.setProduct(supplierOrderItemDb.getProduct());
        }

        if(supplierOrderItemDb.getSupplierOrder() != null)
            supplierOrderItem.setSupplierOrder(supplierOrderItemDb.getSupplierOrder());

        supplierOrderItem.setIdSupplierOrderItem(supplierOrderItemDb.getIdSupplierOrderItem());
        supplierOrderItem.setUuid(supplierOrderItemDb.getUuid());

        return supplierOrderItem;
    }
}
