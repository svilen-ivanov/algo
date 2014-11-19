package com.buhtum.algo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoinChange {
    private final static Logger log = LoggerFactory.getLogger(CoinChange.class);

    public static int recursive(int[] coins, int sum, int subSet) {
        if (sum == 0) return 1;
        if (sum < 0) return 0;
        if (sum > 0 && subSet < 1) return 0;
        return recursive(coins, sum, subSet - 1) + recursive(coins, sum - coins[subSet - 1], subSet);
    }
}
