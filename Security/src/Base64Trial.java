import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import static java.security.KeyFactory.getInstance;

/**
 * Created by Administrator on 2019/7/21 17:03.
 */

public class Base64Trial {
    public static void main(String[] args) {
        Base64Trial base64Trial = new Base64Trial();

        base64Trial.encoding();


    }

    private void encoding() {

        String encoding;
        byte[] bytes = Base64.getEncoder().encode("aa".getBytes());//编码
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i] + ", ");
        }

        try {
            encoding = new String(bytes, "UTF-8");
            System.out.println(encoding);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        encoding = Base64.getEncoder().encodeToString("aa".getBytes());//编码
        System.out.println(encoding);

//        Base64.getDecoder().decode(bytes);
    }
}
