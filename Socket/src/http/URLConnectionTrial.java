package http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2018/11/16 13:52.
 */

public class URLConnectionTrial {
    public static void main(String[] args) {
        URLConnectionTrial urlConnectionTrial = new URLConnectionTrial();

//        urlConnectionTrial.url();

//        urlConnectionTrial.basic();
//        urlConnectionTrial.httpPOST();
        urlConnectionTrial.httpUpload();
//        urlConnectionTrial.httpUploadTwoFile();
//        urlConnectionTrial.httpUploadMultipleFile();
//        urlConnectionTrial.httpUploadWithMixed(); //测试失败了，应该用于邮件附件


//        urlConnectionTrial.httpRedirect();
//        urlConnectionTrial.disconnect();
//        urlConnectionTrial.keepAlive();
    }

    private void url() {

        try {
            String target = "http://192.168.0.126/abc/mn.php?st=55#ff";
            URL url = new URL(target);

            System.out.println("Protocol is " + url.getProtocol());
            System.out.println("Host is " + url.getHost());
            System.out.println("Port is " + url.getPort());
            System.out.println("DefaultPort is " + url.getDefaultPort());
            System.out.println("File is " + url.getFile());
            System.out.println("Ref is " + url.getRef());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    private void httpUploadWithMixed() {
        try {
            URL url = new URL("http://192.168.0.126/upload.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");

            //设置请求头信息
            String boundaryString = UUID.randomUUID().toString().substring(0, 6);
            String subBoundaryString = UUID.randomUUID().toString().substring(0, 6);
            httpURLConnection.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);

            //发送请求
            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            //字段1
            bufferedWriter.write("--" + boundaryString + "\n");
            bufferedWriter.write("Content-Disposition: form-data; name=\"one\"" + "\n\n");
            bufferedWriter.write("111" + "\n");

            //字段2
            bufferedWriter.write("--" + boundaryString + "\n");
            bufferedWriter.write("Content-Disposition: form-data; name=\"file\"" + "\n");
            bufferedWriter.write("Content-Type: multipart/mixed;boundary=" + subBoundaryString + "\n\n");


            bufferedWriter.write("--" + subBoundaryString + "\n");
            bufferedWriter.write("Content-Disposition: form-data; name=\"file1\";filename=\"a.jpg\"" + "\n");
            bufferedWriter.write("Content-Type: image/jpeg" + "\n\n");
            bufferedWriter.flush();
            //发送w1.jpg
            FileInputStream fileInputStream = new FileInputStream(new File("Socket/res/w1.jpg"));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            int bytesRead;
            byte[] dataBuffer = new byte[1024];
            while ((bytesRead = fileInputStream.read(dataBuffer)) != -1) {
                bufferedOutputStream.write(dataBuffer, 0, bytesRead);
            }
            bufferedOutputStream.flush();


            //字段4
            bufferedWriter.write("\n--" + subBoundaryString + "\n");
            bufferedWriter.write("Content-Disposition: form-data; name=\"file2\";filename=\"b.jpg\"" + "\n");
            bufferedWriter.write("Content-Type: image/jpeg" + "\n\n");
            bufferedWriter.flush();
            //发送w2.jpg
            fileInputStream = new FileInputStream(new File("Socket/res/w2.jpg"));
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            while ((bytesRead = fileInputStream.read(dataBuffer)) != -1) {
                bufferedOutputStream.write(dataBuffer, 0, bytesRead);
            }
            bufferedOutputStream.flush();

            bufferedWriter.write("\n--" + subBoundaryString + "--\n");
            bufferedWriter.write("\n--" + boundaryString + "--\n");
            bufferedWriter.flush();

            bufferedOutputStream.close();
            bufferedWriter.close();


            //读取服务端回应
            InputStream inputStream = httpURLConnection.getInputStream();
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

    private void httpUploadMultipleFile() {
        try {
            URL url = new URL("http://192.168.0.126/upload.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setUseCaches(false);
            httpURLConnection.setChunkedStreamingMode(1024 * 2);
            httpURLConnection.setRequestProperty("Transfer-Encoding", "chunked"); //可选的，系统会自动设置


            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");

            //设置请求头信息
            String boundaryString = UUID.randomUUID().toString().substring(0, 6);
            httpURLConnection.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);

            //发送请求
            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            //字段1
            bufferedWriter.write("--" + boundaryString + "\n");
            bufferedWriter.write("Content-Disposition: form-data; name=\"one\"" + "\n\n");
            bufferedWriter.write("111" + "\n");

            //字段2
            bufferedWriter.write("--" + boundaryString + "\n");
            bufferedWriter.write("Content-Disposition: form-data; name=\"file[]\";filename=\"a.jpg\"" + "\n");
            bufferedWriter.write("Content-Type: image/jpeg" + "\n\n");
            bufferedWriter.flush();
            //发送w1.jpg
            FileInputStream fileInputStream = new FileInputStream(new File("Socket/res/w1.jpg"));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            int bytesRead;
            byte[] dataBuffer = new byte[1024];
            while ((bytesRead = fileInputStream.read(dataBuffer)) != -1) {
                bufferedOutputStream.write(dataBuffer, 0, bytesRead);
            }
            bufferedOutputStream.flush();


            //字段4
            bufferedWriter.write("\n--" + boundaryString + "\n");
            bufferedWriter.write("Content-Disposition: form-data; name=\"file[]\";filename=\"b.jpg\"" + "\n");
            bufferedWriter.write("Content-Type: image/jpeg" + "\n\n");
            bufferedWriter.flush();
            //发送w2.jpg
            fileInputStream = new FileInputStream(new File("Socket/res/w2.jpg"));
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            while ((bytesRead = fileInputStream.read(dataBuffer)) != -1) {
                bufferedOutputStream.write(dataBuffer, 0, bytesRead);
            }
            bufferedOutputStream.flush();

            bufferedWriter.write("\n--" + boundaryString + "--\n");
            bufferedWriter.flush();

            bufferedOutputStream.close();
            bufferedWriter.close();


            //读取服务端回应
            InputStream inputStream = httpURLConnection.getInputStream();
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

    private void httpUploadTwoFile() {
        try {
            URL url = new URL("http://192.168.0.126/upload.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");

            //设置请求头信息
            String boundaryString = UUID.randomUUID().toString().substring(0, 6);
            httpURLConnection.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);

            //发送请求
            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            //字段1
            bufferedWriter.write("--" + boundaryString + "\n");
            bufferedWriter.write("Content-Disposition: form-data; name=\"one\"" + "\n\n");
            bufferedWriter.write("111" + "\n");

            //字段2
            bufferedWriter.write("--" + boundaryString + "\n");
            bufferedWriter.write("Content-Disposition: form-data; name=\"file1\";filename=\"a.jpg\"" + "\n");
            bufferedWriter.write("Content-Type: image/jpeg" + "\n\n");
            bufferedWriter.flush();
            //发送w1.jpg
            FileInputStream fileInputStream = new FileInputStream(new File("Socket/res/w1.jpg"));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            int bytesRead;
            byte[] dataBuffer = new byte[1024];
            while ((bytesRead = fileInputStream.read(dataBuffer)) != -1) {
                bufferedOutputStream.write(dataBuffer, 0, bytesRead);
            }
            bufferedOutputStream.flush();


            //字段3
            bufferedWriter.write("\n--" + boundaryString + "\n");
            bufferedWriter.write("Content-Disposition: form-data; name=\"two\"" + "\n\n");
            bufferedWriter.write("222" + "\n");
            //字段4
            bufferedWriter.write("--" + boundaryString + "\n");
            bufferedWriter.write("Content-Disposition: form-data; name=\"file2\";filename=\"b.jpg\"" + "\n");
            bufferedWriter.write("Content-Type: image/jpeg" + "\n\n");
            bufferedWriter.flush();
            //发送w2.jpg
            fileInputStream = new FileInputStream(new File("Socket/res/w2.jpg"));
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            while ((bytesRead = fileInputStream.read(dataBuffer)) != -1) {
                bufferedOutputStream.write(dataBuffer, 0, bytesRead);
            }
            bufferedOutputStream.flush();

            bufferedWriter.write("\n--" + boundaryString + "--\n");
            bufferedWriter.flush();

            bufferedOutputStream.close();
            bufferedWriter.close();


            //读取服务端回应
            InputStream inputStream = httpURLConnection.getInputStream();
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

    private void httpRedirect() {
//        httpURLConnection.setInstanceFollowRedirects(false);
    }

    private void httpUpload() {
        try {
//            URL url = new URL("http://192.168.0.127/post.php");
            URL url = new URL("http://192.168.0.126:8008/post.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setChunkedStreamingMode(1024); //使用分块传送
//            httpURLConnection.setFixedLengthStreamingMode(5923); //使用固定尺寸

            //设置请求头信息
            String boundaryString = UUID.randomUUID().toString().substring(0, 6);
            httpURLConnection.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);

            //发送请求
            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("--" + boundaryString + "\n");
            bufferedWriter.write("Content-Disposition: form-data; name=\"one\"" + "\n\n");
            bufferedWriter.write("111" + "\n");

            bufferedWriter.write("--" + boundaryString + "\n");
            bufferedWriter.write("Content-Disposition: form-data; name=\"two\"" + "\n\n");
            bufferedWriter.write("222" + "\n");


            bufferedWriter.write("--" + boundaryString + "\n");
            bufferedWriter.write("Content-Disposition: form-data; name=\"file\";filename=\"a.jpg\"" + "\n");
            bufferedWriter.write("Content-Type: image/jpeg" + "\n\n");
            bufferedWriter.flush();
            //发送文件
            File file = new File("Socket/res/w1.jpg");
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            int bytesRead;
            byte[] dataBuffer = new byte[1024];
            while ((bytesRead = fileInputStream.read(dataBuffer)) != -1) {
                bufferedOutputStream.write(dataBuffer, 0, bytesRead);
            }
            bufferedOutputStream.flush();

            bufferedWriter.write("\n--" + boundaryString + "--\n");
            bufferedWriter.flush();

            bufferedOutputStream.close();
            bufferedWriter.close();


            //读取服务端回应
            InputStream inputStream = httpURLConnection.getInputStream();
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

            //发送请求主体
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