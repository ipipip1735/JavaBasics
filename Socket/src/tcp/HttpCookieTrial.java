package tcp;

import java.io.IOException;
import java.net.*;
import java.util.List;

/**
 * Created by Administrator on 2018/11/16 14:49.
 */

public class HttpCookieTrial {
    public static void main(String[] args) {
        HttpCookieTrial httpCookieTrial = new HttpCookieTrial();
//        try {
//            httpCookieTrial.baise();
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        httpCookieTrial.parse();

    }

    private void parse() {

        String s = "set-cookie:FEED_SIDS=304607_1116_16; path=/; domain=.baidu.com; BDSVRTM=231; path=/";
        List<HttpCookie> httpCookies = HttpCookie.parse(s);
        System.out.println(httpCookies);

    }

    private void baise() throws IOException {

//        String urlString = "http://m.yssp88.com/android.php";
        String urlString = "https://m.baidu.com";
        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
        URL url = new URL(urlString);
        URLConnection urlConnection = url.openConnection();
        urlConnection.getContent();
        CookieStore cookieStore = cookieManager.getCookieStore();
        List<HttpCookie> cookies = cookieStore.getCookies();




        int cookieIdx = 0;
        for (HttpCookie ck : cookies) {

            System.out.println("------------------ Cookie." + ++cookieIdx + " ------------------");
            //Get the cookie name
            System.out.println("Cookie name: " + ck.getName());

            //Get the domain set for the cookie
            System.out.println("Domain: " + ck.getDomain());
            //Get the max age of the cookie
            System.out.println("Max age: " + ck.getMaxAge());
            //Get the path of the server
            System.out.println("Server path: " + ck.getPath());
            //Get boolean if the cookie is being restricted to a secure protocol
            System.out.println("Is secured: " + ck.getSecure());
            //Gets the value of the cookie
            System.out.println("Cookie value: " + ck.getValue());
            //Gets the version of the protocol with which the given cookie is related.
            System.out.println("Cookie protocol version: " + ck.getVersion());

            System.out.println("toString: " + ck);

        }



    }
}
