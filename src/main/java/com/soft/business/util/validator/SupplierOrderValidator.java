package com.soft.business.util.validator;

import com.soft.business.exception.EmptyInputException;
import com.soft.business.model.SupplierOrder;
import com.soft.business.util.ApiErrorCodesConstantes;
import com.soft.business.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class SupplierOrderValidator {

    public void createSupplierOrderValidator(SupplierOrder supplierOrder) {
        if(StringUtils.isEmpty(supplierOrder.getSupplierOrderNumber()))
            throw new EmptyInputException(
                    ApiErrorCodesConstantes.SUPPLIER_ORDER_EMPTY_ORDER_CODE_EXCEPTION_CODE,
                    ApiErrorCodesConstantes.SUPPLIER_ORDER_EMPTY_ORDER_CODE_EXCEPTION_MESSAGE
            );
    }
}
