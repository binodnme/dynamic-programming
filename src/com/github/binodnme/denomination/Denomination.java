package com.github.binodnme.denomination;

import java.util.*;

/**
 * @author lftech on 11/2/2016.
 */
public class Denomination {
    private static List<Integer> denominationList;
    private static Map<Integer, Integer> history = new HashMap<>();
    private static Map<Integer, List<Object>> solutionHistory = new HashMap<>();

    public static void main(String[] args) {
        denominationList = new ArrayList<>();
        denominationList.add(1);
        denominationList.add(2);
        denominationList.add(5);
        denominationList.add(8);
        denominationList.add(10);

        System.out.println(getMinDenominationsWithSolution(33));
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

    private static List<Object> getMinDenominationsWithSolution(int amount) {
        if (amount <= 0) {
            List<Object> result = new ArrayList<>();
            result.add(0);
            result.add(new ArrayList<>());
            return result;
        }

        List<Object> value = solutionHistory.get(amount);
        if (value != null) {
            return value;
        }

        Integer minValue = amount;
        List<Integer> minValues = new ArrayList<>();
        List<Object> solutions = new ArrayList<>();
        for (Integer den : denominationList) {
            if (den <= amount) {
                List<Object> calculatedDenominations = getMinDenominationsWithSolution(amount - den);
                Integer calculateMinValue = 1 + (Integer) calculatedDenominations.get(0);

                if(minValue > calculateMinValue) {
                    minValue = calculateMinValue;
                    minValues = new ArrayList<>();
                    minValues.addAll((Collection<? extends Integer>) calculatedDenominations.get(1));
                    minValues.add(den);
                }
            }
        }
        solutionHistory.put(amount, solutions);
        solutions.add(minValue);
        solutions.add(minValues);
        return solutions;
    }

    private static Integer minOf(Integer minValue, int i) {
        return minValue < i ? minValue : i;
    }
}
