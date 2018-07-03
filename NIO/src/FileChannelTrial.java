import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

public class FileChannelTrial {


    public static void main(String[] args) {
        FileChannelTrial fileChannel = new FileChannelTrial();

        //基本使用
        fileChannel.createChannel();
        fileChannel.createChannelFromInputStream();
        fileChannel.createChannelFromOutputStream();
        fileChannel.createChannelFromRandomAccessFile();



        fileChannel.force(); //强制同步
        fileChannel.mapped();//内存映射
        fileChannel.transfer();//位传送


    }



    private void transfer() {
        try {

            //From
//            RandomAccessFile file1 = new RandomAccessFile("res/a", "r");
//            ReadableByteChannel readableByteChannel = file1.getChannel();
//            ((FileChannel) readableByteChannel).position(1);
//
//            RandomAccessFile file2 = new RandomAccessFile("res/b", "rw");
//            FileChannel fileChannel = file2.getChannel();
//
//            fileChannel.transferFrom(readableByteChannel, 2, 3);


            //TO
            RandomAccessFile file1 = new RandomAccessFile("res/b", "rw");
            WritableByteChannel writableByteChannel = file1.getChannel();
            ((FileChannel) writableByteChannel).position(1);

            RandomAccessFile file2 = new RandomAccessFile("res/a", "r");
            FileChannel fileChannel = file2.getChannel();

            fileChannel.transferTo(2, 3, writableByteChannel);


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