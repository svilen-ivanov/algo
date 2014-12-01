package com.buhtum.algo.test;

import com.buhtum.algo.CoinChange;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CoinChangeTest {
    private final static Logger log = LoggerFactory.getLogger(CoinChangeTest.class);
    @Test
    public void testRecursiveDataSet1() throws Exception {
        int[] coins = new int[]{1, 2, 3};
        int sum = 4;
        int numWays = CoinChange.recursive(coins, sum, coins.length);
        assertThat(numWays, is(4));
        log.debug("Number of ways to represent {} with {} coins is {}", sum, coins, numWays);
    }

    @Test
    public void testRecursiveDataSet2() throws Exception {
        int[] coins = new int[]{2, 5, 3, 6};
        int sum = 10;
        int numWays = CoinChange.recursive(coins, sum, coins.length);
        assertThat(numWays, is(5));
        log.debug("Number of ways to represent {} with {} coins is {}", sum, coins, numWays);
    }

    @Test
    public void testRecursiveDataSet3() throws Exception {
        int[] coins = new int[]{1, 6, 7};
        int sum = 15;
        int numWays = CoinChange.recursive(coins, sum, coins.length);
        assertThat(numWays, is(6));
        log.debug("Number of ways to represent {} with {} coins is {}", sum, coins, numWays);
    }
}
