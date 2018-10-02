import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/10/9.
 */
public class Input {


    public static void main(String[] args) {

        Input myInput = new Input();
//        myInput.readString();
//        myInput.FileinputStream();
//        myInput.BufferInputStream();
        myInput.mark();


    }



    private void mark() {

        try {

            byte[] bytes={1,2,3,4,5};
            ByteArrayInputStream in=new ByteArrayInputStream(bytes);
            BufferedInputStream bis=new BufferedInputStream(in,1);

            bis.skip(1);
            bis.read();
            System.out.println("available is " + bis.available());

            bis.mark(2);

            System.out.print(bis.read()+",");
            System.out.print(bis.read()+",");
            System.out.print(bis.read()+",");
            bis.reset();
            System.out.print(bis.read()+",");


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void BufferInputStream() {

        try {
            FileInputStream file = new FileInputStream("IO/res/a");
            BufferedInputStream buffer = new BufferedInputStream(file, 1);



            //是否支持
//            boolean supported = buffer.markSupported();
//            System.out.println(supported);



            buffer.mark(2); //标记位置
            System.out.println( buffer.read());
            System.out.println( buffer.read());
            System.out.println( buffer.read());
            buffer.reset();  //还原到mark()位置
            System.out.println( buffer.read());


            byte[] bytes = new byte[2];
            buffer.mark(1); //标记位置
            buffer.read(bytes);
            System.out.println(bytes[0]);
            System.out.println(bytes[1]);
            buffer.read(bytes);
            System.out.println(bytes[0]);
            System.out.println(bytes[1]);

            buffer.reset();  //还原到mark()位置
            buffer.read(bytes);
            System.out.println(bytes[0]);
            System.out.println(bytes[1]);
            buffer.read(bytes);
            System.out.println(bytes[0]);
            System.out.println(bytes[1]);






//            byte[] bytes = new byte[10];
//            int n;
//            while ((n = buffer.read(bytes) )!= -1) {
//                for (int i = 0; i < n; i++) {
//                    System.out.println(bytes[i]);
//                }
//            }

            buffer.close();





        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void FileinputStream() {

        try {
            FileInputStream file = new FileInputStream("IO/res/a");



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    //使用input读字符串
    public void readString() {
        String s = "卡1卡";

        byte[] contains = s.getBytes(Charset.forName("utf-8"));


        String d = new String(contains, Charset.forName("utf-8"));
        System.out.println(d);


    }
}
