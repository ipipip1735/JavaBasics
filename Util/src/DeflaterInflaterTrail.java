import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2021/1/24.
 */
public class DeflaterInflaterTrail {

    public static void main(String[] args) {
        DeflaterInflaterTrail deflaterInflaterTrail = new DeflaterInflaterTrail();

//        deflaterInflaterTrail.deflater();
//        deflaterInflaterTrail.inflater();
//        deflaterInflaterTrail.test();


//        deflaterInflaterTrail.deflateWithByteBuffer();
//        deflaterInflaterTrail.inflateWithByteBuffer();


        deflaterInflaterTrail.deflaterFile();
        deflaterInflaterTrail.inflaterFile();

    }

    private void test() {


        byte[] bytes = new byte[15 * 1024];
        byte[] result = new byte[32 * 1024];
        Random random = new Random();


        Deflater deflater = new Deflater();

        System.out.println("in|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) random.nextInt(256);
        }
        deflater.setInput(bytes);

        System.out.println("out|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
        System.out.println("count = " + deflater.deflate(result));

        System.out.println("in|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) random.nextInt(256);
        }
        deflater.setInput(bytes);


        System.out.println("out|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
        System.out.println("count = " + deflater.deflate(result));


        System.out.println("in|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) random.nextInt(256);
        }
        deflater.setInput(bytes);


        System.out.println("out|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
        System.out.println("count = " + deflater.deflate(result));


        deflater.finish();

        System.out.println("out|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
        System.out.println("count = " + deflater.deflate(result));

        System.out.println("out|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
        System.out.println("count = " + deflater.deflate(result));


//        while (deflater.needsInput()) {
//            System.out.println("s|finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
//            deflater.setInput(bytes, 0, length);
//
//
//        }


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
        try (InputStream inputStream = Files.newInputStream(Path.of("Security/res/big.txt"));
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
            while (!inflater.finished() || !inflater.needsInput()) {
                ByteBuffer temp = ByteBuffer.allocate(10);
                count = inflater.inflate(temp);
                uncompressedDataLength += count;
                System.out.println("count = " + count + ", uncompressedDataLength = " + uncompressedDataLength);

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


        while (!deflater.finished() || !deflater.needsInput()) {
            byteBuffer = ByteBuffer.allocate(10);
            System.out.println("writing!");
            count = deflater.deflate(byteBuffer);

            compressedDataLength += count;
            System.out.println("count = " + count + ", compressedDataLength = " + compressedDataLength);

            byteBuffer.rewind();
            while (byteBuffer.hasRemaining()) {
                System.out.print(byteBuffer.get() + ",");
            }
            System.out.println("");
        }

        deflater.end();
    }

    private void inflater() {

        int count = 0, uncompressedDataLength = 0;

        //压缩
        Deflater deflater = new Deflater();
        byte[] bytes = "abcdefghijklmnopqrstuvwxyz".getBytes(UTF_8);
        System.out.println("bytes length is " + bytes.length);
        deflater.setInput(bytes);//输入数据
        deflater.finish();//输入完毕，后续没有数据输入

        bytes = new byte[128];
        count = deflater.deflate(bytes);//压缩


        //解压
        Inflater inflater = new Inflater();//创建解压器
        inflater.setInput(Arrays.copyOfRange(bytes, 0, count));//输入数据
        inflater.finished();//输入完毕，后续没有数据输入

        bytes = new byte[10];
        try {
            while (!inflater.finished() || !inflater.needsInput()) {
                Arrays.fill(bytes, (byte) 0);
                count = inflater.inflate(bytes);//解压
                uncompressedDataLength += count;

                System.out.println("count = " + count + ", uncompressedDataLength = " + uncompressedDataLength);
                for (byte b : bytes) System.out.print(b + ", ");
            }

        } catch (DataFormatException e) {
            e.printStackTrace();
        }

        inflater.end();

    }

    private void deflater() {

        int count = 0, compressedDataLength = 0;

        byte[] bytes = new byte[128];
        Random random = new Random();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) random.nextInt(256);
        }

        Deflater deflater = new Deflater();
        deflater.setInput(bytes);
        deflater.finish();

        bytes = new byte[32];
        while (!deflater.finished() || !deflater.needsInput()) {
            System.out.println("finished = " + deflater.finished() + ", needsInput = " + deflater.needsInput());
            System.out.println("writing!");
            Arrays.fill(bytes, (byte) 0);
            count = deflater.deflate(bytes);
            compressedDataLength += count;
            System.out.println("count = " + count + ", compressedDataLength = " + compressedDataLength);
            for (byte b : bytes) System.out.print(b + ",");
            System.out.println("");
        }

        deflater.end();
    }
}
