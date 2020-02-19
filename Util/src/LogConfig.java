import java.util.logging.*;

/**
 * Created by Administrator on 2020/2/18 22:16.
 */
public class LogConfig {
    public LogConfig() {
        System.out.println("~~" + getClass().getSimpleName() + ".Constructor~~");

        Logger logger = LogManager.getLogManager().getLogger("");
        logger.setLevel(Level.INFO);

        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.INFO);
        handler.setFormatter(new SimpleFormatter());
        logger.addHandler(handler);

    }
}
