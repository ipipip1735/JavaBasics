package cache;

import java.io.*;
import java.net.CacheResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/20.
 */
public class CacheResponseTrial extends CacheResponse {
    FileInputStream fis;
    Map<String, List<String>> headers;
    public CacheResponseTrial(String filename) {
        System.out.println("=======  " + getClass().getSimpleName() + "  =======");
        System.out.println("filename is " + filename);

        try {
            fis = new FileInputStream(new File(filename));
            ObjectInputStream ois = new ObjectInputStream (fis);
            headers = (Map<String, List<String>>)  ois.readObject();
        } catch (IOException ex) {
            // handle exception
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public InputStream getBody() throws IOException {
        System.out.println("*********  " + getClass().getSimpleName() + ".getBody  *********");

        return fis;
    }

    public Map getHeaders() throws IOException {
        System.out.println("*********  " + getClass().getSimpleName() + ".getHeaders  *********");

        return headers;
    }
}
