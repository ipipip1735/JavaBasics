import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static java.lang.System.err;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2018/10/2.
 */
public class ThrowTrial {
    public static void main(String[] args) {
        ThrowTrial throwTrial = new ThrowTrial();

//        throwTrial.constructor();
//        throwTrial.base();
//        throwTrial.cause();


//        try {
//            throwTrial.suppress(); //将错误增加到压制栈
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }


        throwTrial.resourceTry();

    }

    private void resourceTry() {
        try (MyResource res1 = new MyResource("res1");
             MyResource res2 = new MyResource("res2")
        ) {

            System.out.println("call res");
            throw new Exception("try error");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void suppress() throws Throwable {


        Throwable t1 = null;
        try {
            throw new Throwable("t1");

        } catch (Throwable throwable) {
            t1 = throwable;

        } finally {
            Throwable t2 = new Throwable("t2");
            if (Objects.nonNull(t1)) t2.addSuppressed(t1);
            throw t2;

        }


    }

    private void cause() {


        Throwable primary = new Throwable("Primary");
        Throwable parent = new Throwable("Parent", primary);
        Throwable son = new Throwable("son", parent);

        try {
            throw son;
        } catch (Throwable throwable) {
            System.out.println(throwable.getCause().getCause());
            throwable.printStackTrace();
        }

        System.out.println("end");

    }

    private void constructor() {
        try {
            MyThrowable myThrowable = new MyThrowable("kill process");


            throw myThrowable;
        } catch (MyThrowable myThrowable1) {
            myThrowable1.printStackTrace();
        }


    }

    private void base() {

        try {
            throw new Throwable("go", null, true, true) {
                @Override
                public String getLocalizedMessage() {
                    System.out.println("my custome Error!");
                    return super.getLocalizedMessage();
                }
            };

        } catch (Throwable throwable) {
            throwable.printStackTrace();

        }

    }
}


class MyResource implements AutoCloseable {
    private String name;
    public MyResource(String name) {
        this.name = name;
    }

    @Override
    public void close() throws Exception {
        System.out.println(name  + "|resource close");
        throw new Exception("close error");
    }
}
