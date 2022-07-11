package com.soft.business.util;

import java.util.Arrays;
import java.util.List;

public class FunctionalUtils {

    public static boolean checkValidOrderStatus(Integer ItemStatus) {
        // TODO: get arrayList value from dto
        List<Integer> status = Arrays.asList(1, 2, 3);

        return !status.contains(ItemStatus);
    }

    public static boolean checkItemStatusExists(Integer ItemStatus) {
        List<Integer> status = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        return status.contains(ItemStatus);
    }
}
