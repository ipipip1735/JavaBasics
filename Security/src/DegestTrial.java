import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by Administrator on 2019/7/26 16:40.
 */

public class DegestTrial {

    String plainText;
    String messageDidgst;

    public DegestTrial() {
        this.plainText = "AAAAABBBBBBBBCCCCCCCDDDDDDDDDD";
    }

    public static void main(String[] args) {
        DegestTrial degestTrial = new DegestTrial();
        degestTrial.dgstOnce();//一次性计算出摘要
//        degestTrial.dgst();//逐次计算摘要
    }


    private void dgstOnce() {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            System.out.println("getAlgorithm is " + messageDigest.getAlgorithm());
            System.out.println("getDigestLength is " + messageDigest.getDigestLength());


            byte[] data = messageDigest.digest(plainText.getBytes());//一次性计算出摘要
            messageDidgst = Base64.getEncoder().encodeToString(data);//Base64编码后保存
            System.out.println(messageDigest.getAlgorithm() + "(" + messageDigest.getDigestLength() * 8 + ")|" + messageDidgst);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    private void dgst() {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            System.out.println("getAlgorithm is " + messageDigest.getAlgorithm());
            System.out.println("getDigestLength is " + messageDigest.getDigestLength());


            try (InputStream inputStream = Files.newInputStream(Path.of("Security\\res\\big.txt"))) {
                System.out.println("available is " + inputStream.available());
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) != -1) {
                    messageDigest.update(buffer, 0, length);//逐K计算摘要
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] data = messageDigest.digest();//完成计算
            messageDidgst = Base64.getEncoder().encodeToString(data);//Base64编码后保存
            System.out.println(messageDigest.getAlgorithm() + "(" + messageDigest.getDigestLength() * 8 + ")|" + messageDidgst);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
