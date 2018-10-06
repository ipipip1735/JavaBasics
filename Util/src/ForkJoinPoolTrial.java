import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.RecursiveAction;

/**
 * Created by Administrator on 2018/7/2 10:47.
 */

public class ForkJoinPoolTrial {
    public static void main(String[] args) {
        ForkJoinPoolTrial forkJoinPoolTrial = new ForkJoinPoolTrial();
//        ForkJoinPoolTrial.base();
        ForkJoinPoolTrial.join();
    }

    private static void join() {

        ForkJoinPool forkJoinPool = new ForkJoinPool(2);

        System.out.println("-------");


        RA ra = new RA(6);
        forkJoinPool.invoke(ra);


        System.out.println("-------");


//        forkJoinPool.shutdown();
//        try {
//            forkJoinPool.awaitTermination(0, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


//        try {
//            Thread.sleep(3000l);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}

class RA extends RecursiveAction {
    int n = 0;

    public RA(int n) {
        this.n = n;

    }

    @Override
    protected void compute() {
        System.out.println("RA-compute");
//        dispatch1();
        dispatch2();


    }

    private void dispatch2() {
        List<RA1> list = new LinkedList<RA1>();
        for (int i = 0; i < n; i++) {
            list.add(new RA1(i));
        }

        for (RA1 ra1 : list) {
            ra1.fork();
        }
        for (RA1 ra1 : list) {
            ra1.join();
        }


    }

    private void dispatch1() {

        RA1 rao1 = new RA1(1);
        RA1 rao2 = new RA1(2);
        RA2 rao3 = new RA2(3);


        rao1.fork();
        rao2.fork();
        rao3.fork();
    }
}


class RA1 extends RecursiveAction {
    int n = 0;

    public RA1(int n) {
        this.n = n;

    }

    @Override
    protected void compute() {
        System.out.println("RA1'n is " + n);

        ForkJoinWorkerThread thread = (ForkJoinWorkerThread) Thread.currentThread();
        ForkJoinPool pool = thread.getPool();
        System.out.println("thread is " + thread);
        System.out.println("pool is " + pool);
    }
}


class RA2 extends RecursiveAction {
    int n = 0;

    public RA2(int n) {
        this.n = n;

    }

    @Override
    protected void compute() {

        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("RA2'n is " + n);

        ForkJoinWorkerThread thread = (ForkJoinWorkerThread) Thread.currentThread();
        ForkJoinPool pool = thread.getPool();
        System.out.println("thread is " + thread);
        System.out.println("pool is " + pool);
    }
}

