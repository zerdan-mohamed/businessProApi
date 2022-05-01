package com.soft.business.util;


import com.soft.business.dto.SupplierOrderDto;
import com.soft.business.model.SupplierOrder;

import java.util.Date;

public class StringUtils {

    public static boolean isEmpty(String value) {
        if (value == null || value.isEmpty() || value.trim().isEmpty()) return true;
        else return false;
    }

    public static String generateOrderCode(String prefix, SupplierOrder supplierOrder) {
        Date  creationDate = supplierOrder.getCreationDate();
        Integer supplierOrderNumber = supplierOrder.getSupplierOrderNumber();
        String orderYear = String.valueOf(creationDate.getYear());

        String generatedOrderCode = prefix + orderYear + "/" + supplierOrderNumber;

        return generatedOrderCode;
    }
}
