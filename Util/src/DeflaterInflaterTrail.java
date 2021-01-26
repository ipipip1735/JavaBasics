import java.io.*;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Stream;
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


//        byte[] inData = new byte[50 * 1024 * 1024];
//        byte[] output = new byte[60 * 1024 * 1024];


//        Random random = new Random();
//        for (int i = 0; i < inData.length; i++) {
//
////            if (i < (inData.length / 2)) {
////                inData[i] = (byte) 97;
////            } else {
////                inData[i] = (byte) random.nextInt(256);
////            }
//
//            inData[i] = (byte) random.nextInt(256);
//        }

//        deflaterInflaterTrail.deflater(inData, output);
//        deflaterInflaterTrail.inflater(output);


        deflaterInflaterTrail.def();
//        deflaterInflaterTrail.inf(deflaterInflaterTrail.def());


//        deflaterInflaterTrail.deflaterFile();
//        deflaterInflaterTrail.inflaterFile();

    }


    private void inflaterFile() {

//        try (InputStream inputStream = Files.newInputStream(Path.of("Util/res/big.jpg.gz"));
//             OutputStream outputStream = Files.newOutputStream(Path.of("Util/res/big1.jpg"))) {

        try (InputStream inputStream = Files.newInputStream(Path.of("Util/res/bigText.gz"));
             OutputStream outputStream = Files.newOutputStream(Path.of("Util/res/bigText1.txt"))) {

            byte[] bytes = new byte[1024];
            byte[] result = new byte[1024];

            Inflater inflater = new Inflater();
            int length, count = 0, compressedDataLength = 0;

            while ((length = inputStream.read(bytes)) != -1) {
                inflater.setInput(Arrays.copyOfRange(bytes, 0, length));
                System.out.println("length = " + length);

                while (count > 0 || !inflater.needsInput()) {
                    count = inflater.inflate(result);
                    compressedDataLength += count;
                    System.out.println("count = " + count + ", decompressedDataLength = " + compressedDataLength);

                    outputStream.write(result, 0, count);
                }
            }

            inflater.end();


        } catch (DataFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void deflaterFile() {

        try (InputStream inputStream = Files.newInputStream(Path.of("Util/res/bigText.txt"));
             OutputStream outputStream = Files.newOutputStream(Path.of("Util/res/bigText.gz"))) {

            byte[] bytes = new byte[1024];
            byte[] result = new byte[1024];

            Deflater deflater = new Deflater();
            int length, count = 0, compressedDataLength = 0;

            while ((length = inputStream.read(bytes)) != -1) {
                deflater.setInput(Arrays.copyOfRange(bytes, 0, length));
                System.out.println("length = " + length);

                while (count > 0 || !deflater.needsInput()) {
                    count = deflater.deflate(result);
                    compressedDataLength += count;
                    System.out.println("count = " + count + ", compressedDataLength = " + compressedDataLength);

                    outputStream.write(result, 0, count);
                }
            }
            deflater.finish();
            deflater.end();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void inf(ByteBuffer byteBuffer) {

//        ByteBuffer byteBuffer = ByteBuffer.allocate(data.size());
//        byteBuffer.
//
//        Inflater inflater = new Inflater();
//
//
//        System.out.println(inflater.finished());
//
//        Byte[] bytes;
//        data.toArray(bytes);
//
//
//        inflater.setInput();
//

    }

    private ByteBuffer def() {


        Deflater deflater = new Deflater();
        int count = 0, compressedDataLength = 0;

        ByteBuffer byteBuffer = UTF_8.encode("abcdefghijklmnopqrstuvwxyz");
        deflater.setInput(byteBuffer);
        deflater.finish();


        byteBuffer = ByteBuffer.allocate(10);

        while (!deflater.finished() || !deflater.needsInput()) {
            System.out.println("writing!");
            count = deflater.deflate(byteBuffer);

            compressedDataLength += count;
            System.out.println("count = " + count + ", compressedDataLength = " + compressedDataLength);

            byteBuffer.rewind();


            while (byteBuffer.hasRemaining()) {
                System.out.print(byteBuffer.get() + ", ");
            }

            System.out.println("");
            return byteBuffer.flip();
        }


        System.out.println("");
        System.out.println("compressedDataLength = " + compressedDataLength);

        deflater.end();


        return byteBuffer.flip();
    }

    private void inflater(byte[] inData) {

        Inflater inflater = new Inflater();
        inflater.setInput(inData);
//        inflater.finished();

        byte[] data = new byte[100 * 1024];
        try {
            int i = inflater.inflate(data);
            System.out.println(i);
            for (byte b : data) System.out.print(b + ", ");


        } catch (DataFormatException e) {
            e.printStackTrace();
        }

    }

    private void deflater(byte[] inData, byte[] outData) {


        Deflater deflater = new Deflater();
        int count;
        int compressedDataLength = 0;

//        for (int i = 0, j = 0; i < inData.length; i = j + 1) {
//            j = Math.min(i + inData.length / 3, inData.length);
//            System.out.println("i = " + i + ", j = " + j);

//            deflater.setInput(Arrays.copyOfRange(inData, i, j));

        deflater.setInput(inData);
        while (!deflater.needsInput()) {
            System.out.println("writing!");
            count = deflater.deflate(outData, compressedDataLength, 1 * 1024 * 1024);

            compressedDataLength += count;
            System.out.println("count = " + count + ", compressedDataLength = " + compressedDataLength);
        }

//        }

        deflater.finish();
        deflater.end();

        System.out.println("inDataLength = " + inData.length + ", compressedDataLength = " + compressedDataLength);

    }
}
