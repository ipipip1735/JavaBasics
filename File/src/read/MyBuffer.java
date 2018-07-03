package read;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016/10/9.
 */
public class MyBuffer {

    public static void main(String[] args) throws IOException {

        MyBuffer myBuffer = new MyBuffer();
//        myBuffer.quizReadBuffer();
//        myBuffer.quizWriteBuffer();
        myBuffer.quizFileRead();
    }

    public void quizReadBuffer() throws IOException {

        File file = new File("E:\\Program\\Java\\Intellij\\File\\src\\resource\\a.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, 2);
        System.out.println(bufferedInputStream.available());


        byte[] bytes = new byte[bufferedInputStream.available()];
        bufferedInputStream.skip(3);
        System.out.println((char) bufferedInputStream.read());
        System.out.println((char) bufferedInputStream.read());
        System.out.println((char) bufferedInputStream.read());
        System.out.println((char) bufferedInputStream.read());
        System.out.println((char) bufferedInputStream.read());

        System.out.println((char) bufferedInputStream.read());

        System.out.println((char) bufferedInputStream.read());
        System.out.println((char) bufferedInputStream.read());
        System.out.println((char) bufferedInputStream.read());
        System.out.println((char) bufferedInputStream.read());
        System.out.println((char) bufferedInputStream.read());


    }

    public void quizWriteBuffer() throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("E:\\Program\\Java\\Intellij\\File\\src\\resource\\a.txt", false);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 1024);

    }


    public void quizFileRead() throws IOException {

        File file = new File("E:\\Program\\Java\\Intellij\\File\\src\\resource\\a.txt");
        FileReader fileReader = new FileReader(file);

        char[] chars = new char[1024];
        fileReader.read(chars);
        System.out.println(Charset.defaultCharset());
//        System.out.println(new String(chars));




    }













}
