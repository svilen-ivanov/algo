import com.buhtum.algo.Knapsack;
import com.google.common.collect.ImmutableList;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class KnapsackTest {
    private final static Logger log = LoggerFactory.getLogger(KnapsackTest.class);

    public void test() {
        List<Pair<Integer, Integer>> cakeTuples = ImmutableList.of(
                Pair.with(7, 160),
                Pair.with(3, 90),
                Pair.with(2, 15));

        int capacity = 20;

        Knapsack knapsack = new Knapsack(capacity);
        log.debug("Max value for {} is {}", cakeTuples, knapsack.calculateMaxValue(cakeTuples));
    }
}
