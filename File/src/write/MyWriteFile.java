package write;

import java.io.*;

/**
 * Created by Administrator on 2016/8/16.
 */
public class MyWriteFile {
    public MyWriteFile() {
    }


    public static void main(String[] args) {
        MyWriteFile file = new MyWriteFile();
        try {
            file.writeByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            file.writeString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void writeByteArray() throws IOException {

        Long l = 490556125452525289l;
        byte[] b = new byte[3];
        b[0]=1;
        b[1]=2;
        b[2]=3;



        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);

        baos.write(b);
        for (byte t : baos.toByteArray()) {
            System.out.println(t);
        }


//        DataOutputStream dos = new DataOutputStream(baos);
//        dos.writeLong(l);


//        b = baos.toByteArray();
//        for (byte b1 : b) {
//            System.out.println(b1);
//        }


    }

    private void writeString() throws IOException {

        StringWriter sw = new StringWriter();
        char c = 'd';
        sw.append(c);
        c = 'f';
        sw.append(c);

        System.out.println(sw.toString());


        String s = "ksf";
        char[] cs = new char[1024];
        StringReader sr = new StringReader(s);
        sr.read(cs, 0, 10);

        for (char c1 : cs) {
            System.out.println(c1);
        }


    }





}
