import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016/10/9.
 */
public class Input {


    public static void main(String[] args) {

        Input myInput = new Input();
//        myInput.readString();
//        myInput.inputStream();
        myInput.inputStreamRead();


    }

    private void inputStreamRead() {
        try {
            InputStream stream = new FileInputStream("IO/res/a");
            InputStreamReader  reader  =  new  InputStreamReader(stream,  "UTF-8");
            final  StringBuilder builder  =  new  StringBuilder(128);
            char[]  buffer  =  new char[8192];
            int  len;
            while((len=reader.read(buffer))  >0  ){
                builder.append(buffer,  0,  len);
            }
            String  result  =  builder.toString();
            System.out.println(result);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
