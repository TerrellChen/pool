package terrell.pool.IOIntensive;
/**
 * @author: TerrellChen
 * @version: Created in 上午11:42 18/4/19
 */

import terrell.pool.LifeCycle;

import java.time.Duration;
import java.time.Instant;

/**
 * Description:
 */
public interface RequestExecutor extends LifeCycle {
    int[] request(int numbers);

    default void close(){
    }

    default void start(){
        int numbers = 10000;
        Instant start = Instant.now();
        int[] result = this.request(numbers);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");

        System.out.println("结果为：" + result);
    }
}
