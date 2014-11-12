package com.buhtum.algo;

import org.javatuples.Pair;

import java.util.List;

public class Knapsack {
    private final int capacity;

    public Knapsack(int capacity) {
        this.capacity = capacity;
    }

    public int calculateMaxValue(List<Pair<Integer, Integer>> cakeTuples) {
        int m[][] = new int[cakeTuples.size()][capacity];
        for (int i = 0; i < capacity; i++) {
            m[0][i] = 0;
        }
        for (int i = 1; i < cakeTuples.size(); i++) {
            for (int j = 0; i <= capacity; j++) {
                int weight = cakeTuples.get(i).getValue0();
                int value = cakeTuples.get(i).getValue1();
                if (weight <= j) {
                    m[i][j] = Math.max(m[i - 1][j], m[i - 1][j - weight] + value);
                } else {
                    m[i][j] = m[i - 1][j];
                }
            }
        }

        return 1;
    }
}
