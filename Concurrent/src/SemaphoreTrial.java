import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

/**
 * Created by Administrator on 2018/7/3.
 */

public class SemaphoreTrial {

    public static void main(String[] args) {

        SemaphoreTrial semaphoreTrial = new SemaphoreTrial();

        Semaphore semaphore = new Semaphore(1);


//        semaphoreTrial.observer(semaphore); //打印状态信息
//        semaphoreTrial.take(semaphore);
//        semaphoreTrial.put(semaphore);



        semaphoreTrial.tryAcquire(semaphore);
//        semaphoreTrial.uninterruptibly(semaphore);


    }

    private void tryAcquire(Semaphore semaphore) {

        semaphore.acquireUninterruptibly();


        System.out.println("semaphore is " + semaphore.availablePermits());

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            semaphore.tryAcquire(2, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("semaphore is " + semaphore.availablePermits());

    }

    private void uninterruptibly(Semaphore semaphore) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("S");
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("E");
            }
        });
        semaphore.acquireUninterruptibly();

        thread.start();


        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();
        semaphore.release();
        semaphore.release();
    }

    private void observer(Semaphore semaphore) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


                while (true) {
                    System.out.println("semaphore is " + semaphore.availablePermits());

                    if (semaphore.hasQueuedThreads())
                        System.out.println("QueueLength is " + semaphore.getQueueLength());


                    try {
                        Thread.sleep(1000l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        thread.start();


    }

    private void put(Semaphore semaphore) {
        semaphore.release();
        semaphore.release(2);

    }

    private void take(Semaphore semaphore) {

        try {
            semaphore.acquire();
            System.out.println("semaphore is " + semaphore.availablePermits());
            semaphore.drainPermits();
            System.out.println("semaphore is " + semaphore.availablePermits());
            semaphore.acquire();
            semaphore.acquire();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}