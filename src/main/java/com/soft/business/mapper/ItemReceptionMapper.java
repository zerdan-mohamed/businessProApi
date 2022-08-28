package com.soft.business.mapper;

import com.soft.business.dto.ItemReceptionDto;
import com.soft.business.model.ItemReception;
import com.soft.business.model.ItemReceptionId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class ItemReceptionMapper {

    public ItemReception makeItemReceptionFromDto(int orgId, ItemReceptionDto itemReceptionDto) {
        ItemReception itemReception =new ItemReception();

        ItemReceptionId itemReceptionId = new ItemReceptionId();
        itemReceptionId.setReceptionId(itemReceptionDto.getReceptionId());
        itemReceptionId.setSupplierOrderItemId(itemReceptionDto.getSupplierOrderItemId());

        itemReception.setReceptionId(itemReceptionDto.getReceptionId());
        itemReception.setSupplierOrderItemId(itemReceptionDto.getSupplierOrderItemId());

        itemReception.setOrgId(orgId);
        itemReception.setSupplierOrderId(itemReceptionDto.getSupplierOrderId());
        itemReception.setUuid(UUID.randomUUID().toString());
        itemReception.setQuantity(itemReceptionDto.getQuantity());
        itemReception.setDate(LocalDateTime.now());

        return  itemReception;
    }

    public ItemReceptionDto makeDtoFromItemReception(ItemReception itemReception) {

        return null;
    }

    public ItemReception updateItemReception(
            int orgId, ItemReceptionDto itemReceptionDto, ItemReception dbItemReception
    ) {

        return null;
    }
}
