import com.buhtum.algo.Knapsack;
import com.google.common.collect.ImmutableList;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Test
public class KnapsackTest {
    private final static Logger log = LoggerFactory.getLogger(KnapsackTest.class);

    public static final List<Pair<Integer, Integer>> ITEMS = ImmutableList.of(
            Pair.with(60, 10),
            Pair.with(100, 20),
            Pair.with(100, 60),
            Pair.with(120, 30));
    public static final int CAPACITY = 50;
    public static final int EXPECTED_MAX_VALUE = 220;

    public void naive() {
        // http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
        final int naiveMaxValue = Knapsack.naiveRecursive01(ITEMS, CAPACITY);
        log.debug("Naive max value for {} is {}", ITEMS, naiveMaxValue);
        assertThat(naiveMaxValue, is(EXPECTED_MAX_VALUE));
    }

    
    public void dynamic() {
        final int dynamicMaxValue = Knapsack.dynamic01(ITEMS, CAPACITY);
        log.debug("Dynamic max value for {} is {}", ITEMS, dynamicMaxValue);
        assertThat(dynamicMaxValue, is(EXPECTED_MAX_VALUE));
    }
}
