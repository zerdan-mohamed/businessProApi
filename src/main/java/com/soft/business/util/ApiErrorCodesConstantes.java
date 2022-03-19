package com.soft.business.util;

public interface ApiErrorCodesConstantes {

    /*
        Global error codes
     */
    public static final String NO_SUCH_ELEMENT_EXCEPTION_CODE = "0001";
    public static final String NO_SUCH_ELEMENT_EXCEPTION_MESSAGE = "No value present";
    public static final String CONSTRAINT_VIOLATION_EXCEPTION_CODE = "0002";
    public static final String DATE_VIOLATION_EXCEPTION_CODE = "0003";
    public static final String DATE_VIOLATION_EXCEPTION_MESSAGE = "DATE FORMAT IS NOT RESPECTED";

    /*
        Supplier error codes
        1XXX
     */
    public static final String SUPPLIER_EMPTY_NAME_EXCEPTION_CODE = "1000";
    public static final String SUPPLIER_EMPTY_NAME_EXCEPTION_MESSAGE = "NAME field is mandatory";


    /*
        Product error codes
        2XXX
     */
    public static final String PRODUCT_EMPTY_NAME_EXCEPTION_CODE = "2000";
    public static final String PRODUCT_EMPTY_NAME_EXCEPTION_MESSAGE = "NAME field is mandatory";

}
