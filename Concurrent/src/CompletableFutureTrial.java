import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2019/11/19 5:30.
 */
public class CompletableFutureTrial {

    public static void main(String[] args) {
        CompletableFutureTrial completableFutureTrial = new CompletableFutureTrial();

//        completableFutureTrial.factory();//工厂方法创建实例
//        completableFutureTrial.get();//获取结果
        completableFutureTrial.join();//获取结果，和get()类似，就是抛异常不同
//        completableFutureTrial.cancel();//取消任务
//        completableFutureTrial.then();//异步任务
//        completableFutureTrial.handle();//错误处理
//        completableFutureTrial.accept();//接收结果，无返回值
//        completableFutureTrial.apply();//接收结果，带返回值


    }

    private void join() {


        CompletableFuture<String> completableFuture = new CompletableFuture<>();


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                completableFuture.complete("ok");

                System.out.println("end");
            }
        }).start();


        System.out.println("--start--");

        String r = completableFuture.join();//阻塞
        System.out.println(r);

        System.out.println("--end--");

    }

    private void apply() {


        try {
            Integer r = CompletableFuture.supplyAsync(() -> {

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 10;
            })
                    .applyToEither(CompletableFuture.supplyAsync(() -> {

                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return 20;
                    }), integer -> integer)
                    .get();

            System.out.println(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private void accept() {

        try {
            CompletableFuture.supplyAsync(() -> {

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 10;
            })
                    .acceptEither(CompletableFuture.supplyAsync(() -> {

                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return 20;
                    }), System.out::println)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    private void handle() {

        try {
            Integer r = CompletableFuture.supplyAsync(() -> {
                if (true) throw new RuntimeException("error!");
                return 10;
            })
                    .handle((integer, throwable) -> {
                        System.out.println(integer);
                        System.out.println(throwable);

                        return integer * 10;
                    })
                    .get();
            System.out.println(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void then() {


        //方式一：使用thenApply()
//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(1000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("supplyAsync|" + Thread.currentThread());
//
//            return "Hello";
//        });
//
//        System.out.println("--then-s-");
//
//        CompletableFuture<String> future = completableFuture.thenApply(s -> {
//            System.out.println("then|" + Thread.currentThread());
//            try {
//                Thread.sleep(1000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return s + " World";
//        });
//
//        System.out.println("--then-e-");
//
//
//        System.out.println("--start--");
//
//        try {
//            String r = future.get();
//            System.out.println(r);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        System.out.println("--end--");


        //方式二：使用thenRun()
//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
//            System.out.println("~~supplyAsync~~");
//            return "Hello";
//        });
//
//        CompletableFuture<Void> futureRun = completableFuture.thenRun(() -> {
//            System.out.println("Run!");
//        });
//
//        try {
//            System.out.println("--");
//            futureRun.get();
//            System.out.println("--");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }


        //方法三：thenCombine()
//        try {
//
//            String r = CompletableFuture.supplyAsync(() -> {
//                System.out.println(Thread.currentThread());
//                try {
//                    Thread.sleep(6000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("-A-");
//                return "AA";
//            })
//                    .thenCombine(
//                            CompletableFuture.supplyAsync(() -> {
//                                System.out.println(Thread.currentThread());
//                                try {
//                                    Thread.sleep(6000L);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                                System.out.println("-B-");
//                                return "BB";
//                            }),
//                            (s1, s2) -> {
//                                System.out.println(Thread.currentThread());
//                                return s1 + s2;
//                            })
//                    .get();
//
//            System.out.println(r);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }


        //方法四：thenCompose()
//        try {
//            Integer r = CompletableFuture.supplyAsync(() -> 10)
//            .thenCompose(integer -> {
//                System.out.println(integer);
//
//                return CompletableFuture.supplyAsync(() -> integer * 10);
//            })
//            .get();
//            System.out.println(r);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }


        //方式五：thenAccept()
        try {
//            CompletableFuture.supplyAsync(() -> 10)
//                    .thenAccept(System.out::println)
//                    .get();

            CompletableFuture.supplyAsync(() -> 11)
                    .thenAcceptBoth(CompletableFuture.supplyAsync(() -> 22),
                            (r1, r2) -> {
                                System.out.println(r1);
                                System.out.println(r2);
                            })
                    .get();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }


    /**
     * 使用静态工厂方法实例化
     * supplyAsync()：执行异步任务
     */
    private void factory() {


        //方式一：使用supplyAsync()
//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {//实例化
//
//            System.out.println(Thread.currentThread());
//
//            try {
//                Thread.sleep(2000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            return "OOO";
//        });
//
//
//        System.out.println("--start--");
//        try {
//            String r = completableFuture.get();
//            System.out.println(r);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        System.out.println("--end--");
//
//
//        try {
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        //方式二：使用runAsync()
//        try {
//            CompletableFuture.runAsync(() -> {
//                try {
//                    Thread.sleep(5000L);
//                    System.out.println("runAsync");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).thenRun(() -> {
//                try {
//                    Thread.sleep(6000L);
//                    System.out.println("thenRun");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }


        //例三：创建已经有结果的任务
//        String r = CompletableFuture.completedFuture("ok")//创建已经有结果的任务
//                .getNow("000");//非阻塞，立即获取结果
//        System.out.println(r);


    }

    private void cancel() {

        CompletableFuture<String> completableFuture = new CompletableFuture<>();


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start");

                for (int i = 0; i < 10; i++) {

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);

                    if (i > 5) {
                        completableFuture.cancel(false);
                        System.out.println(completableFuture.isCancelled());//返回true
                    }
                }

                System.out.println("end");

            }
        }).start();


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

    private void get() {


        CompletableFuture<String> completableFuture = new CompletableFuture<>();


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                completableFuture.complete("ok");

                System.out.println("end");
            }
        }).start();


        System.out.println("--start--");
//        System.out.println(completableFuture.getNow("000"));//非阻塞，立即返回结果，如果没有就返回参数中指定的默认值

        try {
            String r = completableFuture.get();//阻塞
            System.out.println(r);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("--end--");


    }
}
