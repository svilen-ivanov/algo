import com.buhtum.algo.Knapsack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

@Test
public class KnapsackTest {
    private final static Logger log = LoggerFactory.getLogger(KnapsackTest.class);
    public void test() {
        Knapsack knapsack = new Knapsack();
        log.debug("Output is {}", knapsack.test());
    }
}
