import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Administrator on 2018/7/4.
 */

public class AtomicTrial {
    public static void main(String[] args) {
        AtomicTrial atomicTrial = new AtomicTrial();
        atomicTrial.base();
    }

    private void base() {

//        Integer integer = Integer.valueOf(6);
//        if (b) b = false;
//
//
//        int i = 100;
//        if (i > 10) i = 200;


        AtomicBoolean bool = new AtomicBoolean();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    bool.compareAndExchange(false, true);
                    System.out.println("thread1 is " + bool.get());
                    try {
                        Thread.sleep(1000l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    bool.compareAndExchange(true, false);
                    System.out.println("thread2 is " + bool.get());
                    try {
                        Thread.sleep(1000l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();



//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (b) {
//                    b = b? false: true;
//
//                }
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (b) {
//                    b = b? false: true;
//
//                }
//            }
//        }).start();
//
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (b) {
//                    b = b? false: true;
//
//                }
//            }
//        }).start();



    }
}
