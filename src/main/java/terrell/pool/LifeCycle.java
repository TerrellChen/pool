package terrell.pool;
/**
 * @author: TerrellChen
 * @version: Created in 上午10:37 18/4/19
 */

/**
 * Description:
 */
public interface LifeCycle {
    void close() throws Exception;
    void start() throws Exception;
}
