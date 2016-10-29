package com.github.binodnme.fib;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Binod Shrestha
 */
public class Fibonacci {
    private static Map<Integer, Long> map = new HashMap<>();


    public static long fib(int n) {
        if (n <= 2) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public static long fib1(int n) {
        long a = 1;
        long b = 1;

        for (int i = 3; i <= n; i++) {
            long temp = b;
            b = a + b;
            a = temp;
        }
        return b;
    }

    public static long fib2(int n) {
        Long result = map.get(n);
        if (result != null) return result;
        map.put(n, fib2(n - 1) + fib2(n - 2));
        return map.get(n);
    }

    public static void main(String[] args) {
        map.put(1, 1L);
        map.put(2, 1L);
        System.out.println(fib2(80));
    }
}