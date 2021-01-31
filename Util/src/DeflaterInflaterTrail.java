import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;
import java.util.zip.*;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2021/1/24.
 */
public class DeflaterInflaterTrail {

    public static void main(String[] args) {
        DeflaterInflaterTrail deflaterInflaterTrail = new DeflaterInflaterTrail();

        //使用字节数组
//        deflaterInflaterTrail.deflater();
//        deflaterInflaterTrail.inflater();

        //使用ByteBuffer
//        deflaterInflaterTrail.deflateWithByteBuffer();
        deflaterInflaterTrail.inflateWithByteBuffer();


        //刷新模式
//        deflaterInflaterTrail.noFlush();
//        deflaterInflaterTrail.syncFLUSH();
//        deflaterInflaterTrail.fullFLUSH();


        //压缩解压文件
//        deflaterInflaterTrail.deflaterFile();
//        deflaterInflaterTrail.inflaterFile();

        //数据统计
//        deflaterInflaterTrail.getByte();


        //压缩解压文件使用
//        deflaterInflaterTrail.deflaterInputStream();//压缩输入流中的数据（创建.gz文件）
//        deflaterInflaterTrail.inflaterInputStream();//解压输入流中的数据（解压.gz文件）


        //压缩解压文件使用
//        deflaterInflaterTrail.deflaterOutputStream();//压缩数据后交给输出流输出（创建.gz文件）
//        deflaterInflaterTrail.inflaterOutputStream();//解压数据后交给输出流输出（解压.gz文件）

    }

    private void getByte() {

        byte[] bytes = new byte[1024];
        byte[] result = new byte[1024];
        Random random = new Random();

        Deflater deflater = new Deflater();

        for (int n = 1; n < 20; n++) {

//            for (int i = 0; i < bytes.length; i++) {
//                bytes[i] = (byte) random.nextInt(256);
//            }
            deflater.setInput(bytes);
            deflater.deflate(result);

            System.out.println(n + "|getTotalIn is " + deflater.getTotalIn() + ", " +
                    "getTotalOut is " + deflater.getTotalOut());
        }

        deflater.finish();
        deflater.end();

    }

    private void inflaterOutputStream() {

        try (FileInputStream fileInputStream = new FileInputStream("Util/res/big.txt.gz");
             FileOutputStream fileOutputStream = new FileOutputStream("Util/res/big1.txt");
             InflaterOutputStream inflaterOutputStream = new InflaterOutputStream(fileOutputStream)) {

            int n;
            byte[] bytes = new byte[1024];
            while ((n = fileInputStream.read(bytes)) != -1) {
                System.out.println("n = " + n);
                inflaterOutputStream.write(bytes, 0, n);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deflaterOutputStream() {

        try (FileInputStream fileInputStream = new FileInputStream("Util/res/big.txt");
             FileOutputStream fileOutputStream = new FileOutputStream("Util/res/big.txt.gz");
             DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(fileOutputStream)) {

            int n;
            byte[] bytes = new byte[1024];
            while ((n = fileInputStream.read(bytes)) != -1) {
                System.out.println("n = " + n);
                deflaterOutputStream.write(bytes, 0, n);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inflaterInputStream() {

        try (FileInputStream fileInputStream = new FileInputStream("Util/res/big.txt.gz");
             InflaterInputStream inflaterInputStream = new InflaterInputStream(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream("Util/res/big1.txt")) {

            int n;
            byte[] bytes = new byte[10];
            while ((n = inflaterInputStream.read(bytes)) != -1) {
                System.out.println("n = " + n);
                fileOutputStream.write(bytes, 0, n);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deflaterInputStream() {

        try (FileInputStream fileInputStream = new FileInputStream("Util/res/big.txt");
             FileOutputStream outputStream = new FileOutputStream("Util/res/big.txt.gz");
             DeflaterInputStream deflaterInputStream = new DeflaterInputStream(fileInputStream)) {

            int n;
            byte[] bytes = new byte[10];

            while ((n = deflaterInputStream.read(bytes)) != -1) {
                System.out.println("n = " + n);
                outputStream.write(bytes, 0, n);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        //方式二
//        try (FileInputStream fileInputStream = new FileInputStream("Util/res/big.txt");
//             FileOutputStream outputStream = new FileOutputStream("Util/res/big.txt.gz");
//             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
//             DeflaterInputStream deflaterInputStream = new DeflaterInputStream(fileInputStream)) {
//
//            while (deflaterInputStream.available() != 0) {
//                bufferedOutputStream.write(deflaterInputStream.read());
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    private void fullFLUSH() {

        Deflater deflater = new Deflater();

        System.out.println("[in]start|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
        deflater.setInput(UTF_8.encode("abcdefghijklmnopqrstuvwxyz"));
        System.out.println("[in]end|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        for (int i = 0; i < 5; i++) {
            System.out.println("[out]start|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
            System.out.println("count = " + deflater.deflate(byteBuffer, Deflater.FULL_FLUSH));//使用完全刷新模式
            byteBuffer.clear();
            System.out.println("[out]end|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
        }

        System.out.println("-------------------");
        deflater.finish();
        System.out.println("finish!");
        System.out.println("-------------------");


        for (int i = 0; i < 5; i++) {
            System.out.println("[out]start|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
            System.out.println("count = " + deflater.deflate(byteBuffer, Deflater.FULL_FLUSH));
            byteBuffer.clear();
            System.out.println("[out]end|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
        }

        deflater.end();
    }

    private void syncFLUSH() {

        Deflater deflater = new Deflater();

        System.out.println("[in]start|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
        deflater.setInput(UTF_8.encode("abcdefghijklmnopqrstuvwxyz"));
        System.out.println("[in]end|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        for (int i = 0; i < 5; i++) {
            System.out.println("[out]start|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
            System.out.println("count = " + deflater.deflate(byteBuffer, Deflater.SYNC_FLUSH));//使用同步刷新模式
            byteBuffer.clear();
            System.out.println("[out]end|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
        }

        System.out.println("-------------------");
        deflater.finish();
        System.out.println("finish!");
        System.out.println("-------------------");


        for (int i = 0; i < 5; i++) {
            System.out.println("[out]start|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
            System.out.println("count = " + deflater.deflate(byteBuffer, Deflater.SYNC_FLUSH));
            byteBuffer.clear();
            System.out.println("[out]end|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
        }

        deflater.end();

    }

    private void noFlush() {

        byte[] bytes = new byte[16 * 1024 + 512];
        byte[] result = new byte[8 * 1024];
        Random random = new Random();

        Deflater deflater = new Deflater();

        System.out.println("[in]start|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) random.nextInt(256);
        }
        deflater.setInput(bytes);
        System.out.println("[in]end|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());


        for (int i = 0; i < 5; i++) {
            System.out.println("[out]start|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
            System.out.println("count = " + deflater.deflate(result));
            System.out.println("[out]end|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
        }

        System.out.println("-------------------");
        deflater.finish();
        System.out.println("finish!");
        System.out.println("-------------------");

        for (int i = 0; i < 5; i++) {
            System.out.println("[out]start|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
            System.out.println("count = " + deflater.deflate(result));
            System.out.println("[out]end|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
        }

        deflater.end();
    }


    private void inflaterFile() {

        try (InputStream inputStream = Files.newInputStream(Path.of("Util/res/big.txt.gz"));
             OutputStream outputStream = Files.newOutputStream(Path.of("Util/res/big1.txt"))) {

            byte[] bytes = new byte[1024];
            byte[] result = new byte[2 * 1024];

            Inflater inflater = new Inflater();
            int length, count = 0, compressedDataLength = 0;

            while ((length = inputStream.read(bytes)) != -1) {

                System.out.println("length = " + length);

                while (inflater.needsInput()) {
                    System.out.println("finished = " + inflater.finished() + ", needsInput = " + inflater.needsInput());
                    inflater.setInput(bytes, 0, length);
                }


                while ((count = inflater.inflate(result)) > 0) {
                    System.out.println("count = " + count + ", uncompressedDataLength = " + (compressedDataLength += count));
                    outputStream.write(result, 0, count);
                }


            }

            inflater.finished();

            while (!inflater.finished()) {
                if ((count = inflater.inflate(result)) > 0) {
                    System.out.println("count = " + count + ", uncompressedDataLength = " + (compressedDataLength += count));
                    outputStream.write(result, 0, count);
                }
            }


            inflater.end();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataFormatException e) {
            e.printStackTrace();
        }

    }


    private void deflaterFile() {
        try (InputStream inputStream = Files.newInputStream(Path.of("Util/res/big.txt"));
             OutputStream outputStream = Files.newOutputStream(Path.of("Util/res/big.txt.gz"))) {

            byte[] bytes = new byte[128];
            byte[] result = new byte[64];

            Deflater deflater = new Deflater();
            int length, count = 0, compressedDataLength = 0;

            while ((length = inputStream.read(bytes)) != -1) {
                System.out.println("length = " + length);

                while (deflater.needsInput()) {
                    System.out.println("finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
                    deflater.setInput(bytes, 0, length);
                }

                while ((count = deflater.deflate(result)) > 0) {
                    System.out.println("count = " + count + ", compressedDataLength = " + (compressedDataLength += count));
                    outputStream.write(result, 0, count);
                }
            }

            deflater.finish();

            while (!deflater.finished()) {
                if ((count = deflater.deflate(result)) > 0) {
                    System.out.println("count = " + count + ", compressedDataLength = " + (compressedDataLength += count));
                    outputStream.write(result, 0, count);
                }
            }
            deflater.end();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void inflateWithByteBuffer() {

        //压缩
        Deflater deflater = new Deflater();
        ByteBuffer byteBuffer = UTF_8.encode("abcdefghijklmnopqrstuvwxyz");
        deflater.setInput(byteBuffer);
        deflater.finish();

        byteBuffer = ByteBuffer.allocate(1024);
        deflater.deflate(byteBuffer);
        deflater.end();


        //解压
        int count = 0, uncompressedDataLength = 0;
        Inflater inflater = new Inflater();
        inflater.setInput(byteBuffer.flip());//设置待解压的数据（就是上面压缩后的数据）

        try {
            while (!inflater.finished()) {
                ByteBuffer temp = ByteBuffer.allocate(10);
                count = inflater.inflate(temp);
                uncompressedDataLength += count;
                System.out.println("count = " + count + ", uncompressedDataLength = " + uncompressedDataLength);
                System.out.println("getTotalIn is " + inflater.getTotalIn() + ", getTotalOut is " + inflater.getTotalOut() + ", count = " + count);
                temp.rewind();
                while (temp.hasRemaining()) {
                    System.out.print(temp.get() + ",");
                }
                System.out.println("");
            }

        } catch (DataFormatException e) {
            e.printStackTrace();
        }
    }

    private void deflateWithByteBuffer() {

        Deflater deflater = new Deflater();
        int count = 0, compressedDataLength = 0;

        ByteBuffer byteBuffer = UTF_8.encode("abcdefghijklmnopqrstuvwxyz");
        deflater.setInput(byteBuffer);
        deflater.finish();


        while (!deflater.finished()) {
            byteBuffer = ByteBuffer.allocate(10);
            System.out.println("writing!");
            count = deflater.deflate(byteBuffer);

            compressedDataLength += count;
            System.out.println("count = " + count + ", compressedDataLength = " + compressedDataLength);
            System.out.println("getTotalIn is " + deflater.getTotalIn() + ", getTotalOut is " + deflater.getTotalOut() + ", count = " + count);

            byteBuffer.rewind();
            while (byteBuffer.hasRemaining()) {
                System.out.print(byteBuffer.get() + ",");
            }
            System.out.println("");
        }

        deflater.end();
    }

    private void inflater() {

        int count;

        //压缩
        Deflater deflater = new Deflater();
        byte[] bytes = "abcdefghijklmnopqrstuvwxyz".getBytes(UTF_8);
        System.out.println("bytes length is " + bytes.length);
        deflater.setInput(bytes);//输入数据
        deflater.finish();//输入完毕（后续没有数据输入）

        bytes = new byte[128];
        count = deflater.deflate(bytes);//压缩
        deflater.end();


        //解压
        Inflater inflater = new Inflater();//创建解压器

        inflater.setInput(Arrays.copyOf(bytes, count));//输入数据
        inflater.finished();//输入结束（后续没有数据输入）
        System.out.println("input count is " + count);

        bytes = new byte[10];
        try {
            while (!inflater.finished()) {
                Arrays.fill(bytes, (byte) 0);
                count = inflater.inflate(bytes);//解压

                System.out.println("getTotalIn is " + inflater.getTotalIn() + ", getTotalOut is " + inflater.getTotalOut() + ", count = " + count);

                for (byte b : bytes) System.out.print(b + ", ");
                System.out.println("");
            }

        } catch (DataFormatException e) {
            e.printStackTrace();
        }

        inflater.end();

    }

    private void deflater() {

        int count;

        byte[] bytes = new byte[128];
        Random random = new Random();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) random.nextInt(256);
        }

        Deflater deflater = new Deflater();
        deflater.setInput(bytes);//输入数据
        deflater.finish();//输入结束
        System.out.println("input count is " + bytes.length);

        bytes = new byte[32];
        while (!deflater.finished()) {
            System.out.println("finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
            System.out.println("writing!");
            Arrays.fill(bytes, (byte) 0);
            count = deflater.deflate(bytes);

            System.out.println("getTotalIn is " + deflater.getTotalIn() + ", " +
                    "getTotalOut is " + deflater.getTotalOut() + ", " +
                    "count = " + count);

            for (byte b : bytes) System.out.print(b + ",");
            System.out.println("");
        }

        deflater.end();
    }
}
