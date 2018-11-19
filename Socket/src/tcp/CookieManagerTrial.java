package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2018/11/17 15:09.
 */

public class CookieManagerTrial {
    public static void main(String[] args) {
        CookieManagerTrial cookieManagerTrial = new CookieManagerTrial();
//        cookieManagerTrial.basic();
//        cookieManagerTrial.connectWithCookie();
        cookieManagerTrial.requestAndCookieManager();
    }

    private void requestAndCookieManager() {
        try {
            String urlString = "http://192.168.0.126:8008/cookies.php";
            URL url = new URL(urlString);


            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(new CookiePolicy() {
                @Override
                public boolean shouldAccept(URI uri, HttpCookie cookie) {
                    System.out.println("uri is " + uri);
                    System.out.println("cookie is " + cookie);
                    return true;
                }
            });
            CookieHandler.setDefault(cookieManager);

            System.out.println("------first request------");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            url.getContent();
            System.out.println("-----Cookie------");
            for (HttpCookie cookie : cookieManager.getCookieStore().getCookies()) {
                System.out.println(cookie);
            }
            httpURLConnection.disconnect();
//            urlConnection.disconnect();


            System.out.println("------second request------");
            InputStream inputStream = (InputStream) url.getContent();
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

    private void connectWithCookie() {
        try {
            String urlString = "http://192.168.0.126:8008/cookies.php";
            URL url = new URL(urlString);


            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(new CookiePolicy() {
                @Override
                public boolean shouldAccept(URI uri, HttpCookie cookie) {
                    System.out.println("uri is " + uri);
                    System.out.println("cookie is " + cookie);
                    return true;
                }
            });

            //增加Cookie到Cookie池
            HttpCookie httpCookie = new HttpCookie("myCookie", "ccc");
            httpCookie.setPath("/");
            cookieManager.getCookieStore().add(url.toURI(), httpCookie);


            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            //设置请求头Cookie字段
            httpURLConnection.setRequestProperty("Cookie", "myCookie=MMM; myCookie2=NNN");


            //读取回应主体
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
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }

    private void basic() {
        try {

            //设置CookiePolicy方法一
//            CookieManager cookieManager = new CookieManager(null, new CookiePolicy() {
//                @Override
//                public boolean shouldAccept(URI uri, HttpCookie cookie) {
//                    System.out.println("uri is " + uri);
//                    System.out.println("cookie is " + cookie);
//                    return true;
//                }
//            });
//            CookieHandler.setDefault(cookieManager);


            //设置CookiePolicy方法二
            CookieManager cookieManager = new CookieManager();
            CookieHandler.setDefault(cookieManager);
            cookieManager.setCookiePolicy(new CookiePolicy() {
                @Override
                public boolean shouldAccept(URI uri, HttpCookie cookie) {
                    System.out.println("uri is " + uri);
                    System.out.println("cookie is " + cookie);
                    return true;
                }
            });


            String urlString = "http://192.168.0.126:8008/cookies.php";
//            String urlString = "https://m.baidu.com";
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            urlConnection.getContent(); //发起链接


            CookieStore cookieStore = cookieManager.getCookieStore();
            List<HttpCookie> cookies = cookieStore.getCookies();

            //获取URI
            System.out.println("------URI-------");
            for (URI uri : cookieStore.getURIs()) {
                System.out.println("uri is " + uri);
            }

            //获取Cookie
            System.out.println("------Cookie-------");
            int cookieIdx = 0;
            for (HttpCookie ck : cookies) {
                System.out.println("-- Cookie." + ++cookieIdx + " --");
                System.out.println("Cookie name: " + ck.getName());
                System.out.println("Domain: " + ck.getDomain());
                System.out.println("Max age: " + ck.getMaxAge());
                System.out.println("Server path: " + ck.getPath());
                System.out.println("Is secured: " + ck.getSecure());
                System.out.println("Cookie value: " + ck.getValue());
                System.out.println("Cookie protocol version: " + ck.getVersion());
                System.out.println("toString: " + ck);
            }

            //获取回应体
            System.out.println("------Body-------");
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
