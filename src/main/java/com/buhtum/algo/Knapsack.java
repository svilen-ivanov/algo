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

    public static int dynamic01(List<Item> tuples, int capacity) {
        final int size = tuples.size();
        int weights[][] = new int[size][capacity + 1];
        boolean keep[][] = new boolean[size][capacity + 1];

        Arrays.fill(weights[0], 0);

        for (int i = 1; i < size; i++) {
            final Item item = tuples.get(i);

            for (int w = 0; w <= capacity; w++) {
                if (item.getWeight() <= w) {
                    int existingMax = weights[i - 1][w];
                    int newMax = item.getValue() + weights[i - 1][w - item.getWeight()];
                    if (existingMax < newMax) {
                        weights[i][w] = newMax;
                        keep[i][w] = true;
                    } else {
                        keep[i][w] = false;
                        weights[i][w] = existingMax;
                    }
                } else {
                    weights[i][w] = weights[i - 1][w];
                    keep[i][w] = false;
                }
            }
        }

        for (int i = size - 1, currCapacity = capacity; i >= 0; i--) {
            if (keep[i][currCapacity]) {
                final Item item = tuples.get(i);

                log.debug("Included: " + item);
                currCapacity -= item.getWeight();
            }
        }

        return weights[size - 1][capacity];
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
