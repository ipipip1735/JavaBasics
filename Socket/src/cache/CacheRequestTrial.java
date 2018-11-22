package cache;

import java.io.*;
import java.net.CacheRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/20.
 */
public class CacheRequestTrial extends CacheRequest {
    FileOutputStream fos;
    File file;

    public CacheRequestTrial(String filename, Map<String, List<String>> rspHeaders) {
        System.out.println("=======  " + getClass().getSimpleName() + "  =======");

        try {
            file = new File(filename);
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(rspHeaders);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public OutputStream getBody() throws IOException {
        System.out.println("*********  " + getClass().getSimpleName() + ".getBody  *********");

        return fos;
    }

    public void abort() {
        System.out.println("*********  " + getClass().getSimpleName() + ".abort  *********");

        // we abandon the cache by close the stream,
        // and delete the file
        try {
            fos.close();
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
