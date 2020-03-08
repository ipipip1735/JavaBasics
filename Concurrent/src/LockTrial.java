import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/10/26 9:27.
 */

public class LockTrial {
    public static void main(String[] args) {
        LockTrial lockTrial = new LockTrial();
//        lockTrial.lock();
        lockTrial.condition();
    }

    private void condition() {

        ReentrantLock reentrantLock = new ReentrantLock();
        Condition main = reentrantLock.newCondition();
        Condition sub = reentrantLock.newCondition();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("sub lock");
                reentrantLock.lock();//锁


                System.out.println("signal main");
                main.signal();//唤醒主线程


                try {
                    System.out.println("sub waiting");
                    sub.await();//锁让给主线程
                    System.out.println("sub awake");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



                System.out.println("sub unlock");
                reentrantLock.unlock();//解锁
            }
        });






        thread.start();

        System.out.println("main Lock");
        reentrantLock.lock(); //加锁
        try {

            System.out.println("main waiting");
            main.await();//让出锁，子线程启动要时间，把锁让出来，子线程才能使用
            System.out.println("main awake");


            System.out.println("signal sub");
            sub.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("main unlock");
        reentrantLock.unlock();//解锁


    }


    private void lock() {

        ReentrantLock reentrantLock = new ReentrantLock();

        System.out.println("before");
        reentrantLock.lock();

        System.out.println("-----start");
        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                System.out.println("R-before");
                reentrantLock.unlock();

                System.out.println("R-after");
            }
        }).start();
        System.out.println("-----end");


        try {
            Thread.sleep(8000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reentrantLock.unlock();
        System.out.println("after");


    }
}
