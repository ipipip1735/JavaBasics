import java.nio.ByteBuffer;
import java.util.Vector;

/**
 * Created by Administrator on 2018/11/13.
 */
public class MemoryLeak {
    static Vector v = new Vector(10);

    ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);


    public static void main(String[] args) {
        MemoryLeak memoryLeak = new MemoryLeak();
        memoryLeak.staticLeak();






    }

    private void staticLeak() {

        for (int i = 1; i<10; i++)
        {
            Object o = new MemoryLeak();
            v.add(o);
//            o = null;
        }

        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
