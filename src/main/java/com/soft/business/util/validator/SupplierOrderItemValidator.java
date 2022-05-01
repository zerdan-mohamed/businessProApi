package com.soft.business.util.validator;

import com.soft.business.dto.SupplierOrderDto;
import com.soft.business.dto.SupplierOrderItemDto;
import com.soft.business.exception.EmptyInputException;
import com.soft.business.util.ApiErrorCodesConstantes;
import com.soft.business.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class SupplierOrderItemValidator {

    public void createSupplierOrderItemValidator(SupplierOrderItemDto supplierOrderItemDto) {
        if (StringUtils.isEmpty(supplierOrderItemDto.getProduct().getName())) {
            throw new EmptyInputException(
                    ApiErrorCodesConstantes.SUPPLIER_ORDER_ITEM_EMPTY_PRODUCT_NAME_EXCEPTION_CODE,
                    ApiErrorCodesConstantes.SUPPLIER_ORDER_ITEM_EMPTY_PRODUCT_NAME_EXCEPTION_MESSAGE
            );
        }
        if (StringUtils.isEmpty(supplierOrderItemDto.getSupplierOrder().getSupplierOrderNumber())) {
            throw new EmptyInputException(
                    ApiErrorCodesConstantes.SUPPLIER_ORDER_ITEM_EMPTY_ORDER_NUMBER_EXCEPTION_CODE,
                    ApiErrorCodesConstantes.SUPPLIER_ORDER_ITEM_EMPTY_ORDER_NUMBER_EXCEPTION_MESSAGE
            );
        }

        // add validation for supplierOrderStatus not paid (done)
    }
}
