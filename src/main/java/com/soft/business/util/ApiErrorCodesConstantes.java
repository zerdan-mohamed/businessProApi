package com.soft.business.util;

public interface ApiErrorCodesConstantes {

    /*
        Global error codes
     */
    String NO_SUCH_ELEMENT_EXCEPTION_CODE = "0001";
    String NO_SUCH_ELEMENT_EXCEPTION_MESSAGE = "No value present";
    String CONSTRAINT_VIOLATION_EXCEPTION_CODE = "0002";

    /*
        Product error codes
        2XXX
     */
    public static final String PRODUCT_EMPTY_NAME_EXCEPTION_CODE = "2000";
    public static final String PRODUCT_EMPTY_NAME_EXCEPTION_MESSAGE = "NAME field is mandatory";


}
