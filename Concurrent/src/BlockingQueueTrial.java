import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2018/6/29.
 */
public class BlockingQueueTrial {
    public static void main(String[] args) {
        BlockingQueueTrial blockingQueueTrial = new BlockingQueueTrial();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                blockingQueueTrial.add();
//                blockingQueueTrial.minus();
//                blockingQueueTrial.iterate();
            }
        });

        System.out.println("start");

        thread.start();
        try {
            Thread.sleep(3000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");

    }

    private void iterate() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);

//        blockingQueue.offer("aa");
//        blockingQueue.offer("bb");
//        blockingQueue.offer("cc");

//        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.peek());


    }

    private void add() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);

        try {
            blockingQueue.put("aa");
            blockingQueue.put("bb");
            blockingQueue.put("cc");
            blockingQueue.put("dd");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        blockingQueue.add("aa");
//        blockingQueue.add("bb");
//        blockingQueue.add("cc");
//        blockingQueue.add("dd");


//        System.out.println(blockingQueue.offer("aa"));
//        System.out.println(blockingQueue.offer("bb"));
//        System.out.println(blockingQueue.offer("cc"));
//        System.out.println(blockingQueue.offer("dd"));
    }

    private void minus() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(5);
        try {
            blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
