import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Administrator on 2018/12/8.
 */

public class SynchronousQueueTrial {

    public static void main(String[] args) {
        SynchronousQueueTrial queueTrial = new SynchronousQueueTrial();
        queueTrial.put();
//        queueTrial.take();
//        queueTrial.sync();


    }

    private void take() {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(1000l);//睡眠1秒，怕机器受不了
                        String s = "result is " + new Random().nextInt(100);
                        synchronousQueue.add(s);
                        //synchronousQueue.offer(s);
                        //synchronousQueue.put(s);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        while (true) {
            try {
                System.out.println(synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void put() {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(1000l);//睡眠1秒，怕机器受不了
                        System.out.println(synchronousQueue.remove());
//                        System.out.println(synchronousQueue.poll());
//                    System.out.println(synchronousQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

        while (true) {
            try {
                synchronousQueue.put("result is " + new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    private void sync() {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue(true);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500l);
                    synchronousQueue.put("ok1");
                    System.out.println("[1]" + "end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000l);
                    synchronousQueue.put("ok2");
                    System.out.println("[2]" + "end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        try {
            System.out.println("[main]start");

            Thread.sleep(2000l);
            String result = synchronousQueue.take();
            System.out.println("[main]" + "result is " + result);
            Thread.sleep(2000l);
            result = synchronousQueue.take();
            System.out.println("[main]" + "result is " + result);

            System.out.println("[main]end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }

    private void putTake() {

        SynchronousQueue<String> synchronousQueue = new SynchronousQueue();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000l);
                    System.out.println("[1]" + "size is " + synchronousQueue.size());

                    String result = synchronousQueue.take();
                    System.out.println("[1]" + "result is " + result);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100l);
                    System.out.println("2|size is " + synchronousQueue.size());
                    synchronousQueue.put("2222");
                    System.out.println("2|end");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        try {
            System.out.println("start");
            synchronousQueue.put("String");
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();


    }
}
