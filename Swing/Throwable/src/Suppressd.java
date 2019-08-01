import java.io.IOException;

/**
 * Created by Administrator on 2017/5/23.
 */
public class Suppressd {

    public static void main(String[] args) {

        Suppressd suppressd = new Suppressd();
        try {
            suppressd.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getSuppressed().length);
        }
    }

    private void show() throws IOException {

        try (
                MyClose myClose = new MyClose();
                MyCloseOne myCloseOne = new MyCloseOne();
        ) {
            System.out.println("haha");
            myClose.show();

        }
    }


}


class MyClose implements AutoCloseable {

    public MyClose() throws RuntimeException{
        System.out.println("MyClose");


    }

    public void see() throws RuntimeException {
        System.out.println("see");
        throw new RuntimeException("run time");
    }

    public void show() throws IOException {
        System.out.println("show");
        throw new IOException("ioe");
    }

    @Override
    public void close() throws RuntimeException {
        System.out.println("close");
        throw new RuntimeException("close");
    }
}
class MyCloseOne implements AutoCloseable {

    public MyCloseOne() throws RuntimeException{
        System.out.println("constractor!");
            throw new RuntimeException("Constractor");


    }

    public void see() throws RuntimeException {
        System.out.println("see");
        throw new RuntimeException("run time");
    }

    public void show() throws IOException {
        System.out.println("show");
        throw new IOException("ioe");
    }

    @Override
    public void close() throws RuntimeException {
        System.out.println("close");
        throw new RuntimeException("close");
    }
}