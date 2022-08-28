/**
 * Created by Administrator on 2022/8/26.
 */

/**
 * 静态不变属性单例，即饿汉模式
 */
//public class Singleton {
//
//    //方式一
//    static final Singleton INSTANCE = new Singleton();
//
//    //方式二
////    static final Singleton INSTANCE;
////    static {
////        INSTANCE = new Singleton();
////    }
//}



/**
 * 懒加载单例，即懒汉模式
 */
public class Singleton {
    public static void main(String[] args) {
        Singleton.getInstance();
    }

    private static volatile Singleton INSTANCE;

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }

        return INSTANCE;
    }
}



/**
 * 内嵌类单例
 */
//public class Singleton {
//    public static void main(String[] args) {
//        Singleton.getInstance();
//    }
//
//
//    public static Singleton getInstance() {
//        return Holder.INSTANCE;
//    }
//
//    private static class Holder {
//        static Singleton INSTANCE = new Singleton();
//    }
//}



/**
 * 枚举单例
 */
//public enum Singleton {
//    INSTANCE;
//}
