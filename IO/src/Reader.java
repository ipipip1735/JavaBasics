import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2018/9/30 14:43.
 */

public class Reader {
    public static void main(String[] args) {
        Reader reader = new Reader();

//        reader.read();
        reader.bufferedRead();


    }

    private void bufferedRead() {

        try {
            Path path = Paths.get("IO/res/a");
            InputStream inputStream = new FileInputStream(path.toFile());
            InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String s;
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void read() {

        try {
            InputStream stream = new FileInputStream("IO/res/a");
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            final StringBuilder builder = new StringBuilder(128);
            char[] buffer = new char[8192];
            int len;
            while ((len = reader.read(buffer)) > 0) {
                builder.append(buffer, 0, len);
            }
            String result = builder.toString();
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
