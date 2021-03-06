package com.buhtum.algo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.max;

public class Knapsack {
    private final static Logger log = LoggerFactory.getLogger(Knapsack.class);

    /**
     * Recursive implementation of 0-1 knapsack problem. Terrible O(2^n)
     */
    public static int naiveRecursive01(List<Item> items, int capacity) {
        return naiveRecursive01(items, capacity, items.size());
    }

    private static int naiveRecursive01(List<Item> items, int capacity, int size) {
        if (capacity == 0 || size == 0) return 0;

        final int index = size - 1;
        final Item item = items.get(index);

        if (item.getWeight() > capacity) {
            return naiveRecursive01(items, capacity, index);
        } else {
            return max(
                    item.getValue() + naiveRecursive01(items, capacity - item.getWeight(), index),
                    naiveRecursive01(items, capacity, index));
        }
    }

    /**
     * Dynamic programming implementation of 0-1 knapsack problem. O(nW) complexity
     */
    public static int dynamic01(List<Item> tuples, int capacity) {
        final int size = tuples.size();
        final int values[][] = new int[size + 1][capacity + 1];

        Arrays.fill(values[0], 0);

        for (int itemIndex = 1; itemIndex <= size; itemIndex++) {
            final Item item = tuples.get(itemIndex - 1);

            for (int weight = 0; weight <= capacity; weight++) {
                if (item.getWeight() <= weight) {
                    values[itemIndex][weight] = max(
                            item.getValue() + values[itemIndex - 1][weight - item.getWeight()],
                            values[itemIndex - 1][weight]);
                } else {
                    values[itemIndex][weight] = values[itemIndex - 1][weight];
                }
            }
        }

        printArray(values);

        int remainingCapacity = capacity;
        for (int itemIndex = size; itemIndex > 0; itemIndex--) {
            if (values[itemIndex][remainingCapacity] != values[itemIndex - 1][remainingCapacity]) {
                final Item item = tuples.get(itemIndex - 1);

                log.debug("Picked item {}: {}", itemIndex, item);
                remainingCapacity -= item.getWeight();
            }
        }

        return values[size][capacity];
    }

    /**
     * Dynamic implementation of unbounded knapsack problem
     */
    public static int dynamicUnbounded(List<Item> tuples, int capacity) {
        int[] values = new int[capacity + 1];
        int[][] countItemsPerWeight = new int[capacity + 1][tuples.size()];
        Arrays.fill(values, 0);

        for (int w = 1; w <= capacity; w++) {
            values[w] = 0;
            for (int i = 0; i < tuples.size(); i++) {
                Item item = tuples.get(i);
                if (item.getWeight() <= w) {
                    final int newValue = item.getValue() + values[w - item.getWeight()];
                    if (values[w] < newValue) {
                        values[w] = newValue;
                        final int[] items = countItemsPerWeight[w - item.getWeight()];
                        countItemsPerWeight[w] = Arrays.copyOf(items, items.length);
                        countItemsPerWeight[w][i]++;
                    }
                }
            }
        }

        log.debug("Knapsack items:");
        log.debug("===============");
        int[] maxCapacityItems = countItemsPerWeight[capacity];
        for (int i = 0; i < maxCapacityItems.length; i++) {
            int count = maxCapacityItems[i];
            log.debug("\t{} x {} (item #{})", count, tuples.get(i), i);
        }


        return values[capacity];
    }

    private static void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < arr[i].length; j++) {
                sb.append(String.format("%3d", arr[i][j])).append(" ");
            }
            log.debug("{}: {}", i, sb.toString());
        }
    }

    public static class Item {
        private final int value;
        private final int weight;

        private Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }

        public static Item of(int value, int weight) {
            return new Item(value, weight);
        }

        public int getValue() {
            return value;
        }

        public int getWeight() {
            return weight;
        }

        public String toString() {
            return "[weight: " + weight + "; value: " + value + "]";
        }

        @Override
        public boolean equals(Object o) {
            return EqualsBuilder.reflectionEquals(this, o);
        }

        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this);
        }
    }
}
