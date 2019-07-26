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
        degestTrial.dgst();
    }

    private void dgst() {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            System.out.println("getAlgorithm is " + messageDigest.getAlgorithm());
            System.out.println("getDigestLength is " + messageDigest.getDigestLength());


            messageDigest.update(plainText.getBytes());
            byte[] data = messageDigest.digest();


            messageDidgst = Base64.getEncoder().encodeToString(data);
            System.out.println(messageDidgst);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
