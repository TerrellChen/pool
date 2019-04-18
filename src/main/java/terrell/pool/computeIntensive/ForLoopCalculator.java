package terrell.pool.computeIntensive;
/**
 * @author: TerrellChen
 * @version: Created in 下午1:34 15/4/19
 */

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
}
