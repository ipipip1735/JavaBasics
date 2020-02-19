import java.util.logging.ConsoleHandler;

/**
 * Created by Administrator on 2020/2/17 22:07.
 */
public class OneHandler extends ConsoleHandler {
    public OneHandler() {
        System.out.println("*********  " + getClass().getSimpleName() + ".Constructor  *********");
    }
}
