package com.soft.business.util;

public interface ApiErrorCodesConstantes {

    // ### Global error codes ###
    public static final String NO_SUCH_ELEMENT_EXCEPTION_CODE = "0001";
    public static final String NO_SUCH_ELEMENT_EXCEPTION_MESSAGE = "No value present";
    public static final String CONSTRAINT_VIOLATION_EXCEPTION_CODE = "0002";
    public static final String DATE_VIOLATION_EXCEPTION_CODE = "0003";
    public static final String DATE_VIOLATION_EXCEPTION_MESSAGE = "DATE FORMAT IS NOT RESPECTED";

    // ### Supplier error codes 1XXX ###
    public static final String SUPPLIER_EMPTY_NAME_EXCEPTION_CODE = "1000";
    public static final String SUPPLIER_EMPTY_NAME_EXCEPTION_MESSAGE = "NAME field is mandatory";

    // ### Product error codes 2XXX ###
    public static final String PRODUCT_EMPTY_NAME_EXCEPTION_CODE = "2000";
    public static final String PRODUCT_EMPTY_NAME_EXCEPTION_MESSAGE = "NAME field is mandatory";

    // ### Supplier order error codes 3XXX ###
    public static final String SUPPLIER_ORDER_EMPTY_ORDER_CODE_EXCEPTION_CODE = "3000";
    public static final String SUPPLIER_ORDER_EMPTY_ORDER_CODE_EXCEPTION_MESSAGE = "supplier_order_number field is mandatory";
    public static final String SUPPLIER_ORDER_ITEM_EMPTY_PRODUCT_UUID_EXCEPTION_CODE = "3001";
    public static final String SUPPLIER_ORDER_ITEM_EMPTY_PRODUCT_UUID_EXCEPTION_MESSAGE = "product uuid field is mandatory";
    public static final String SUPPLIER_ORDER_ITEM_EMPTY_SUPPLIER_ORDER_UUID_EXCEPTION_CODE = "3002";
    public static final String SUPPLIER_ORDER_ITEM_EMPTY_SUPPLIER_ORDER_UUID_EXCEPTION_MESSAGE = "supplierOrder uuid field is mandatory";
    public static final String SUPPLIER_ORDER_ITEM_DELETE_STATUS_PAID_EXCEPTION_CODE = "3003";
    public static final String SUPPLIER_ORDER_ITEM_DELETE_STATUS_PAID_EXCEPTION_MESSAGE = "Can't delete a paid Item";

    public static final String SUPPLIER_ORDER_ITEM_SUPPLIER_ORDER_PROVIDED_EXCEPTION_CODE = "3004";
    public static final String SUPPLIER_ORDER_ITEM_SUPPLIER_ORDER_PROVIDED_EXCEPTION_MESSAGE = "Can't update the supplier of an existing item";


    // ### reception error codes 4XXX ###
    public static final String RECEPTION_EMPTY_ORDER_CODE_EXCEPTION_CODE = "4000";
    public static final String RECEPTION_EMPTY_ORDER_CODE_EXCEPTION_MESSAGE = "reception uuid field is mandatory";
    public static final String RECEPTION_ITEM_EMPTY_ORDER_CODE_EXCEPTION_CODE = "4001";
    public static final String RECEPTION_ITEM_EMPTY_ORDER_CODE_EXCEPTION_MESSAGE = "reception item is mandatory to create reception";


}
