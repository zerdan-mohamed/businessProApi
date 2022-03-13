package com.soft.business.util.validator;

import com.soft.business.dto.ProductFamilyDto;
import com.soft.business.exception.EmptyInputException;
import com.soft.business.util.ApiErrorCodesConstantes;
import com.soft.business.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductFamilyValidator {

    public void createProductFamilyValidator(ProductFamilyDto productFamilyDto) {
        if(StringUtils.isEmpty(productFamilyDto.getName())) {
            throw new EmptyInputException(
                    ApiErrorCodesConstantes.PRODUCT_EMPTY_NAME_EXCEPTION_CODE,
                    ApiErrorCodesConstantes.PRODUCT_EMPTY_NAME_EXCEPTION_MESSAGE
            );
        }
    }
}
