package tcp;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2018/11/16 13:52.
 */

public class URLConnectionTrial {
    public static void main(String[] args) {
        URLConnectionTrial urlConnectionTrial = new URLConnectionTrial();
//        urlConnectionTrial.basic();
        urlConnectionTrial.disconnect();
        urlConnectionTrial.keepAlive();
    }

    private void keepAlive() {

        try {
            URL url = new URL("http://192.168.0.126:8008/cookies.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Connection", "close");
            httpURLConnection.connect();





        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void disconnect() {

        try {
            URL url = new URL("http://192.168.0.126:8008/cookies.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
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

    private void basic() {

        try {

            URL url = new URL("http://192.168.0.126:8008/cookies.php");
            URLConnection urlConnection = url.openConnection();

            //设置请求头信息
            urlConnection.setConnectTimeout(3000);
            urlConnection.setReadTimeout(2000);

            //设置Cookie
            urlConnection.setRequestProperty("Cookie", "sessionID=123; name=chirs");
            System.out.println(urlConnection.getRequestProperties());


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