import java.io.*;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

import static java.security.KeyFactory.getInstance;

/**
 * Created by Administrator on 2019/7/21 17:03.
 */

public class Base64Trial {
    public static void main(String[] args) {
        Base64Trial base64Trial = new Base64Trial();
        base64Trial.encoding(); //基本使用



//        base64Trial.decodeFile(new File("src/main/resources/onrar_encode"), new File("out_decode"));
        base64Trial.encodeFile(new File("target/classes/onrar"), new File("target/classes/onrar_encode"));
    }


    private void encodeFile(File src, File des) {


        try (FileInputStream fis = new FileInputStream(src);
             BufferedInputStream bis = new BufferedInputStream(fis);
             FileOutputStream fos = new FileOutputStream(des);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] buffer = new byte[1024];
            int length;
            Base64.Encoder encoder = Base64.getEncoder();
            while ((length = bis.read(buffer)) != -1) {
                System.out.println("length = " + length);
                if (length != 1024) buffer = Arrays.copyOf(buffer, length);

                byte[] encode = encoder.encode(buffer);
                System.out.println("encode.length = " + encode.length);
                String s = new String(encode);
                System.out.println("s = " + s);
                bos.write(s.getBytes());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void decodeFile(File src, File des) {
        if (!src.exists()) {
            System.out.println("src exists is false");
            return;
        }

        try (FileInputStream fis = new FileInputStream(src);
             BufferedInputStream bis = new BufferedInputStream(fis);
             FileOutputStream fos = new FileOutputStream(new File("out_decode"));
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] buffer = new byte[1368];
            int length;
            Base64.Decoder decoder = Base64.getDecoder();
            while ((length = bis.read(buffer)) != -1) {
                System.out.println("length = " + length);
                if (length != 1368) buffer = Arrays.copyOf(buffer, length);

                byte[] decode = decoder.decode(buffer);
                System.out.println("decode.length = " + decode.length);
//                String s = new String(decode);
//                System.out.println("s = " + s);
                bos.write(decode);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
