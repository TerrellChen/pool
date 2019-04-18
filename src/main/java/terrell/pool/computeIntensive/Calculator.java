package terrell.pool.computeIntensive;
/**
 * @author: TerrellChen
 * @version: Created in 下午1:34 15/4/19
 */

import terrell.pool.LifeCycle;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * Description: 实时任务 cpu密集型
 */
public interface Calculator extends LifeCycle {
    long sumUp(long... numbers) throws Exception;

    default void close(){

    }

    @Override
    default void start() throws Exception {
        long[] numbers = LongStream.rangeClosed(1, 100000000).toArray();

        Instant start = Instant.now();
        long result = this.sumUp(numbers);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");

        System.out.println("结果为：" + result);
    }
}
