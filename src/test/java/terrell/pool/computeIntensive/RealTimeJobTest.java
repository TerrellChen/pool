package terrell.pool.computeIntensive;

import org.junit.Test;

/**
 * @author: TerrellChen
 * @version: Created in 上午11:30 18/4/19
 */

/**
 * Description:
 */
public class RealTimeJobTest {
    @Test
    public void test() throws Exception{
        ForLoopCalculator forLoopCalculator = new ForLoopCalculator();
        ForkJoinCalculator forkJoinCalculator = new ForkJoinCalculator();
        FutureCalculator futureCalculator = new FutureCalculator();
        CompletableFutureCalculator completableFutureCalculator = new CompletableFutureCalculator();
        ParallelStreamCalculator parallelStreamCalculator = new ParallelStreamCalculator();

        System.out.println("Fork/Join");
        forkJoinCalculator.start();
        forkJoinCalculator.close();
        System.out.println("");
        System.out.println("For loop");
        forLoopCalculator.start();
        forLoopCalculator.close();
        System.out.println("");
        System.out.println("Future");
        futureCalculator.start();
        futureCalculator.close();
        System.out.println("");
        System.out.println("CompletableFuture");
        completableFutureCalculator.start();
        completableFutureCalculator.close();
        System.out.println("");
        System.out.println("ParallelStream");
        parallelStreamCalculator.start();
        parallelStreamCalculator.close();
    }

}
