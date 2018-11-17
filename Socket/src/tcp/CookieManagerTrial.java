package tcp;

import java.io.IOException;
import java.net.*;
import java.util.List;

/**
 * Created by Administrator on 2018/11/17 15:09.
 */

public class CookieManagerTrial {
    public static void main(String[] args) {
        CookieManagerTrial cookieManagerTrial = new CookieManagerTrial();
        cookieManagerTrial.basic();
    }

    private void basic() {
        try {

            CookieManager cookieManager = new CookieManager();
            CookieHandler.setDefault(cookieManager);






//        String urlString = "http://m.yssp88.com/android.php";
            String urlString = "https://m.baidu.com";
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();

            urlConnection.getHeaderFields();
//            urlConnection.getContent();

            CookieStore cookieStore = cookieManager.getCookieStore();
            List<HttpCookie> cookies = cookieStore.getCookies();

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
