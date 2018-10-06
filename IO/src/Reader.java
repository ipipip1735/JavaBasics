import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Administrator on 2018/9/30 14:43.
 */

public class Reader {
    public static void main(String[] args) {
        Reader reader = new Reader();

        reader.base();

    }

    private void base() {

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
}
