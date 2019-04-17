package terrell.pool.calculate;
/**
 * @author: TerrellChen
 * @version: Created in 下午1:34 15/4/19
 */

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * Description:
 */
public class ForLoopCalculator implements Calculator {
    @Override
    public long sumUp(long... numbers) {
        long total = 0;
        for (long i:numbers){
            total += i;
        }
        return total;
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 100000000).toArray();

        Instant start = Instant.now();
        Calculator calculator = new ForLoopCalculator();
        long result = calculator.sumUp(numbers);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");

        System.out.println("结果为：" + result);
    }
}
