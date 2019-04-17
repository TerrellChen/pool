package terrell.pool.calculate;
/**
 * @author: TerrellChen
 * @version: Created in 下午2:17 15/4/19
 */

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.stream.LongStream;

/**
 * Description:
 */
public class CompletableFutureCalculator implements Calculator {

    private int parallism;

    private ExecutorService pool;

    public CompletableFutureCalculator(){
        parallism = Runtime.getRuntime().availableProcessors();
        pool = Executors.newFixedThreadPool(parallism);
    }

    public void close(){
        pool.shutdown();
    }

    @Override
    public long sumUp(long... numbers) {
        CompletableFuture<Long> cf = CompletableFuture.completedFuture(0l);
        List<CompletableFuture<Long>> results = new ArrayList<>();

        // 把任务分解为 n 份，交给 n 个线程处理   4核心 就等分成4份呗
        // 然后把每一份都扔个一个SumTask线程 进行处理
        int part = numbers.length / parallism;
        for (int i = 0; i < parallism; i++) {
            int from = i * part; //开始位置
            int to = (i == parallism - 1) ? numbers.length - 1 : (i + 1) * part - 1; //结束位置

            //扔给线程池计算
            results.add(CompletableFuture.supplyAsync(() -> {
                long total = 0;
                for (int j = from; j <= to; j++) {
                    total += numbers[j];
                }
                return total;
            }));
        }

        for(CompletableFuture<Long> t: results) {
            cf = cf.thenCombine(t, (l1,l2) -> l1 + l2);
        }

        return cf.join();
    }

    public static void main(String[] args) throws Exception {
        long[] numbers = LongStream.rangeClosed(1, 100000000).toArray();

        Instant start = Instant.now();
        Calculator calculator = new CompletableFutureCalculator();
        long result = calculator.sumUp(numbers);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");

        System.out.println("结果为：" + result);

        ((CompletableFutureCalculator) calculator).close();
    }
}
