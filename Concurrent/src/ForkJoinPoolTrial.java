import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2018/7/3.
 */

public class ForkJoinPoolTrial {
    public static void main(String[] args) {
        ForkJoinPoolTrial forkJoinPoolTrial = new ForkJoinPoolTrial();

        //测试提交任务，即单测试fork，无join操作
//        forkJoinPoolTrial.execute(); //异步提交，非阻塞
//        forkJoinPoolTrial.invoke(); //同步提交，阻塞
//        forkJoinPoolTrial.submit();


        //测试分叉合并，先fork，再join
        forkJoinPoolTrial.join();


    }

    private void join() {

        int sum = 0;
        ForkJoinPool forkJoinPool = new ForkJoinPool(2); //创建线程池
//        ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool(2);//工具类提供的API，将间接调用上面的方法



        List<ForkJoinTask<Integer>> list = new ArrayList<>();

        //Fork操作
        System.out.println("-------");
        for (int i = 0; i < 5; i++) {
            RTOne rtOne = new RTOne(i); //创建任务
            ForkJoinTask forkJoinTask = forkJoinPool.submit(rtOne); //发送任务到线程池
            list.add(forkJoinTask);
        }
        System.out.println("-------");

        //Join操作
        for (ForkJoinTask task : list) {
            sum += (int) task.join(); //join()是阻塞式的
        }

        System.out.println(sum);

    }

    private void submit() {

        ForkJoinPool forkJoinPool = new ForkJoinPool(2); //创建线程池
//        ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool(2);//工具类提供的API，将间接调用上面的方法


        //线程池运行前增加任务
        System.out.println("-------");

        for (int i = 0; i < 5; i++) {
            RAOne raOne = new RAOne(i); //创建任务
            forkJoinPool.submit(raOne); //发送任务到线程池
        }
        forkJoinPool.shutdown();  //关闭线程池
        System.out.println("-------");
        try {
            forkJoinPool.awaitTermination(50, TimeUnit.SECONDS);  //设置最大关闭时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void invoke() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2); //创建线程池
//        ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool(2);//工具类提供的API，将间接调用上面的方法

        //线程池运行前增加任务
        System.out.println("-------");

        for (int i = 0; i < 5; i++) {
            RTOne rtOne = new RTOne(i); //创建任务
            Integer integer = forkJoinPool.invoke(rtOne); //发送任务到线程池，将阻塞当前线程，即阻塞主线程
            System.out.println(integer);
        }
        forkJoinPool.shutdown();  //关闭线程池
        System.out.println("-------");
        try {
            forkJoinPool.awaitTermination(50, TimeUnit.SECONDS);  //设置最大关闭时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void execute() {
//        ForkJoinPool forkJoinPool = new ForkJoinPool(2); //创建线程池
        ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool(2);//工具类提供的API，将间接调用上面的方法


        //方式一：线程池运行前增加任务
//        System.out.println("-------");
//
//        for (int i = 0; i < 5; i++) {
//            RAOne raOne = new RAOne(i); //创建任务，RAOne，即不fork，也不join，仅仅测试盗窃算法
//            forkJoinPool.execute(raOne); //发送任务到线程池
//        }
//        forkJoinPool.shutdown();  //关闭线程池
//        System.out.println("-------");
//        try {
//            forkJoinPool.awaitTermination(50, TimeUnit.SECONDS);  //设置最大关闭时间，阻塞线程，当线程池关闭后才返回
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        //方式二：线程池运行时增加任务
        System.out.println("-------");
        RA ra = new RA(6); //创建总任务
        forkJoinPool.execute(ra); //发送总任务到线程池
        forkJoinPool.shutdown();  //关闭线程池
        System.out.println("-------");
        try {
            forkJoinPool.awaitTermination(50,
                    TimeUnit.SECONDS);  //设置最大关闭时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}

class RA extends RecursiveAction {
    int n;

    public RA(int n) {
        this.n = n;
    }

    @Override
    protected void compute() {
        System.out.println("~~RAOne.compute~~");

        //创建任务
        List<RAOne> list = new LinkedList<RAOne>();
        for (int i = 0; i < n; i++) {
            list.add(new RAOne(i)); //RAOne仅不fork，也不join，仅代表普通耗时任务
        }


        //发送任务到线程池中某个线程的任务队列（每线程维护一个普通数组，当数组中的任务被消费光，线程就结束了）
        for (RAOne raOne : list) {
            raOne.fork(); //非阻塞式fork操作，把任务送入线程池就立刻返回
//            raOne.invoke(); //阻塞式fork操作，当任务执行完成才返回，这样join就不必要了。这种不划算本线程将一直阻塞，尽管其他线程会盗窃此线程任务
            System.out.println("@-@-@-@-@-@-@-@-@-@-@-@");
        }

        System.out.println("[Tag RA]" + "RA start");
        System.out.println(getPool());
        System.out.println("[Tag RA]" + Thread.currentThread());
        System.out.println("[Tag RA]" + "getQueuedTaskCount() is " + getQueuedTaskCount());
        System.out.println("[Tag RA]" + "getSurplusQueuedTaskCount() is " + getSurplusQueuedTaskCount());
        System.out.println("[Tag RA]" + "RA end");


        int t = 0;
        for (RAOne raOne : list) {

            //3种Join操作
//            try {
////                raOne.get(); //阻塞线程，等待任务完成，可能抛异常
//                raOne.get(1, TimeUnit.SECONDS); //阻塞线程，等待任务完成，可能抛异常
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (TimeoutException e) {
//                e.printStackTrace();
//            }

            raOne.join(); //阻塞线程，等待任务完成（上面get()可能抛异常，这种不抛异常）。这种不划算本线程将一直阻塞，尽管其他线程会盗窃此线程任务
            System.out.println("===== " + t + " is =====");

            System.out.println(t++);
        }


    }
}

/**
 * RAOne仅不fork，也不join，用于测试盗窃算法
 */
class RAOne extends RecursiveAction {

    public RAOne(int n) {
        setForkJoinTaskTag((short) n);
    }


    @Override
    protected void compute() {
        System.out.println("~~RAOne.compute~~");
        System.out.println("[Tag" + getForkJoinTaskTag() + "]" + "RAOne start");
        System.out.println("[Tag" + getForkJoinTaskTag() + "]" + getPool());
        System.out.println("[Tag" + getForkJoinTaskTag() + "]" + Thread.currentThread());
        System.out.println("[Tag" + getForkJoinTaskTag() + "]" + "getQueuedTaskCount() is " + getQueuedTaskCount());
        System.out.println("[Tag" + getForkJoinTaskTag() + "]" + "getSurplusQueuedTaskCount() is " + getSurplusQueuedTaskCount());
        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[Tag" + getForkJoinTaskTag() + "]" + "RAOne end");
    }
}

/**
 * RT代表总任务，将先fork，在join
 */
class RT<Integer> extends RecursiveTask<Integer> {
    int n;
    Integer result;

    public RT(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        System.out.println("~~RT.compute~~");

        List<RTOne> list = new LinkedList<RTOne>();


        for (int i = 0; i < n; i++) {
            list.add(new RTOne(i));
        }


        for (RTOne raOne : list) {
            raOne.fork();
        }

        for (RTOne rtOne : list) {
            result = (Integer) rtOne.join();
        }

        return result;
    }
}

class RTOne extends RecursiveTask<Integer> {
    int n;

    public RTOne(int n) {
        this.n = n;
        setForkJoinTaskTag((short) n);
    }

    @Override
    protected Integer compute() {
        System.out.println("~~RTOne.compute~~");

        System.out.println("[Tag" + getForkJoinTaskTag() + "]" + "RTOne start");
        System.out.println("[Tag" + getForkJoinTaskTag() + "]" + getPool());
        System.out.println("[Tag" + getForkJoinTaskTag() + "]" + Thread.currentThread());
        System.out.println("[Tag" + getForkJoinTaskTag() + "]" + "getQueuedTaskCount() is " + getQueuedTaskCount());
        System.out.println("[Tag" + getForkJoinTaskTag() + "]" + "getSurplusQueuedTaskCount() is " + getSurplusQueuedTaskCount());
        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[Tag" + getForkJoinTaskTag() + "]" + "RTOne end");
        return Integer.valueOf(n);
    }
}