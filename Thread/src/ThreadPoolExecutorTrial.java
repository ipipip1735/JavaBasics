import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2018/10/26 9:56.
 */

public class ThreadPoolExecutorTrial {
    public static void main(String[] args) {
        ThreadPoolExecutorTrial threadPoolExecutorTrial = new ThreadPoolExecutorTrial();
        threadPoolExecutorTrial.base();
//        threadPoolExecutorTrial.custom();


    }

    private void custom() {

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                int i = new Random().nextInt(10);
                System.out.println("[run]" + i);
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        BaseThreadPoolExecutor baseThreadPoolExecutor =
                new BaseThreadPoolExecutor(1, 1, 0L,
                        TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());


        baseThreadPoolExecutor.execute(runnable);
        System.out.println(1);
        baseThreadPoolExecutor.execute(runnable);
        System.out.println(2);
        baseThreadPoolExecutor.execute(runnable);
        System.out.println(3);
        baseThreadPoolExecutor.execute(runnable);
        System.out.println(4);

        baseThreadPoolExecutor.shutdown();


    }

    private void base() {

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
        executorService.shutdown();
    }
}


class BaseThreadPoolExecutor extends ThreadPoolExecutor {
    public BaseThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("~~beforeExecute~~");
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        System.out.println("~~afterExecute~~");
    }

    @Override
    protected void terminated() {
        System.out.println("~~terminated~~");
    }
}