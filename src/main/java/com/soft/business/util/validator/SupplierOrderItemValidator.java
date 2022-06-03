package com.soft.business.util.validator;

import com.soft.business.dto.SupplierOrderItemDto;
import com.soft.business.exception.EmptyInputException;
import com.soft.business.exception.FunctionalException;
import com.soft.business.util.ApiErrorCodesConstantes;
import com.soft.business.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class SupplierOrderItemValidator {

    public void createSupplierOrderItemValidator(SupplierOrderItemDto supplierOrderItemDto) {
        if (StringUtils.isEmpty(supplierOrderItemDto.getProduct().getUuid())) {
            throw new EmptyInputException(
                    ApiErrorCodesConstantes.SUPPLIER_ORDER_ITEM_EMPTY_PRODUCT_UUID_EXCEPTION_CODE,
                    ApiErrorCodesConstantes.SUPPLIER_ORDER_ITEM_EMPTY_PRODUCT_UUID_EXCEPTION_MESSAGE
            );
        }
        if (StringUtils.isEmpty(supplierOrderItemDto.getSupplierOrder().getUuid())) {
            throw new EmptyInputException(
                    ApiErrorCodesConstantes.SUPPLIER_ORDER_ITEM_EMPTY_SUPPLIER_ORDER_UUID_EXCEPTION_CODE,
                    ApiErrorCodesConstantes.SUPPLIER_ORDER_ITEM_EMPTY_SUPPLIER_ORDER_UUID_EXCEPTION_MESSAGE
            );
        }

        // add validation for supplierOrderStatus not paid (done)
    }

    public void updateSupplierOrderItemValidator(SupplierOrderItemDto supplierOrderItemDto) {
        if (supplierOrderItemDto.getSupplierOrder() != null) {
            throw new FunctionalException(
                    ApiErrorCodesConstantes.SUPPLIER_ORDER_ITEM_SUPPLIER_ORDER_PROVIDED_EXCEPTION_CODE,
                    ApiErrorCodesConstantes.SUPPLIER_ORDER_ITEM_SUPPLIER_ORDER_PROVIDED_EXCEPTION_MESSAGE
            );
        }
    }
}
