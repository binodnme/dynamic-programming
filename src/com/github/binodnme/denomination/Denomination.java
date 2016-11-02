package com.github.binodnme.denomination;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lftech on 11/2/2016.
 */
public class Denomination {
    private static List<Integer> denominationList;

    public static void main(String[] args) {
        denominationList = new ArrayList<>();
        denominationList.add(1);
        denominationList.add(2);
        denominationList.add(5);
        denominationList.add(8);
        denominationList.add(10);

        System.out.println(getMinDenominations(27));
    }

    private static Integer getMinDenominations(int amount) {
        if( amount <= 0){
            return 0;
        }

        Integer minValue = amount;
        for (Integer den : denominationList){
            if (den <= amount){
                minValue = minOf(minValue, 1 + getMinDenominations(amount - den));
            }
        }

        return minValue;
    }

    private static Integer minOf(Integer minValue, int i) {
        return minValue < i ? minValue : i;
    }
}
