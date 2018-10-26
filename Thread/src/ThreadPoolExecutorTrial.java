import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Administrator on 2018/10/26 9:56.
 */

public class ThreadPoolExecutorTrial {
    public static void main(String[] args) {
        ThreadPoolExecutorTrial threadPoolExecutorTrial = new ThreadPoolExecutorTrial();
        threadPoolExecutorTrial.base();
    }

    private void base() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("one");
            }
        };

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(runnable);
    }
}
