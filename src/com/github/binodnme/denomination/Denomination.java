package com.github.binodnme.denomination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lftech on 11/2/2016.
 */
public class Denomination {
    private static List<Integer> denominationList;
    private static Map<Integer, Integer> history = new HashMap<>();

    public static void main(String[] args) {
        denominationList = new ArrayList<>();
        denominationList.add(1);
        denominationList.add(2);
        denominationList.add(5);
        denominationList.add(8);
        denominationList.add(10);

        System.out.println(getMinDenominationsWithMemoization(500));
    }

    private static Integer getMinDenominations(int amount) {
        if (amount <= 0) {
            return 0;
        }

        Integer minValue = amount;
        for (Integer den : denominationList) {
            if (den <= amount) {
                minValue = minOf(minValue, 1 + getMinDenominations(amount - den));
            }
        }

        return minValue;
    }

    private static Integer getMinDenominationsWithMemoization(int amount) {
        if (amount <= 0) {
            return 0;
        }

        Integer value = history.get(amount);
        if (value != null) {
            return value;
        }

        Integer minValue = amount;
        for (Integer den : denominationList) {
            if (den <= amount) {
                minValue = minOf(minValue, 1 + getMinDenominationsWithMemoization(amount - den));
            }
        }
        history.put(amount, minValue);
        return minValue;
    }

    private static Integer minOf(Integer minValue, int i) {
        return minValue < i ? minValue : i;
    }
}
