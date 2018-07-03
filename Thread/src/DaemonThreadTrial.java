import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2018/7/2.
 */

public class DaemonThreadTrial {

    public static void main(String[] args) {
        DaemonThreadTrial daemonThreadTrial = new DaemonThreadTrial();
        daemonThreadTrial.base();
    }

    private void base() {
        int[] i = {0};

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
//                while (true) {
//
//                    System.out.println(i[0]++);
//
//                    try {
//                        sleep(1000l);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        });
        thread.setDaemon(true);  //将线程设置为守护线程
        thread.start();


        while (true) {
            if(i[0]>5)break;
            System.out.println(i[0]++);
            System.out.println(thread.getState());

            try {
                sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
