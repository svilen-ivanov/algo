package com.buhtum.algo;

import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.*;

public class Knapsack {
    private final static Logger log = LoggerFactory.getLogger(Knapsack.class);

    public static int calculateMaxValue(int capacity, List<Pair<Integer, Integer>> cakeTuples) {
        int m[][] = new int[cakeTuples.size()][capacity];
        for (int i = 0; i < capacity; i++) {
            m[0][i] = 0;
        }
        for (int i = 1; i < cakeTuples.size(); i++) {
            for (int j = 0; i <= capacity; j++) {
                int weight = cakeTuples.get(i).getValue0();
                int value = cakeTuples.get(i).getValue1();
                if (weight <= j) {
                    m[i][j] = max(m[i - 1][j], m[i - 1][j - weight] + value);
                } else {
                    m[i][j] = m[i - 1][j];
                }
            }
        }

        return 1;
    }

    /**
     * Recursive implementation of 0-1 knapsack problem. Terrible O(2^n)
     */
    public static int naiveRecursive01(List<Pair<Integer, Integer>> tuples, int capacity) {
        return naiveRecursive01(tuples, capacity, tuples.size());
    }

    private static int naiveRecursive01(List<Pair<Integer, Integer>> tuples, int capacity, int size) {
        if (capacity == 0 || size == 0) return 0;

        final int index = size - 1;
        final Pair<Integer, Integer> item = tuples.get(index);
        final int value = item.getValue0();
        final int weight = item.getValue1();

        if (weight > capacity) {
            return naiveRecursive01(tuples, capacity, index);
        } else {
            return max(
                    value + naiveRecursive01(tuples, capacity - weight, index),
                    naiveRecursive01(tuples, capacity, index));
        }
    }

    public static int dynamic01(List<Pair<Integer, Integer>> tuples, int capacity) {
        final int size = tuples.size();
        int weights[][] = new int[size][capacity + 1];

        Arrays.fill(weights[0], 0);

        for (int i = 1; i < size; i++) {
            final Pair<Integer, Integer> item = tuples.get(i);
            final int value = item.getValue0();
            final int weight = item.getValue1();

            for (int w = 0; w <= capacity; w++) {
                if (weight <= w) {
                    weights[i][w] = max(weights[i - 1][w], value + weights[i - 1][w - weight]);
                } else {
                    weights[i][w] = weights[i - 1][w];
                }
            }
        }

        return weights[size - 1][capacity];
    }
}
