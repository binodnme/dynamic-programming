package com.github.binodnme.rodcutting;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Binod Shrestha
 */
public class RodCuttingProblem {
    private static Map<Integer, Integer> profitMap = new HashMap<>();
    private static Map<Integer, Integer> history = new HashMap<>();

    public static void main(String[] args) {
        initMap();
        System.out.println(memoizedCutRod(35, profitMap));
    }

    private static Integer cutRod(Integer length, Map<Integer, Integer> map) {
        if (length == 0) return 0;

        Integer revenue = -1;
        for (int i = 0; i <= length; i++) {
            Integer profit = map.get(i);
            if (profit != null) {
                revenue = maxOf(revenue, profit + cutRod(length - i, map));
            }
        }
        return revenue;
    }

    private static Integer memoizedCutRod(Integer length, Map<Integer, Integer> map) {
        if (length == 0) return 0;

        Integer storedData = history.get(length);
        if (storedData != null) {
            return storedData;
        }

        Integer revenue = -1;
        for (int i = 0; i <= length; i++) {
            Integer profit = map.get(i);
            if (profit != null) {
                revenue = maxOf(revenue, profit + memoizedCutRod(length - i, map));
            }
        }
        history.put(length, revenue);
        return revenue;
    }

    private static Integer maxOf(Integer revenue, int i) {
        return revenue > i ? revenue : i;
    }

    private static void initMap() {
        profitMap.put(1, 1);
        profitMap.put(2, 5);
        profitMap.put(3, 8);
        profitMap.put(4, 9);
        profitMap.put(5, 10);
        profitMap.put(6, 17);
        profitMap.put(7, 17);
        profitMap.put(8, 20);
        profitMap.put(9, 24);
        profitMap.put(10, 30);
    }
}