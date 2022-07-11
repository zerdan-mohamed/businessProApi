package com.soft.business.util;

public interface SupplierOrderConstants {

    // TODO: i think that an array is best to handle our cases of object status
    public static final Integer CREATED = 1;
    public static final Integer IN_DELIVERY = 2;
    public static final Integer DELIVERED = 3;
    public static final Integer PAID = 4;
    public static final Integer INVOICED = 5;
    public static final Integer CANCELED = 6;
    public static final Integer DELETED = 7;
}
