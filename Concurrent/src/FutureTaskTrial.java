import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2018/12/7.
 */
public class FutureTaskTrial {
    public static void main(String[] args) {
        FutureTaskTrial futureTaskTrial = new FutureTaskTrial();
//        futureTaskTrial.get();
        futureTaskTrial.done();
    }

    private void done() {

        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("~~call~~");

                Thread.sleep(15000);
                System.out.println("--");

                return "ok";
            }
        }){
            @Override
            protected void done() {
                System.out.println("~~done~~");

            }
        };

        new Thread(futureTask).start();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        futureTask.cancel(false); //仅修改状态，让线程睡到自然醒，自己终止
//        futureTask.cancel(true); //修改状态，并强制唤醒


    }

    private void get() {

        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("~~call~~");

                Thread.sleep(1500);

                return "ok";
            }
        });

        new Thread(futureTask).start();




        try {

            String result = futureTask.get();
            System.out.println("result is " + result);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
