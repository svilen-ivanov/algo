package com.buthum.algo;

import com.buhtum.algo.Knapsack;
import com.buhtum.algo.Knapsack.Item;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Test
public class KnapsackTest {
    private final static Logger log = LoggerFactory.getLogger(KnapsackTest.class);

    public static final List<Item> ITEMS = ImmutableList.of(
            Item.of(60, 10),
            Item.of(100, 20),
            Item.of(100, 60),
            Item.of(120, 30));
    public static final int CAPACITY = 50;
    public static final int EXPECTED_MAX_VALUE = 220;

    public void naive01() {
        // http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
        final int naiveMaxValue = Knapsack.naiveRecursive01(ITEMS, CAPACITY);
        log.debug("Naive max value for {} is {}", ITEMS, naiveMaxValue);
        assertThat(naiveMaxValue, is(EXPECTED_MAX_VALUE));
    }

    public void dynamic01DataSet1() {
        // http://www.es.ele.tue.nl/education/5MC10/Solutions/knapsack.pdf
        final int dynamicMaxValue = Knapsack.dynamic01(ITEMS, CAPACITY);
        log.debug("Dynamic max value for {} is {}", ITEMS, dynamicMaxValue);
        assertThat(dynamicMaxValue, is(EXPECTED_MAX_VALUE));
    }

    public void dynamic01DataSet2() {
        // http://www.es.ele.tue.nl/education/5MC10/Solutions/knapsack.pdf
        final int dynamicMaxValue = Knapsack.dynamic01(ImmutableList.of(
                        Item.of(10, 5),
                        Item.of(40, 4),
                        Item.of(30, 6),
                        Item.of(50, 3)),
                18);
        log.debug("Dynamic max value for {}", dynamicMaxValue);
        assertThat(dynamicMaxValue, is(130));
    }

    public void dynamicUnbounded() {
        // http://en.wikipedia.org/wiki/Knapsack_problem#Unbounded_knapsack_problem
        final ImmutableList<Item> items = ImmutableList.of(
                Item.of(90, 3),
                Item.of(160, 7),
                Item.of(15, 2));
        final int maxCapacity = 20;
        final int dynamicMaxValue = Knapsack.dynamicUnbounded(
                items,
                maxCapacity);
        log.debug("Unbounded max value for {} is {}", items, dynamicMaxValue);
        assertThat(dynamicMaxValue, is(555));
    }


}
