/**
 * Created by Administrator on 2022/8/26.
 */

public class VolatileTrial {
    volatile int over;

    public static void main(String[] args) {

        VolatileTrial volatileTrial = new VolatileTrial();

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                volatileTrial.over++;
            }).start();
        }

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("volatileTrial.over = " + volatileTrial.over);
    }
}



/**
 * 使用数组的情况
 */
//public class VolatileTrial {
//    volatile int[] over = new int[2];
//
//    public static void main(String[] args) {
//
//        VolatileTrial volatileTrial = new VolatileTrial();
//
//        new Thread(()->{
//            while (volatileTrial.over[1] < 5) {}
//            System.out.println("T|System.currentTimeMillis() = " + System.currentTimeMillis());
//        }).start();
//
//
//        for (int i = 0; i < 10; i++) {
//            volatileTrial.over[1] = i;
//            if (i == 5) System.out.println("System.currentTimeMillis() = " + System.currentTimeMillis());
//            try {
//                Thread.sleep(500L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}