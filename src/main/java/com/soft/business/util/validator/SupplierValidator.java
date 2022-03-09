package com.soft.business.util.validator;

import com.soft.business.dto.SupplierDto;
import com.soft.business.exception.EmptyInputException;
import com.soft.business.util.ApiErrorCodesConstantes;
import com.soft.business.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class SupplierValidator {

    public void createSupplierValidator(SupplierDto supplierDto) {
        if(StringUtils.isEmpty(supplierDto.getName())) {
            throw new EmptyInputException(ApiErrorCodesConstantes.SUPPLIER_EMPTY_NAME_CODE,
                    ApiErrorCodesConstantes.SUPPLIER_EMPTY_NAME_MESSAGE);
        }
    }
}
