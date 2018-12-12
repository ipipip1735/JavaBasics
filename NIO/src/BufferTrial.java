import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.FloatBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

public class BufferTrial {
    public static void main(String[] args) {
        BufferTrial trialBuffer = new BufferTrial();


//        trialBuffer.rewind();
//        trialBuffer.flip();
//        trialBuffer.getBuffer();
//        trialBuffer.getAbsoluteBuffer();
//        trialBuffer.getBulkBuffer();
//        trialBuffer.getChar();
//        trialBuffer.order();
//        trialBuffer.baseUse();


        //        trialBuffer.wrap();
//        trialBuffer.slice();
//        trialBuffer.duplicate();
//        trialBuffer.as();

        trialBuffer.bufferToBuffer();



//        trialBuffer.writeToRead();




    }

    private void bufferToBuffer() {

        ByteBuffer byteBuffer1 = ByteBuffer.allocate(5);
        byteBuffer1.put((byte) 12);
        byteBuffer1.put((byte) 13);
        byteBuffer1.put((byte) 14);
        byteBuffer1.put((byte) 15);
        System.out.println("b1 has " + byteBuffer1.remaining());

        ByteBuffer byteBuffer2 = ByteBuffer.allocate(2 * byteBuffer1.capacity());
        byteBuffer2.put((byte) 99);

        byteBuffer1.flip();
        byteBuffer2.put(byteBuffer1);


        byteBuffer2.flip();
        while (byteBuffer2.hasRemaining()) {
            System.out.println(byteBuffer2.get());
        }

    }

    private void slice() {

        ByteBuffer buffer = ByteBuffer.allocate(30);
        buffer.put((byte) 97);
        buffer.put((byte) 98);
        buffer.put((byte) 99);
        buffer.flip();
        System.out.println("position is at " + buffer.position());
        System.out.println("limit is at " + buffer.limit());
        System.out.println("capacity is at " + buffer.capacity());

        //共享存储
        ByteBuffer byteBuffer = buffer.slice();//byteBuffer共享buffer中的内容，一个修改另外一个也会变
        System.out.println("new|position is at " + byteBuffer.position());
        System.out.println("new|limit is at " + byteBuffer.limit());
        System.out.println("new|capacity is at " + byteBuffer.capacity());

        while (byteBuffer.hasRemaining()) {
            System.out.println(byteBuffer.get());
        }

    }

    private void wrap() {
//        ByteBuffer byteBuffer = UTF_8.encode("ok");
//
//        System.out.println("BB|position is " + byteBuffer.position());
//        System.out.println("BB|limit is " + byteBuffer.limit());
//        System.out.println("BB|capacity is " + byteBuffer.capacity());
//
//        ByteBuffer byteBuffer1 = ByteBuffer.wrap(byteBuffer.array());
//        System.out.println("position is " + byteBuffer1.position());
//        System.out.println("limit is " + byteBuffer1.limit());
//        System.out.println("capacity is " + byteBuffer1.capacity());
//
//
//        byteBuffer.putChar('d'); //对原Buffer的修改不影响新Buffer，它们是独立的
//        System.out.println("BB|position is " + byteBuffer.position());
//        System.out.println("BB|limit is " + byteBuffer.limit());
//        System.out.println("BB|capacity is " + byteBuffer.capacity());
//        System.out.println("position is " + byteBuffer1.position());
//        System.out.println("limit is " + byteBuffer1.limit());
//        System.out.println("capacity is " + byteBuffer1.capacity());
//
//        while (byteBuffer1.hasRemaining()) {
//            System.out.println(byteBuffer1.get());
//        }



        //类别测试
//        ByteBuffer byteBuffer = ByteBuffer.allocate(15);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(15);
        byteBuffer.putChar('o');
//
        ByteBuffer byteBuffer1 = ByteBuffer.wrap(byteBuffer.array());
//        System.out.println("buffer1 is " + byteBuffer1.isDirect());



    }

    private void as() {

        //ByteBuffer写入字符串
//        ByteBuffer byteBuffer =  ByteBuffer.allocate(6);
//        byteBuffer.asCharBuffer().put("x叉x");
//
//        while (byteBuffer.hasRemaining()) {
//            System.out.println(byteBuffer.getChar());
//        }


        //ByteBuffer 转换为 CharBuffer
//        CharBuffer charBuffer = CharBuffer.allocate(2);
//        charBuffer.put("吃饭");
//        charBuffer.flip();
//
//        ByteBuffer byteBuffer = UTF_8.encode(charBuffer);
//        while (byteBuffer.hasRemaining()) {
//            System.out.println(byteBuffer.get());
//        }


        //CharBuffer 转换为 ByteBuffer
//        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
//        byteBuffer.put("吃饭".getBytes(UTF_8)).flip();
//
//        CharBuffer charBuffer = UTF_8.decode(byteBuffer);
//        while (charBuffer.hasRemaining()) {
//            System.out.println(charBuffer.get());
//        }


        //导出剩余视图
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        System.out.println("BB|position is " + byteBuffer.position());
        System.out.println("BB|limit is " + byteBuffer.limit());
        System.out.println("BB|capacity is " + byteBuffer.capacity());
        byteBuffer.put((byte) 97);
        byteBuffer.put((byte) 98);
        byteBuffer.put((byte) 99);
        byteBuffer.put((byte) 100);
        System.out.println("BB|position is " + byteBuffer.position());
        System.out.println("BB|limit is " + byteBuffer.limit());
        System.out.println("BB|capacity is " + byteBuffer.capacity());

//        byteBuffer.flip();
        byteBuffer.position(1);
        byteBuffer.limit(7);

//        System.out.println("BB|position is " + byteBuffer.position());
//        System.out.println("BB|limit is " + byteBuffer.limit());
//        System.out.println("BB|capacity is " + byteBuffer.capacity());

        CharBuffer charBuffer = byteBuffer.asCharBuffer();
        System.out.println("CB|position is " + charBuffer.position());
        System.out.println("CB|limit is " + charBuffer.limit());
        System.out.println("CB|capacity is " + charBuffer.capacity());

        charBuffer.put('x');
        charBuffer.put('y');



        byteBuffer.rewind();
        while (byteBuffer.hasRemaining()) {
            System.out.println(byteBuffer.get());
        }

    }

    private void convert() {

//        String k = "张s实;例11大。";
//        ByteBuffer byteBuffer = ByteBuffer.wrap(k.getBytes(), 0, 4);
//        Charset charset = UTF_8;
//        CharBuffer charBuffer = charset.decode(byteBuffer);
//        System.out.println(charBuffer);
//
//        byteBuffer = ByteBuffer.wrap(k.getBytes(), 0, 5);
//        System.out.println(UTF_8.decode(byteBuffer));


        String k = "abc";
        ByteBuffer byteBuffer = UTF_8.encode(k);
        byteBuffer.get();
        System.out.println("position is " + byteBuffer.position());
        System.out.println("limit is " + byteBuffer.limit());
//        while (byteBuffer.hasRemaining()) {
//            System.out.println(byteBuffer.get());
//        }
//        byteBuffer.flip();
//        String k = new String({'吃'}, UTF_8);
//        ByteBuffer byteBuffer = ByteBuffer.wrap(k.getBytes(), 0, 4);

//        System.out.println("result is ");
//        byteBuffer.flip();
        CharBuffer charBuffer = byteBuffer.asCharBuffer();
        System.out.println("position is " + charBuffer.position());
        System.out.println("limit is " + charBuffer.limit());

        while (charBuffer.hasRemaining()) {
            System.out.println((byte) charBuffer.get());
        }


//        System.out.println("position is " + charBuffer.position());
//        System.out.println("limit is " + charBuffer.limit());
//        char c = charBuffer.get();

//        System.out.println((byte)c);


//        char c =  charBuffer.get();
//        char chars[] = charBuffer.array();


//        String s = new String(chars);
//        System.out.println(s);


//        char c[] = {'吃', '饭'};
//        System.out.println(c);
//        CharBuffer charBuffer = CharBuffer.allocate(3);
//        charBuffer.put(c);
//        charBuffer.flip();
//        System.out.println(charBuffer.get());

    }


    private void duplicate() {

        byte[] bytes = new byte[10];
        Arrays.fill(bytes, (byte) 97);//填充字节数组
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length + 20);
        buffer.put(bytes);
        buffer.flip();

        //共享存储
        ByteBuffer byteBuffer = buffer.duplicate();//byteBuffer共享buffer中的内容，一个修改另外一个也会变
        System.out.println("position is at " + byteBuffer.position());
        System.out.println("limit is at " + byteBuffer.limit());
        System.out.println("capacity is at " + byteBuffer.capacity());

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

    private void getBuffer() {

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
            aFile = new RandomAccessFile("NIO/res/flipData", "rw");

            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(48);
//        System.out.println("position is at " + buf.position());

            int bytesRead;
//        System.out.println("position is at " + buf.position());


            while ((bytesRead = inChannel.read(buf)) != -1) {

                System.out.println("Read " + bytesRead);
                System.out.println("position is at " + buf.position());
                buf.flip();

                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }

                buf.clear();
            }
            aFile.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void rewind() {
        ByteBuffer b = ByteBuffer.allocate(1);
        b.put((byte) 123);
        b.flip();
//        b.rewind();
        b.put((byte) 123);
//        b.put((byte) 123);

    }

    public void getChar() {
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.putChar('d');
        buf.flip();
        System.out.println(buf.getChar());


    }
}


//class BufferToText {
//    private static final int BSIZE = 1024;
//    public static void main(String[] args) throws Exception {
//        FileChannel fc =
//                new FileOutputStream("data2.txt").getChannel();
//        fc.write(ByteBuffer.wrap("Some text".getBytes()));
//        fc.close();
//        fc = new FileInputStream("data2.txt").getChannel();
//        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
//        fc.read(buff);
//        buff.flip();
//// Doesn’t work:
//        System.out.println(buff.asCharBuffer());
//// Decode using this system’s default Charset:
//        buff.rewind();
//        String encoding = System.getProperty("file.encoding");
//        System.out.println("Decoded using " + encoding + ": "
//                + Charset.forName(encoding).decode(buff));
//// Or, we could encode with something that will print:
//        fc = new FileOutputStream("data2.txt").getChannel();
//        fc.write(ByteBuffer.wrap(
//                "Some text".getBytes("UTF-16BE")));
//        fc.close();
//// Now try reading again:
//        fc = new FileInputStream("data2.txt").getChannel();
//        buff.clear();
//        fc.read(buff);
//        buff.flip();
//        System.out.println(buff.asCharBuffer());
//// Use a CharBuffer to write through:
//        fc = new FileOutputStream("data2.txt").getChannel();
//        buff = ByteBuffer.allocate(24); // More than needed
//        buff.asCharBuffer().put("Some text");
//        fc.write(buff);
//        fc.close();
//// Read and display:
//        fc = new FileInputStream("data2.txt").getChannel();
//        buff.clear();
//        fc.read(buff);
//        buff.flip();
//        System.out.println(buff.asCharBuffer());
//    }
//}