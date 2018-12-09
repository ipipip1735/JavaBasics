import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2018/10/26 9:56.
 */

public class ThreadPoolExecutorTrial {
    public static void main(String[] args) {
        ThreadPoolExecutorTrial threadPoolExecutorTrial = new ThreadPoolExecutorTrial();
//        threadPoolExecutorTrial.singletonPool();
//        threadPoolExecutorTrial.customPool();
        threadPoolExecutorTrial.cachePool();
//        threadPoolExecutorTrial.fixPool();


    }

    private void fixPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 4; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread());
                }
            });
        }

        executorService.shutdown();

    }

    private void cachePool() {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 20; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread());
                }
            });

            if (i > 2 && i<5) {
                try {
                    Thread.sleep(2500l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        executorService.shutdown();


    }

    private void customPool() {


        BaseThreadPoolExecutor baseThreadPoolExecutor =
                new BaseThreadPoolExecutor(1, 4, 0L,
                        TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread());
                int i = new Random().nextInt(10);
                System.out.println("[run]" + i);
//                try {
//                    Thread.sleep(2000l);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

        };
        Runnable runnable1 = new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread());
                int i = new Random().nextInt(10);
                System.out.println("[run]" + i);
                try {
                    Thread.sleep(2000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

        baseThreadPoolExecutor.execute(runnable);
        System.out.println(1);
        baseThreadPoolExecutor.execute(runnable1);
        System.out.println(2);
        baseThreadPoolExecutor.execute(runnable);
        System.out.println(3);
        baseThreadPoolExecutor.execute(runnable1);
        System.out.println(4);

        baseThreadPoolExecutor.shutdown();


    }

    private void singletonPool() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int i = new Random().nextInt(10);
                System.out.println("[run]" + i);
                try {
                    Thread.sleep(2000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
//        executorService.shutdown();
        System.out.println("end");
    }
}


class BaseThreadPoolExecutor extends ThreadPoolExecutor {
    public BaseThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

//    @Override
//    protected void beforeExecute(Thread t, Runnable r) {
//        System.out.println("~~beforeExecute~~");
//    }

//    @Override
//    protected void afterExecute(Runnable r, Throwable t) {
//        System.out.println("~~afterExecute~~");
//    }

//    @Override
//    protected void terminated() {
//        System.out.println("~~terminated~~");
//    }
}