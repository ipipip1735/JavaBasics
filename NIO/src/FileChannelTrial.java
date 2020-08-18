import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileChannelTrial {


    public static void main(String[] args) {
        FileChannelTrial fileChannel = new FileChannelTrial();

        //基本使用
//        fileChannel.createChannel();
//        fileChannel.createChannelFromInputStream();
//        fileChannel.createChannelFromOutputStream();
//        fileChannel.createChannelFromRandomAccessFile();

//        fileChannel.write();
//        fileChannel.read();

//        fileChannel.scatter();
//        fileChannel.gather();

//        fileChannel.force(); //强制同步
//        fileChannel.mapped();//内存映射
        fileChannel.transfer();//位传送


    }




    private void gather() {

        try {

            ByteBuffer header = ByteBuffer.allocate(128);
            ByteBuffer body = ByteBuffer.allocate(1024);
            ByteBuffer[] bufferArray = {header, body};

            RandomAccessFile file = new RandomAccessFile("NIO/res/sql.log", "rw");
            FileChannel fileChannel = file.getChannel();
            fileChannel.write(bufferArray);//逐个写入数组中每个ByteBuffer，一个写满写另外一个

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void scatter() {

        try {

            ByteBuffer header = ByteBuffer.allocate(128);
            ByteBuffer body = ByteBuffer.allocate(1024);
            ByteBuffer[] bufferArray = {header, body};

            RandomAccessFile file = new RandomAccessFile("NIO/res/sql.log", "rw");
            FileChannel fileChannel = file.getChannel();
            fileChannel.read(bufferArray);//逐个读取数组中每个ByteBuffer，一个读取完读下一个

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void write() {
        try {

            RandomAccessFile file = new RandomAccessFile("NIO/res/sql.log", "rw");
            FileChannel fileChannel = file.getChannel();
            System.out.println("position is " + fileChannel.position());


            //方法一
//            ByteBuffer byteBuffer = ByteBuffer.allocate(128);
////            byteBuffer.putChar('a').putChar('v').flip();
//            byteBuffer.put(UTF_8.encode("51吃饭a"))//必须保证ByteBuffer有足够的空间，否则put()方法将报错
//                    .flip();
//            int n = fileChannel.write(byteBuffer, 1);
//            System.out.println("n is " + n);


            //方法二
            int n = fileChannel.write(UTF_8.encode("51吃饭a"),1);
            System.out.println("n is " + n);

            System.out.println("position is " + fileChannel.position());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read() {

        try {

            RandomAccessFile file = new RandomAccessFile("NIO/res/sql.log", "rw");
            FileChannel fileChannel = file.getChannel();
            System.out.println("position is " + fileChannel.position());


            ByteBuffer byteBuffer = ByteBuffer.allocate(6);
            int n = fileChannel.read(byteBuffer, 3);
            System.out.println("n is " + n);

            byteBuffer.flip();
            CharBuffer charBuffer = UTF_8.decode(byteBuffer);
            System.out.println(charBuffer);


            //绝对位置读写不会改变position
            System.out.println("position is " + fileChannel.position());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void transfer() {
        try {

            //From
            RandomAccessFile file1 = new RandomAccessFile("res/a", "r");
            ReadableByteChannel readableByteChannel = file1.getChannel();
            ((FileChannel) readableByteChannel).position(1);//从索引1开始读取

            RandomAccessFile file2 = new RandomAccessFile("res/b", "rw");
            FileChannel fileChannel = file2.getChannel();

            fileChannel.transferFrom(readableByteChannel, 2, 3);//从源通道索引1开始读取，从目标通道索引2开始写入，写入3字节


            //TO
//            RandomAccessFile file1 = new RandomAccessFile("res/b", "rw");
//            WritableByteChannel writableByteChannel = file1.getChannel();
//            ((FileChannel) writableByteChannel).position(1);//从索引1开始写入
//
//            RandomAccessFile file2 = new RandomAccessFile("res/a", "r");
//            FileChannel fileChannel = file2.getChannel();
//
//            fileChannel.transferTo(2, 3, writableByteChannel);//从源通道索引2开始读取，读取3字节，从目标通道索引1开始写入




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void mapped() {
        try {

            RandomAccessFile file = new RandomAccessFile("res/a", "r");
            FileChannel fileChannel = file.getChannel();

            MappedByteBuffer mbb = fileChannel.map(FileChannel.MapMode.READ_ONLY, 2, 3);

            while (mbb.hasRemaining()) {
                System.out.println(mbb.get());
            }


//            mbb.load();
//            System.out.println(mbb.get(0));
//            System.out.println(mbb.get(1));
//            System.out.println(mbb.get(2));


//            if (mbb.isLoaded()) {
//
//            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void force() {

        try {

            File file = new File("res/a");
            FileChannel fileChannel = FileChannel.open(file.toPath(),
                    StandardOpenOption.WRITE);

            ByteBuffer byteBuffer = ByteBuffer.allocate(6);
            byteBuffer.put((byte) 97);
            byteBuffer.put((byte) 97);
            byteBuffer.put((byte) 98);
            byteBuffer.put((byte) 98);
            byteBuffer.put((byte) 99);
            byteBuffer.put((byte) 99);
            byteBuffer.flip();

            fileChannel.write(byteBuffer);
//            fileChannel.force(false);
//            fileChannel.close();

            while (true) {
                System.out.println("go");
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createChannelFromRandomAccessFile() {

        try {

            RandomAccessFile file = new RandomAccessFile("res/a", "rw");
            FileChannel fileChannel = file.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(3);

            while (fileChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    System.out.println(byteBuffer.get());
                }
                byteBuffer.clear();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createChannelFromOutputStream() {


        try {

            FileOutputStream outputStream = new FileOutputStream("res/a");
            FileChannel fileChannel = outputStream.getChannel();


            ByteBuffer byteBuffer = ByteBuffer.allocate(6);
            byteBuffer.put((byte) 97);
            byteBuffer.put((byte) 97);
            byteBuffer.put((byte) 98);
            byteBuffer.put((byte) 98);
            byteBuffer.put((byte) 99);
            byteBuffer.put((byte) 99);
            byteBuffer.flip();

            fileChannel.write(byteBuffer);
            fileChannel.force(false);

            for (int i = 0; ; i++) {
                System.out.println("i is " + i);

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        while (fileChannel.read(byteBuffer) != -1) {
//            byteBuffer.flip();
//            while (byteBuffer.hasRemaining()) {
//                System.out.println(byteBuffer.get());
//            }
//            byteBuffer.clear();
//        }


    }

    private void createChannelFromInputStream() {

        try {

            FileInputStream inputStream = new FileInputStream("res/a");
            FileChannel fileChannel = inputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(3);

            while (fileChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    System.out.println(byteBuffer.get());
                }
                byteBuffer.clear();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createChannel() {

        try {
            File file = new File("res/a");
            FileChannel fileChannel = FileChannel.open(file.toPath(), StandardOpenOption.READ);

            ByteBuffer byteBuffer = ByteBuffer.allocate(3);

            //读文件
            while (fileChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    System.out.println(byteBuffer.get());
                }
//                byteBuffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}