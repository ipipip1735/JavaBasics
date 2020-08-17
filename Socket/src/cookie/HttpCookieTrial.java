package cookie;

import java.net.*;
import java.util.List;

/**
 * Created by Administrator on 2018/11/16 14:49.
 */

public class HttpCookieTrial {
    public static void main(String[] args) {
        HttpCookieTrial httpCookieTrial = new HttpCookieTrial();

//        httpCookieTrial.parse();
        httpCookieTrial.check();

    }

    private void check() {
//        String domain = ".b.c";//返回true
//        String host = ".b.c";

//        String domain = "b.c";//返回true
//        String host = "b.c";

//        String domain = ".b.c";//返回true
//        String host = "a.b.c";

//        String domain = "b.c";//返回false
//        String host = "a.b.c";

//        String domain = ".b.c";//返回false
//        String host = "k.a.b.c";

//        String domain = ".local";//返回true
//        String host = "a.local";

//        String domain = "local";//返回false
//        String host = "a.local";

//        String domain = "a.local";//返回true
//        String host = "a";

//        String domain = "a.b.local";//返回false
//        String host = "a.b";

        String domain = ".local";//返回false
        String host = "a";



//        String domain = "192.168.0.1";
//        String host = "192.168.0.1";
        boolean b = HttpCookie.domainMatches(domain, host);
        System.out.println(b);
    }

    private void parse() {

        String s = "set-cookie:one=11; max-age=3600; path=/one";//使用set-cookie语法
//        String s = "set-cookie2:one=11;path=/one, two=22;path=/two";//使用set-cookie2语法
        List<HttpCookie> httpCookies = HttpCookie.parse(s);
        int cookieIdx = 0;
        for (HttpCookie ck : httpCookies) {
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
    }
}
