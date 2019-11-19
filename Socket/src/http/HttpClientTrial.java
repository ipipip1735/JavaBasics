package http;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2019/11/18 10:06.
 */
public class HttpClientTrial {

    String uri = "https://www.example.com/";

    public static void main(String[] args) {
        HttpClientTrial httpClientTrial = new HttpClientTrial();
//        httpClientTrial.create();
        httpClientTrial.bodyHandler();
    }

    private void bodyHandler() {


        //方式一：获取字节数组
//        try {
//
//            byte[] bytes = HttpClient.newHttpClient()
//                    .send(HttpRequest.newBuilder(new URI(uri)).build(),
//                            HttpResponse.BodyHandlers.ofByteArray())
//                    .body();
//            System.out.println(bytes.length);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }




        //方式二：获取字符串
//        try {
//
//            String body = HttpClient.newHttpClient()
//                    .send(HttpRequest.newBuilder(new URI(uri)).build(),
//                            HttpResponse.BodyHandlers.ofString())
//                    .body();
//            System.out.println(body);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }



        //方式三：获取一行字符串，返回Stream<String>
//        try {
//            Stream<String> body = HttpClient.newHttpClient()
//                    .send(HttpRequest.newBuilder(new URI(uri)).build(),
//                            HttpResponse.BodyHandlers.ofLines())
//                    .body();
//            body.forEach(System.out::println);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }


        //方式四：获取buffer
        try {
            Flow.Publisher<List<ByteBuffer>> body = HttpClient.newHttpClient()
                    .send(HttpRequest.newBuilder(new URI(uri)).build(),
                            HttpResponse.BodyHandlers.ofPublisher())
                    .body();

            body.subscribe();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }






        //方式三：获取输入流
//        try {
//
//            InputStream inputStream = HttpClient.newHttpClient()
//                    .send(HttpRequest.newBuilder(new URI(uri)).build(),
//                            HttpResponse.BodyHandlers.ofInputStream())
//                    .body();
//
//            byte[] bytes = new byte[1024 * 1];
//            int n;
//            while ((n = inputStream.read(bytes)) != -1) {
//                System.out.println(n);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }











    }

    private void create() {

        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .setHeader("xxx", "yyy")
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();

            //方式一：使用
            HttpResponse<byte[]> httpResponse = httpClient.send(httpRequest, responseInfo -> {
                System.out.println("~~BodyHandler~~");
                System.out.println("headers is " + responseInfo.headers());
                System.out.println("statusCode is " + responseInfo.statusCode());
                System.out.println("version is " + responseInfo.version());

                return HttpResponse.BodySubscribers.ofByteArray();
            });


            System.out.println("-----------");

            System.out.println("body is " + httpResponse.body().length);
            System.out.println("headers is " + httpResponse.headers());
            System.out.println("previousResponse is " + httpResponse.previousResponse());
            System.out.println("request is " + httpResponse.request());
            System.out.println("sslSession is " + httpResponse.sslSession());
            System.out.println("statusCode is " + httpResponse.statusCode());
            System.out.println("uri is " + httpResponse.uri());
            System.out.println("version is " + httpResponse.version());


        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
