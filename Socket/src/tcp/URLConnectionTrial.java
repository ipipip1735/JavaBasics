package tcp;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2018/11/16 13:52.
 */

public class URLConnectionTrial {
    public static void main(String[] args) {
        URLConnectionTrial urlConnectionTrial = new URLConnectionTrial();
        urlConnectionTrial.basic();
    }

    private void basic() {

        try {

            URLConnection urlConnection = new URL("https://www.baidu.com").openConnection();
            urlConnection
                    .connect();


            //设置请求头信息
            urlConnection.setConnectTimeout(3000);
            urlConnection.setReadTimeout(2000);




            //获取回应头信息
            System.out.println(urlConnection.getHeaderFields());


            //获取回应主体
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String s;
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }
            bufferedReader.close();




        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
