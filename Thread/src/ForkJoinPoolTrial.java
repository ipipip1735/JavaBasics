import javax.lang.model.type.NullType;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2018/7/3.
 */

public class ForkJoinPoolTrial {
    public static void main(String[] args) {
        ForkJoinPoolTrial forkJoinPoolTrial = new ForkJoinPoolTrial();

//        ForkJoinPoolTrial.invoke();
//        ForkJoinPoolTrial.submit();
        ForkJoinPoolTrial.execute();
    }

    private static void submit() {

        //总任务
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println("runnable");

                List<RAOne> list = new LinkedList<RAOne>();

                for (int i = 0; i < 6; i++) {
                    list.add(new RAOne(i));
                }


                for (RAOne raOne : list) {
                    raOne.fork();
                }

//                for (RAOne raOne : list) {
//                    raOne.join();
//                }

            }
        };


        System.out.println("-------");

        ForkJoinPool forkJoinPool = new ForkJoinPool(2); //创建线程池
        ForkJoinTask<Integer> forkJoinTask = (ForkJoinTask<Integer>) forkJoinPool.submit(runnable); //发送总任务到线程池

        forkJoinPool.shutdown(); //关闭线程池
        try {
            forkJoinPool.awaitTermination(10, TimeUnit.SECONDS); //设置最大等待时间为10秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(forkJoinTask.getRawResult()); //获取结果

        System.out.println("-------");


    }

    private static void invoke() {

        ForkJoinPool forkJoinPool = new ForkJoinPool(2); //创建线程池
        RT<Integer> rt = new RT<Integer>(6); //创建总任务
        System.out.println("-------");

        Integer integer = forkJoinPool.invoke(rt); //发送总任务到线程池
        System.out.println(integer.intValue()); //执行总任务

        System.out.println("-------");

    }

    private static void execute() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2); //创建线程池

        System.out.println("-------");


        RA ra = new RA(6); //创建总任务
        forkJoinPool.execute(ra); //发送总任务到线程池

        forkJoinPool.shutdown();  //关闭线程池
        try {
            forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);  //设置最大关闭时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("-------");

    }


}

class RA extends RecursiveAction {
    int n;

    public RA(int n) {
        this.n = n;
    }

    @Override
    protected void compute() {

        //创建任务
        List<RAOne> list = new LinkedList<RAOne>();

        for (int i = 0; i < n; i++) {
            list.add(new RAOne(i));
        }

        //发送任务
        for (RAOne raOne : list) {
            raOne.fork();
        }

        for (RAOne raOne : list) {
            raOne.join();
        }


    }
}

class RAOne extends RecursiveAction {
    int n;

    public RAOne(int n) {
        this.n = n;
    }

    @Override
    protected void compute() {
        System.out.println("RAOne'S is " + n);
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        System.out.println(Thread.currentThread());
        System.out.println("RAOne'E is " + n);
    }
}


class RT<Integer> extends RecursiveTask<Integer> {
    int n;
    Integer result;

    public RT(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {

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
    }

    @Override
    protected Integer compute() {
        System.out.println("RAOne'S is " + n);
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        System.out.println(Thread.currentThread());
        System.out.println("RAOne'E is " + n);
        return Integer.valueOf(n);
    }
}