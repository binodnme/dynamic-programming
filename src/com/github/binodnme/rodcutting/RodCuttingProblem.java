package com.github.binodnme.rodcutting;

import java.util.*;

/**
 * Created by Binod Shrestha
 */
public class RodCuttingProblem {
    private static Map<Integer, Integer> profitMap = new HashMap<>();
    private static Map<Integer, Integer> history = new HashMap<>();
    private static Map<Integer, List<Object>> historyTest = new HashMap<>();

    public static void main(String[] args) {
        initMap();
        System.out.println(memoizedCutRod(500, profitMap));
    }

    //naive solution
    private static Integer cutRod(Integer length, Map<Integer, Integer> map) {
        if (length == 0) return 0;

        Integer revenue = -1;
        for (int i = 1; i <= length; i++) {
            Integer profit = map.get(i);
            if (profit != null) {
                revenue = maxOf(revenue, profit + cutRod(length - i, map));
            }
        }
        return revenue;
    }

    //using dynamic programming
    private static Integer memoizedCutRod(Integer length, Map<Integer, Integer> map) {
        if (length == 0) return 0;

        Integer storedData = history.get(length);
        if (storedData != null) {
            return storedData;
        }

        Integer revenue = -1;
        for (int i = 1; i <= length; i++) {
            Integer profit = map.get(i);
            if (profit != null) {
                revenue = maxOf(revenue, profit + memoizedCutRod(length - i, map));
            }
        }
        history.put(length, revenue);
        return revenue;
    }

    //using dynamic programming
    private static List<Object> memoizedCutRodWithSolution(Integer length, Map<Integer, Integer> map) {
        if (length == 0) {
            List<Object> list = new ArrayList<>();
            list.add(0);
            list.add(new ArrayList<>());
            return list;
        }

        List<Object> storedData = historyTest.get(length);
        if (storedData != null) {
            return storedData;
        }

        Integer revenue = -1;
        List<Integer> path = new ArrayList<>();
        List<Object> solution = new ArrayList<>();

        for (int i = 1; i <= length; i++) {
            Integer profit = map.get(i);
            if (profit != null) {
                List<Object> calculatedSolution = memoizedCutRodWithSolution(length - i, map);
                Integer calculatedRevenue = profit + (Integer) calculatedSolution.get(0);

                if (calculatedRevenue > revenue) {
                    revenue = calculatedRevenue;
                    path = new ArrayList<>();
                    path.addAll((Collection<? extends Integer>) calculatedSolution.get(1));
                    path.add(i);
                }
            }
        }
        historyTest.put(length, solution);
        solution.add(revenue);
        solution.add(path);
        return solution;

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
        profitMap.put(6, 11);
        profitMap.put(7, 17);
        profitMap.put(8, 20);
        profitMap.put(9, 24);
        profitMap.put(10, 30);
    }
}