package com.soft.business.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class FunctionalUtils {

    public static boolean isValidStatus(Set<Integer> moduleStatus, Integer itemStatus) {

        return moduleStatus.contains(itemStatus);
    }

    // @Deprecated
    public static boolean checkItemStatusExists(Integer itemStatus) {
        List<Integer> status = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        return status.contains(itemStatus);
    }

    // TODO : [sprint3] Check invoice status
    public static boolean checkReceptionDeleteRules(String params) {

        return true;
    }


}
