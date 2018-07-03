import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class BufferTrial {
    public static void main(String[] args) {
        BufferTrial trialBuffer = new BufferTrial();


//        trialBuffer.flip();
//        trialBuffer.getBuffer();
//        trialBuffer.getAbsoluteBuffer();
//        trialBuffer.getBulkBuffer();
//        trialBuffer.getChar();
//        trialBuffer.order();
//        trialBuffer.baseUse();


//        trialBuffer.writeToRead();
        trialBuffer.duplicate();





    }



    private void duplicate() {

        byte[] bytes = new byte[255];
        Arrays.fill(bytes, (byte) 97);//填充字节数组
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.put(bytes);

        //共享存储
        ByteBuffer byteBuffer = buffer.duplicate();//byteBuffer共享buffer中的内容，一个修改另外一个也会变
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        byteBuffer.flip(); //转换为读模式
        while (byteBuffer.hasRemaining()) {  //遍历读取
            System.out.println(byteBuffer.get());
        }


    }

    private void baseUse() {
        ByteBuffer buffer = ByteBuffer.allocate(30);

        buffer.put((byte) 12);//插入

        buffer.flip(); //转换为读模式

        //测试指针位移
        System.out.println(buffer.position());
        System.out.println(buffer.get());
        System.out.println(buffer.position());
        System.out.println("position is " + buffer.position());
        System.out.println("limit is " + buffer.limit());

        buffer.put((byte) 52);//插入数据
        System.out.println(buffer.position());
        System.out.println(buffer.get());//获取数据

    }


    /***
     * 先写入数据，再读取写入数据
     */
    public void writeToRead() {

        byte[] bytes = new byte[255];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);//分配空间

        //写操作
        buffer.clear(); //转换为写模式
        Arrays.fill(bytes, (byte) 97);//填充字节数组
        buffer.put(bytes);//将字节数组的内容写入到buffer

        //读操作
        buffer.flip(); //转换为读模式
        Arrays.fill(bytes, (byte) 0);//清空字节数组
        buffer.get(bytes); //读取到字节数组
        System.out.println(new String(bytes)); //打印字节数组

    }



    private void chain() {

    }

    private void order() {
        ByteBuffer buf = ByteBuffer.allocate(48);
        System.out.println(buf.order());

        buf.order(ByteOrder.LITTLE_ENDIAN);
        System.out.println(buf.order());
    }

    private void getBulkBuffer() {
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.put((byte) 11);
        buf.put((byte) 22);

        byte[] bytes = new byte[9];
        buf.get(bytes, 0, 1);


        System.out.println(bytes);
        System.out.println("--------------");
        System.out.println("position is at " + buf.position());
        System.out.println("limit is at " + buf.limit());
        System.out.println("capacity is at " + buf.capacity());
    }

    private void getAbsoluteBuffer() {
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.put((byte) 11);
        buf.put((byte) 22);
        byte b = buf.get(1);

        System.out.println(b);
        System.out.println("--------------");
        System.out.println("position is at " + buf.position());
        System.out.println("limit is at " + buf.limit());
        System.out.println("capacity is at " + buf.capacity());



    }

    private void getBuffer(){

        ByteBuffer buf = ByteBuffer.allocate(48);
        System.out.println("--------------");
        System.out.println("position is at " + buf.position());
        System.out.println("limit is at " + buf.limit());
        System.out.println("capacity is at " + buf.capacity());
        byte b = 123;
        buf.put(b);
        buf.put(b);
        System.out.println("--------------");
        System.out.println("position is at " + buf.position());
        System.out.println("limit is at " + buf.limit());
        System.out.println("capacity is at " + buf.capacity());

        buf.flip();
        System.out.println("--------------");
        System.out.println("position is at " + buf.position());
        System.out.println("limit is at " + buf.limit());
        System.out.println("capacity is at " + buf.capacity());

        byte g = buf.get();
        System.out.println("--------------");
        System.out.println("position is at " + buf.position());
        System.out.println("limit is at " + buf.limit());
        System.out.println("capacity is at " + buf.capacity());
        System.out.println(g);


        g = buf.get();
        System.out.println("--------------");
        System.out.println("position is at " + buf.position());
        System.out.println("limit is at " + buf.limit());
        System.out.println("capacity is at " + buf.capacity());
        System.out.println(g);

    }


    public void flip() {

        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("Buffer/res/flipData", "rw");

            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(48);
//        System.out.println("position is at " + buf.position());

            int bytesRead = inChannel.read(buf);
//        System.out.println("position is at " + buf.position());


            while (bytesRead != -1) {

                System.out.println("Read " + bytesRead);
                System.out.println("position is at " + buf.position());
                buf.flip();

                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }

                buf.clear();
                bytesRead = inChannel.read(buf);
            }
            aFile.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getChar() {
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.putChar('d');
        buf.flip();
        System.out.println(buf.getChar());


    }
}
