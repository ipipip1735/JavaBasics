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
        cookieManagerTrial.connectWithCookie();
    }

    private void connectWithCookie() {
        try {


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
            HttpCookie httpCookie = new HttpCookie("so", "sd");



            String urlString = "http://192.168.0.126:8008/sub/showCookies.php";
            URL url = new URL(urlString);

            cookieManager.getCookieStore().add(url.toURI(),  httpCookie);
            URLConnection urlConnection = url.openConnection();

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
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }

    private void basic() {
        try {

            //绑定CookieManager对象方法一
//            CookieManager cookieManager = new CookieManager(null, new CookiePolicy() {
//                @Override
//                public boolean shouldAccept(URI uri, HttpCookie cookie) {
//                    System.out.println("uri is " + uri);
//                    System.out.println("cookie is " + cookie);
//                    return true;
//                }
//            });
//            CookieHandler.setDefault(cookieManager);

            //方法二
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
            urlConnection.getContent();

            CookieStore cookieStore = cookieManager.getCookieStore();
            List<HttpCookie> cookies = cookieStore.getCookies();

            //获取URI
            for (URI uri : cookieStore.getURIs()) {
                System.out.println(uri);

            }

            //获取Cookie
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
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
