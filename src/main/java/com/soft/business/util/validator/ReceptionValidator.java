package com.soft.business.util.validator;

import com.soft.business.dto.ItemReceptionDto;
import com.soft.business.dto.ReceptionDto;
import com.soft.business.exception.EmptyInputException;
import com.soft.business.model.ItemReception;
import com.soft.business.model.Reception;
import com.soft.business.util.ApiErrorCodesConstantes;
import com.soft.business.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ReceptionValidator {
    public void createReceptionvalidator(Reception reception, Set<ItemReceptionDto> receptionItems) {
        if(StringUtils.isEmpty(reception.getSupplierId().toString())) {
            throw new EmptyInputException(
                    ApiErrorCodesConstantes.RECEPTION_EMPTY_ORDER_CODE_EXCEPTION_CODE,
                    ApiErrorCodesConstantes.RECEPTION_EMPTY_ORDER_CODE_EXCEPTION_MESSAGE
            );
        }

        if(receptionItems.isEmpty()) {
            throw new EmptyInputException(
                    ApiErrorCodesConstantes.RECEPTION_ITEM_EMPTY_ORDER_CODE_EXCEPTION_CODE,
                    ApiErrorCodesConstantes.RECEPTION_ITEM_EMPTY_ORDER_CODE_EXCEPTION_MESSAGE
            );
        }

        // loops receptionItems to validate every item
    }
}
