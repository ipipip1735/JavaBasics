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
import java.nio.file.Path;
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



//        ofByteArray();//获取字节数组
//        ofString();//获取字符串
//        ofLines();//获取一行字符串，返回Stream<String>
//        ofInputStream();//获取输入流
//        ofPublisher();//获取订阅器

        discarding();//丢弃响应


//        ofFileDownload();//下载文件
//        ofFile();//上传文件


    }

    private void discarding() {

//        try {
//            HttpClient.newHttpClient()
//                    .send(HttpRequest.newBuilder(new URI(uri))
//                            .POST()
//                                    .build(),
//                            HttpResponse.BodyHandlers.discarding()
//                            .apply();

//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }

    }

    private void ofFile() {

        try {
            Path body = HttpClient.newHttpClient()
                    .send(HttpRequest.newBuilder(new URI(uri)).build(),
                            HttpResponse.BodyHandlers.ofFile(Path.of("Socket/res/w1.jpg")))
                    .body();

            System.out.println(body);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    private void ofFileDownload() {

        String uri = "http://static-aliyun-doc.oss-cn-hangzhou.aliyuncs.com/download%2Fpdf%2F122061%2F%25E4%25BA%2591%25E5%25BA%2594%25E7%2594%25A8%25E5%25BC%2580%25E5%258F%2591%25E6%258C%2587%25E5%258D%2597_cn_zh-CN.pdf?spm=a2c4g.11186623.2.4.252563a0WF0O85&file=download%2Fpdf%2F122061%2F%25E4%25BA%2591%25E5%25BA%2594%25E7%2594%25A8%25E5%25BC%2580%25E5%258F%2591%25E6%258C%2587%25E5%258D%2597_cn_zh-CN.pdf";
//        String uri = "https://tools.ietf.org/rfc/rfc2606.txt";
        try {
            Path body = HttpClient.newHttpClient()
                    .send(HttpRequest.newBuilder(new URI(uri)).build(),
                            HttpResponse.BodyHandlers.ofFileDownload(Path.of("Socket/res/")))
                    .body();

            System.out.println(body);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }

    private void ofPublisher() {
        try {
            Flow.Publisher<List<ByteBuffer>> body = HttpClient.newHttpClient()
                    .send(HttpRequest.newBuilder(new URI(uri)).build(),
                            HttpResponse.BodyHandlers.ofPublisher())
                    .body();

            body.subscribe(new Flow.Subscriber<List<ByteBuffer>>() {
                @Override
                public void onSubscribe(Flow.Subscription subscription) {
                    System.out.println("~~onSubscribe~~");
                    System.out.println("subscription is " + subscription);

                    subscription.request(1);
                }

                @Override
                public void onNext(List<ByteBuffer> item) {
                    System.out.println("~~onNext~~");
                    System.out.println("item is " + item);
                }

                @Override
                public void onError(Throwable throwable) {
                    System.out.println("~~onNext~~");
                    System.out.println("throwable is " + throwable);

                }

                @Override
                public void onComplete() {
                    System.out.println("~~onComplete~~");
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void ofInputStream() {
        try {

            InputStream inputStream = HttpClient.newHttpClient()
                    .send(HttpRequest.newBuilder(new URI(uri)).build(),
                            HttpResponse.BodyHandlers.ofInputStream())
                    .body();

            byte[] bytes = new byte[1024 * 1];
            int n;
            while ((n = inputStream.read(bytes)) != -1) {
                System.out.println(n);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void ofLines() {
        try {
            Stream<String> body = HttpClient.newHttpClient()
                    .send(HttpRequest.newBuilder(new URI(uri)).build(),
                            HttpResponse.BodyHandlers.ofLines())
                    .body();
            body.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void ofString() {
        try {

            String body = HttpClient.newHttpClient()
                    .send(HttpRequest.newBuilder(new URI(uri)).build(),
                            HttpResponse.BodyHandlers.ofString())
                    .body();
            System.out.println(body);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void ofByteArray() {
                try {

            byte[] bytes = HttpClient.newHttpClient()
                    .send(HttpRequest.newBuilder(new URI(uri)).build(),
                            HttpResponse.BodyHandlers.ofByteArray())
                    .body();
            System.out.println(bytes.length);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
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
