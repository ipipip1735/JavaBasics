package read;


import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/8/16.
 */
public class MyFile {

    File file = new File("File/src/resource/a.txt");

    public MyFile() {
        System.out.println(file.getAbsolutePath());
    }

    public static void main(String[] args) throws Exception {
//        MyFile mFile = new MyFile();
//        mFile.mFileInputStream();
//        mFile.testDelete();


//        mFile.testReadUTF();
//        mFile.testWriteUTF();


//        mFile.getLenght();





    }



//  测试length()

    public void getLenght() {
        long l = this.file.length();
        System.out.println(l);
    }




//    public void mInputStreamReader() {
//
//
//        InputStreamReader inputStreamReader = new InputStreamReader();
//
//    }

    public void mFileInputStream() throws Exception {

        File file = new File("File/res/a.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] b = new byte[fileInputStream.available()];
        fileInputStream.read(b);
        System.out.println(new String(b, Charset.forName("utf-8")));

//        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");


//        char[] c = new char[4];
//        inputStreamReader.read(c);

//        System.out.println(new String(c));


    }


    public void testDelete() throws Exception {

//        File file = new File("File/src/resource/a.txt");
//        System.out.println(file.getAbsolutePath());
//        System.out.println(file.isFile());
//        File[] files = File.listRoots();
//
//        for (File f : files) {
//            System.out.println(f.toURI());
//        }


    }

    public void testReadUTF() throws Exception {

        FileInputStream fis = new FileInputStream(file);
        DataInputStream dis = new DataInputStream(fis);
            System.out.println(dis.available());
        while (dis.available() != 0) {
            String k = dis.readUTF();
//            System.out.println(k);

        }


    }


    public void testWriteUTF() throws Exception {

        FileOutputStream fos = new FileOutputStream(file);
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeUTF("k");

    }


}
