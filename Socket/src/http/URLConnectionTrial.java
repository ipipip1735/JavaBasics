package http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
        urlConnectionTrial.httpPOST();
//        urlConnectionTrial.httpUpload();
//        urlConnectionTrial.httpRedirect();
//        urlConnectionTrial.disconnect();
//        urlConnectionTrial.keepAlive();
    }

    private void httpRedirect() {
//        httpURLConnection.setInstanceFollowRedirects(false);
    }

    private void httpUpload() {
//        try {
//            URL url = new URL("http://192.168.0.126/upload.php");
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
////            httpURLConnection.setDoInput(true);
//
//            String w = "param1=a&param2=b&param3=阿航";
//            byte[] postData = w.getBytes(UTF_8);
//            int postDataLength = postData.length;
//
//            httpURLConnection.setUseCaches(false);
//            httpURLConnection.setDoOutput(true);
//            httpURLConnection.setRequestMethod("POST");
//
//            //设置请求头信息
//            String charset = "utf-8";
//            httpURLConnection.setRequestProperty("Accept-Charset", charset);
//            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
//
//            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
//
//
//            OutputStream outputStream = httpURLConnection.getOutputStream();
//            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
//            BufferedWriter bufferedWriter = new BufferedWriter(writer);
//            bufferedWriter.write(w);
//            bufferedWriter.close();
//
//
//            InputStream inputStream = httpURLConnection.getInputStream();
//            InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
//            BufferedReader bufferedReader = new BufferedReader(reader);
//
//            String r;
//            while ((r = bufferedReader.readLine()) != null) {
//                System.out.println(r);
//            }
//            bufferedReader.close();
//
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void httpPOST() {
        try {
            URL url = new URL("http://192.168.0.126/upload.php");
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

            //准备上传数据
            String w = "param1=a&param2=b&param3=阿航";
            byte[] postData = w.getBytes(UTF_8);
            int postDataLength = postData.length;

            httpConn.setUseCaches(false);
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("POST");

            //设置请求头信息
            String charset = "utf-8";
            httpConn.setRequestProperty("Accept-Charset", charset);
            httpConn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);


            OutputStream outputStream = httpConn.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(w);
            bufferedWriter.close();


            InputStream inputStream = httpConn.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String r;
            while ((r = bufferedReader.readLine()) != null) {
                System.out.println(r);
            }
            bufferedReader.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void keepAlive() {

        try {
//            URL url = new URL("http://192.168.0.126:8008/cookies.php");
            URL url = new URL("http://192.168.0.127/upload.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            //基于属性设置
//            System.setProperty("http.keepAlive", "false"); //默认是开启的
//            System.setProperty("http.maxConnections", "15");
//            httpURLConnection.connect();


//            //基于请求头的设置
////            httpURLConnection.setRequestProperty("Connection", "close"); //禁用长连接
//            System.out.println(httpURLConnection.getRequestProperties());//查询是否设置成功
//            httpURLConnection.connect();


            System.out.println(httpURLConnection.getHeaderFields());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void disconnect() {

        try {
            URL url = new URL("http://192.168.0.126:8008/cookies.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

//            InputStream inputStream = httpURLConnection.getInputStream();
//            InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
//            BufferedReader bufferedReader = new BufferedReader(reader);


//            httpURLConnection.disconnect();

//            String s;
//            while ((s = bufferedReader.readLine()) != null) {
//                System.out.println(s);
//            }
//            bufferedReader.close();


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
//            InputStream inputStream = urlConnection.getInputStream();
//            InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
//            BufferedReader bufferedReader = new BufferedReader(reader);
//
//            String s;
//            while ((s = bufferedReader.readLine()) != null) {
//                System.out.println(s);
//            }
//            bufferedReader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}