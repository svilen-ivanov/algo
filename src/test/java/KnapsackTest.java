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

    public void naive() {
        // http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
        final int naiveMaxValue = Knapsack.naiveRecursive01(ITEMS, CAPACITY);
        log.debug("Naive max value for {} is {}", ITEMS, naiveMaxValue);
        assertThat(naiveMaxValue, is(EXPECTED_MAX_VALUE));
    }


    public void dynamic() {
        // http://www.es.ele.tue.nl/education/5MC10/Solutions/knapsack.pdf
        final int dynamicMaxValue = Knapsack.dynamic01(ITEMS, CAPACITY);
        log.debug("Dynamic max value for {} is {}", ITEMS, dynamicMaxValue);
        assertThat(dynamicMaxValue, is(EXPECTED_MAX_VALUE));
    }
}
