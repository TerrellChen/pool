package terrell.pool.calculate;
/**
 * @author: TerrellChen
 * @version: Created in 下午1:58 15/4/19
 */

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.LongStream;

/**
 * Description:
 */
public class ParallelStreamCalculator implements Calculator {
    @Override
    public long sumUp(long... numbers) {
        LongStream longStream = Arrays.stream(numbers);
        return longStream.parallel().reduce(0, Long::sum);
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 100000000).toArray();

        Instant start = Instant.now();
        Calculator calculator = new ParallelStreamCalculator();
        long result = calculator.sumUp(numbers);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");
        System.out.println("结果为：" + result);
    }
}
