import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/7/2.
 */

public class ScheduledExecutorServiceTrial {

    public static void main(String[] args) {
        ScheduledExecutorServiceTrial scheduled = new ScheduledExecutorServiceTrial();
        scheduled.base();
//        scheduled.scheduleAtFixedRate();

    }

    private void scheduleAtFixedRate() {


        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(2);

        Runnable command = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread());
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        scheduled.scheduleAtFixedRate(command, 2l, 2l, TimeUnit.SECONDS);

        try {
            Thread.sleep(55000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scheduled.shutdown();

    }

    private void base() {
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);

        Runnable command = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread());
            }
        };


        scheduled.schedule(command, 1l, TimeUnit.SECONDS);
        scheduled.schedule(command, 3l, TimeUnit.SECONDS);

        scheduled.shutdown();

    }
}
