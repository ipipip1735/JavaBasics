package http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Created by Administrator on 2019/11/18 10:06.
 */
public class HttpClientTrial {

    String uri = "https://www.example.com/";

    public static void main(String[] args) {
        HttpClientTrial httpClientTrial = new HttpClientTrial();
        httpClientTrial.create();
        httpClientTrial.bodyHandler();
    }

    private void bodyHandler() {

        try {

            HttpClient.newHttpClient()
                    .send(HttpRequest.newBuilder().build(), HttpResponse.BodyHandlers.ofString())
                    .body();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
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
