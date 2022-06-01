package com.soft.business.util;

import com.soft.business.model.SupplierOrder;

import java.time.LocalDateTime;
import java.util.Date;

public class StringUtils {

    public static boolean isEmpty(String value) {
        if (value == null || value.isEmpty() || value.trim().isEmpty()) return true;
        else return false;
    }
}
