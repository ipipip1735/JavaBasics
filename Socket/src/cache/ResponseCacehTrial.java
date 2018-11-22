package cache;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2018/11/20.
 */
public class ResponseCacehTrial extends ResponseCache {
    Map<URI, File> map = null;
    String CACHE_FILE_PRIFIX = "Socket/res/";
    String CACHE_FILE_PATH = "Socket/res/httpcache";

    public static void main(String[] args) {

        try {
            ResponseCacehTrial responseCacehTrial = new ResponseCacehTrial();
            ResponseCache.setDefault(responseCacehTrial);
            URL url = new URL("https://www.baidu.com/");
//            URL url = new URL("http://192.168.0.103");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setConnectTimeout(1000);
            conn.setReadTimeout(1000);

            InputStream inputStream = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String s;
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }
            bufferedReader.close();
//            http.disconnect();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        System.out.println("[save]" + map);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(new File(CACHE_FILE_PATH)));
            objectOutputStream.writeObject(map);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<URI, File> getMap() {

        if (Objects.isNull(map)) {

            if (Files.exists(Paths.get(CACHE_FILE_PATH))) {
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(
                            new FileInputStream(new File(CACHE_FILE_PATH)));
                    map = (Map<URI, File>) objectInputStream.readObject();
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                map = new HashMap<>();
                save();
            }
        }

        return map;
    }

    @Override
    public CacheResponse get(URI uri, String rqstMethod, Map<String, List<String>> rqstHeaders) {
        System.out.println("*********  " + getClass().getSimpleName() + ".get  *********");

        System.out.println("rqstMethod is " + rqstMethod);
        System.out.println("rqstHeaders is " + rqstHeaders);

        if (getMap().containsKey(uri)) {
            System.out.println("get hit");
            return new cache.CacheResponseTrial(getMap().get(uri).toString());
        }
        System.out.println("get miss");
        return null;
    }

    @Override
    public CacheRequest put(URI uri, URLConnection conn) {
        System.out.println("*********  " + getClass().getSimpleName() + ".put  *********");

        Map<String, List<String>> rspHeader = conn.getHeaderFields();
        System.out.println(rspHeader);

        if (!(getMap().containsKey(uri))) {
            System.out.println("put miss");
            File file = new File(CACHE_FILE_PRIFIX +
                    UUID.randomUUID().toString().substring(0, 6));
            getMap().put(uri, file);
            save();
            CacheRequest cacheRequest = new cache.CacheRequestTrial(file.toString(), rspHeader);
            return cacheRequest;
        }
        return null;
    }
}
