import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2018/6/29.
 */
public class BlockingQueueTrial {
    public static void main(String[] args) {
        BlockingQueueTrial blockingQueueTrial = new BlockingQueueTrial();
//        blockingQueueTrial.add();
        blockingQueueTrial.get();
    }

    private void get() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);

        try {

            blockingQueue.put("aa");
            blockingQueue.put("bb");
            blockingQueue.put("cc");
            blockingQueue.put("dd");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void add() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(5);
        try {
            blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
