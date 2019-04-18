package terrell.pool.computeIntensive;
/**
 * @author: TerrellChen
 * @version: Created in 下午1:58 15/4/19
 */

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * Description:
 */
public class ParallelStreamCalculator implements Calculator {
    private int parallism;
    private ForkJoinPool pool;

    public ParallelStreamCalculator(){
        parallism = Runtime.getRuntime().availableProcessors();
        pool = new ForkJoinPool(parallism);
    }

    @Override
    public long sumUp(long... numbers) throws Exception{
        System.out.println("Pool size: " + parallism);
        LongStream longStream = Arrays.stream(numbers);
        return pool.submit(() -> longStream.parallel().reduce(0, Long::sum)).get();
    }
}
