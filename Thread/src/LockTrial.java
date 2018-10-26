import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/10/26 9:27.
 */

public class LockTrial {
    public static void main(String[] args) {
        LockTrial lockTrial = new LockTrial();
        lockTrial.lock();
    }

    private void lock() {

        ReentrantLock reentrantLock = new ReentrantLock();


        System.out.println("before");
        reentrantLock.lock();


        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                System.out.println("R-before");
                reentrantLock.unlock();

                System.out.println("R-after");
            }
        }).start();
        System.out.println("after");
//        reentrantLock.unlock();


    }
}
