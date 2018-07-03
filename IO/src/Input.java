import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016/10/9.
 */
public class Input {


    public static void main(String[] args) {

        Input myInput = new Input();
//        myInput.readString();
        myInput.inputStream();


    }

    private void inputStream() {

        try {
            FileInputStream file = new FileInputStream("IO/resource/a");
            BufferedInputStream BufferedInputStream = new BufferedInputStream(file);


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
