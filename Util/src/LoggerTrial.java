import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.*;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2020/2/12 16:12.
 */
public class LoggerTrial {


    public static void main(String[] args) {
        LoggerTrial loggerTrial = new LoggerTrial();

//        loggerTrial.level();//打印日志级别
//        loggerTrial.info(Logger.getLogger("a"));//打印日志器属性
        loggerTrial.logger();//日志器基本使用
//        loggerTrial.inherit();//日志器继承
//        loggerTrial.anonymousLogger();//匿名日志器
//        loggerTrial.resourceBundle();//日志国际化
//        loggerTrial.logpb();//日志国际化
//        loggerTrial.logp();//精确日志
//        loggerTrial.filter();//日志过滤器
//        loggerTrial.handler();//日志处理器
//        loggerTrial.config();//日志配置


    }

    private void inherit() {
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.ALL);
        info(logger);

        Logger loggerA = Logger.getLogger("a");
        loggerA.addHandler(new ConsoleHandler());
//        loggerA.setUseParentHandlers(false);//禁用父Logger处理器
        info(loggerA);
        loggerA.info("mmmmmm");


    }

    private void filter() {

        Logger logger = Logger.getLogger("");
        logger.setFilter(new Filter() {
            @Override
            public boolean isLoggable(LogRecord record) {
                System.out.println("~~isLoggable~~");

                System.out.println(record);

                System.out.println();
                System.out.println("getLevel is " + record.getLevel());
                System.out.println("getLoggerName is " + record.getLoggerName());
                System.out.println("getMessage is " + record.getMessage());
                System.out.println("getMillis is " + record.getMillis());
                System.out.println("getParameters is " + record.getParameters());
                System.out.println("getResourceBundle is " + record.getResourceBundle());
                System.out.println("getResourceBundleName is " + record.getResourceBundleName());
                System.out.println("getSequenceNumber is " + record.getSequenceNumber());
                System.out.println("getSourceClassName is " + record.getSourceClassName());
                System.out.println("getSourceMethodName is " + record.getSourceMethodName());
                System.out.println("getThreadID is " + record.getThreadID());
                System.out.println("getThrown is " + record.getThrown());

                return true;
            }
        });

        logger.log(Level.INFO, "xxx");


    }

    private void logpb() {

        Logger logger = Logger.getLogger("");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("msg", Locale.forLanguageTag("xx-YY"));
        logger.logrb(Level.INFO, resourceBundle, "one", "111", "222");

    }

    private void logp() {

        Logger logger = Logger.getLogger("");
        logger.log(Level.INFO, "mmmmm");
        logger.logp(Level.INFO, "XX", "yyy", "mmmmm");

    }

    private void resourceBundle() {

        //方式一：使用资源对象
//        ResourceBundle resourceBundle = ResourceBundle.getBundle("msg", Locale.forLanguageTag("xx-YY"));
//        System.out.println(resourceBundle.keySet());
//        for (String k : resourceBundle.keySet()) {
//            System.out.println(k + " is " + resourceBundle.getString(k));
//        }
//        Logger logger = Logger.getLogger("", resourceBundle.getBaseBundleName());
//        logger.setResourceBundle(resourceBundle);
//        info(logger);
//        logger.log(Level.INFO, "one");


        //方式二：使用默认地域
//        Logger logger = Logger.getLogger("", "msg");
//        System.out.println("locale is" + logger.getResourceBundle().getLocale());
//        logger.log(Level.INFO, "one");


        //方式三：使用参数
        Logger logger = Logger.getLogger("", "msg");
        logger.log(Level.INFO, "one", new Object[]{"111", 222});

    }

    private void config() {

        //例一:指定配置
//        System.setProperty("java.util.logging.config.class", "LogConfig");
        System.setProperty("java.util.logging.config.file", "Util/res/logging.properties");
        info(Logger.getLogger(""));

        System.out.println(LogManager.getLogManager().getProperty(".level"));

        //例二:重读配置文件
//        try {
//            System.setProperty("java.util.logging.config.class", "LogConfig");
//            LogManager.getLogManager().readConfiguration();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //测试配置结果
//        Logger logger = Logger.getLogger("a");
//        try {
//            logger.addHandler(new FileHandler());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        info(logger);
//        Handler handler = logger.getHandlers()[0];//获取处理器
//        handler.setFormatter(new SimpleFormatter());//手动配置
//        handler.setLevel(Level.ALL);//设置处理器级别
//        logger.info("iii");


    }

    private void level() {
        System.out.println("ALL is " + Level.ALL.intValue());
        System.out.println("SEVERE is " + Level.SEVERE.intValue());
        System.out.println("WARNING is " + Level.WARNING.intValue());
        System.out.println("INFO is " + Level.INFO.intValue());
        System.out.println("CONFIG is " + Level.CONFIG.intValue());
        System.out.println("FINE is " + Level.FINE.intValue());
        System.out.println("FINER is " + Level.FINER.intValue());
        System.out.println("FINEST is " + Level.FINEST.intValue());
        System.out.println("OFF is " + Level.OFF.intValue());
    }

    private void handler() {
        Logger logger = Logger.getLogger("a");
        logger.setLevel(Level.ALL);
        try {
//            Handler handler = new FileHandler("Util/res/a%u.log");//清空写入
//            Handler handler = new FileHandler("Util/res/a%u.log", true);//追加写入
//            Handler handler = new FileHandler("Util/res/a%u.log", 10, 2);//日志滚动写入
            Handler handler = new FileHandler("Util/res/a%u.log", 10, 2, true);//日志滚动追加写入
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }


        logger.log(Level.INFO, "XXX");

    }

    private void anonymousLogger() {
        Logger logger = Logger.getAnonymousLogger();//创建匿名日志器
//        info(logger);
        logger.log(Level.INFO, "XXX");
    }

    private void logger() {

        //获取全局日志器
        Logger logger = Logger.getLogger("global");
        info(logger);

        //获取自名命日志器
//        Logger logger = Logger.getLogger("a");
//        logger.setLevel(Level.ALL);


//        info(logger);

//        logger.log(Level.INFO, "XXX");
//        logger.logp(Level.INFO, "XXX");
//        logger.logrb(Level.INFO, "XXX");
//        logger.entering(Level.INFO, "XXX");
//        logger.exiting(Level.INFO, "XXX");
//        logger.throwing(Level.INFO, "XXX");

        //使用默认处理器记录日志
//        logger.severe("severe|XXX");
//        logger.warning("warning|XXX");
//        logger.info("info|XXX");


        //使用自定义处理器
//        try {
//            FileHandler fileHandler = new FileHandler("Util/res/a0.log");
//            System.out.println(fileHandler.getLevel());
//            fileHandler.setFormatter(new SimpleFormatter());
//            logger.addHandler(fileHandler);//增加文件处理器
//
//            logger.config("config|XXX");
//            logger.fine("fine|XXX");
//            logger.finer("finer|XXX");
//            logger.finest("finest|XXX");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //配置处理器
        ConsoleHandler consoleHandler = new ConsoleHandler();
        System.out.println(consoleHandler.getLevel());//打印处理器日志级别
        consoleHandler.setLevel(Level.ALL);//设置处理器日志级别
        logger.addHandler(consoleHandler);//增加处理器
        logger.config("config|XXX");
        logger.fine("fine|XXX");
        logger.finer("finer|XXX");
        logger.finest("finest|XXX");

    }

    public void info(Logger logger) {
        System.out.println("logger is " + logger);
        System.out.println("getAnonymousLogger is " + logger.getAnonymousLogger());
        System.out.println("getFilter is " + logger.getFilter());
        System.out.println("getGlobal is " + logger.getGlobal());


        System.out.println("getHandlers has " + logger.getHandlers().length);
        for (Handler handler : logger.getHandlers()) {
            System.out.println("Handlers is " + handler);
        }


        System.out.println("getLevel is " + logger.getLevel());
        System.out.println("getName is " + logger.getName());
        System.out.println("getParent is " + logger.getParent());
        System.out.println("getResourceBundle is " + logger.getResourceBundle());
        System.out.println("getResourceBundleName is " + logger.getResourceBundleName());
        System.out.println("getUseParentHandlers is " + logger.getUseParentHandlers());
    }


}
