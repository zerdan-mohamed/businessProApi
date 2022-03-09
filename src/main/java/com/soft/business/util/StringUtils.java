package com.soft.business.util;

import org.springframework.stereotype.Component;

@Component
public class StringUtils {

    public static boolean isEmpty(String value) {
        if (value == null || value.isEmpty() || value.trim().isEmpty()) return true;
        else return false;
    }
}
