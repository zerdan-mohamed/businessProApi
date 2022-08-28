package com.soft.business.util.validator;

import com.soft.business.dto.ProductDto;
import com.soft.business.exception.DateFormatException;
import com.soft.business.exception.EmptyInputException;
import com.soft.business.util.ApiErrorCodesConstantes;
import com.soft.business.util.Constantes;
import com.soft.business.util.StringUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class ProductValidator {
    public void createProductValidator(ProductDto productDto) {
        if(StringUtils.isEmpty(productDto.getName())) {
            throw new EmptyInputException(
                    ApiErrorCodesConstantes.PRODUCT_EMPTY_NAME_EXCEPTION_CODE,
                    ApiErrorCodesConstantes.PRODUCT_EMPTY_NAME_EXCEPTION_MESSAGE
            );
        }

        // false when update
        if(!StringUtils.isEmpty(productDto.getInitialStockDate())) {
            try {
                Constantes.dateFormatter.parse(productDto.getInitialStockDate());
            } catch (ParseException e) {
                throw new DateFormatException(
                        ApiErrorCodesConstantes.DATE_VIOLATION_EXCEPTION_CODE,
                        ApiErrorCodesConstantes.DATE_VIOLATION_EXCEPTION_MESSAGE
                );
            }
        }
    }
}
