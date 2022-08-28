package com.soft.business.util;

import java.util.Arrays;
import java.util.List;

public class FunctionalUtils {

    // TODO : get itemStatus from status table
    public static boolean checkValidOrderStatus(Integer ItemStatus) {
        // TODO: get arrayList value from dto
        List<Integer> status = Arrays.asList(1, 2, 3);

        return !status.contains(ItemStatus);
    }

    // TODO : get itemStatus from status table
    public static boolean checkItemStatusExists(Integer itemStatus) {
        List<Integer> status = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        return status.contains(itemStatus);
    }

    public static boolean checkReceptionDeleteRules(String params) {
        // TODO : sprint3 | check invoice status

        return true;
    }


}
