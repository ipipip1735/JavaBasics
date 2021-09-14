import java.util.TimerTask;

/**
 * Created by Administrator on 2021/8/7.
 */
public class Timer {
    public static void main(String[] args) {

        java.util.Timer timer = new java.util.Timer("Timer");
//        java.util.Timer timer = new java.util.Timer("Timer", true);//使用守护线程

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "|s|scheduledExecutionTime() is " + scheduledExecutionTime());
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "|e|scheduledExecutionTime() is " + scheduledExecutionTime());
            }
        };
//        timer.schedule(timerTask, 1000L, 3000L);//周期任务延迟1秒启动,启动后间隔最小为3秒
        timer.scheduleAtFixedRate(timerTask, 1000L, 3000L);




//        for (int i = 0; i < 5; i++) {
//            int finalI = i;
//            TimerTask timerTask = new TimerTask() {
//                @Override
//                public void run() {
//                    System.out.println(finalI + "|" + Thread.currentThread() + "|scheduledExecutionTime() is " + scheduledExecutionTime());
//                    try {
//                        if (finalI == 3) Thread.sleep(10000L);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
//            timer.schedule(timerTask, 1000L);
//        }

//        timer.purge();
//        timer.cancel();

//        timer.scheduleAtFixedRate(timerTask, 1000L, 2000L);


//        try {
//            Thread.sleep(2000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}
