import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTrial {

    public static void main(String[] args) {
        ThreadTrial threadTrial = new ThreadTrial();
//        threadTrial.sync();
        threadTrial.atomic();

    }

    private void atomic() {
        AtomicInteger atomicInteger = new AtomicInteger(1);


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    atomicInteger.compareAndExchange(1,3);

                    System.out.println(atomicInteger.get());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    atomicInteger.set(4);
                    System.out.println(atomicInteger.get());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;

                }
            }
        }).start();
    }

    private void waits() {
        Integer integer = Integer.valueOf(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    synchronized (integer) {
                        System.out.println("1-" + integer);
                        try {
                            integer.wait();
                            integer.notify();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Thread 1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;

                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    synchronized (integer) {
                        System.out.println("2-" + integer);
                        try {
                            integer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Thread 2");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;

                }
            }
        }).start();

        synchronized (integer) {
            System.out.println("main");
            integer.notify();
        }


    }


    private void sync() {

        Thread thread1, thread2;
        SharedData sharedData = new SharedData();

        thread1 = new Thread(() -> {
            sharedData.add1("Thread2");
        });
        thread2 = new Thread(() -> {

            sharedData.add2(thread1);  //将线程1的实例传递给线程2
        });

        thread1.start();
        thread2.start();


    }
}


class SharedData {
    int o = 0;
    int n = 999; //循环次数


    public void add1(String s) {

        synchronized (this) {

            while (true) {
                if (o > n) break;
                if (o == 777) {
                    try {
                        wait(); //释放锁，并转入wait状态
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("[" + s + "]" + o++);

            }
        }
    }


    public synchronized void add2(Thread thread) {


        while (true) {
            if (o > n * 1.5) break;
            System.out.println("thread1 is " + thread.getState()); //获取线程1的状态信息
            System.out.println(o++);
        }
        notify(); //唤醒处于wait的线程
    }

}
