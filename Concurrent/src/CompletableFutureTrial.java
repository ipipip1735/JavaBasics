import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2019/11/19 5:30.
 */
public class CompletableFutureTrial {

    public static void main(String[] args) {
        CompletableFutureTrial completableFutureTrial = new CompletableFutureTrial();
        completableFutureTrial.create();
    }

    private void create() {

        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            System.out.println("start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            completableFuture.complete("Hello");
            System.out.println("end");
        });


        System.out.println("--start--");

        try {
            String r = completableFuture.get();
            System.out.println(r);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("--end--");


    }
}
