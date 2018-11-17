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

        httpCookieTrial.parse();
        httpCookieTrial.check();

    }

    private void check() {
        String domain = ".a";
        String host = "n.a";
        boolean b = HttpCookie.domainMatches(domain, host);
        System.out.println(b);
    }


    private void parse() {

        String s = "set-cookie:one=11; max-age=3600; path=/one";
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
