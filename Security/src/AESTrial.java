import javax.crypto.*;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

/**
 * Created by Administrator on 2019/7/24 17:08.
 */

public class AESTrial {

    String cipherText;
    String plainText;
    String passwd;
    SecretKey secretKey;
    byte[] salt;


    public AESTrial() {
        plainText = "ABCD";
        passwd = "chris";
        salt = new byte[8];
        new SecureRandom().nextBytes(salt);
    }

    public static void main(String[] args) {

        AESTrial aesTrial = new AESTrial();

        aesTrial.secretkey();


        aesTrial.encrypt();
        aesTrial.decrypt();

    }

    private void secretkey() {

        //方式一
//        try {
//
//            PBEKeySpec pbeKeySpec = new PBEKeySpec(passwd.toCharArray(), salt, 1, 256);
//
//            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//            SecretKey secretKey = factory.generateSecret(pbeKeySpec);
//
//            this.secretKey = new SecretKeySpec(secretKey.getEncoded(), "AES");
//
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (InvalidKeySpecException e) {
//            e.printStackTrace();
//        }


        //方式二
//        try {
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//            keyGenerator.init(256);
//            secretKey = keyGenerator.generateKey();
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }


        //方式三
//        try {
//            MessageDigest digester = MessageDigest.getInstance("SHA-256");
//            digester.update(passwd.getBytes());
//            byte[] key = digester.digest();
//
//
//            secretKey = new SecretKeySpec(key, "AES");
//
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }


        //方式四
        byte[] key = new byte[16];
//        byte[] key = new byte[24];
//        byte[] key = new byte[32];
        Arrays.fill(key, (byte) 0);

        secretKey = new SecretKeySpec(key, "AES");

    }


    private void encrypt() {

        try {
            byte[] iv = new byte[16];
            Arrays.fill(iv, (byte) 0);

            IvParameterSpec ivspec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec, new SecureRandom());

            byte[] data = cipher.doFinal(plainText.getBytes());

            cipherText = new String(Base64.getEncoder().encode(data));
            System.out.println(cipherText);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    private void decrypt() {

        try {
            byte[] iv = new byte[16];
            Arrays.fill(iv, (byte) 0);

            IvParameterSpec ivspec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec, new SecureRandom());


            byte[] data = Base64.getDecoder().decode(cipherText);
            data = cipher.doFinal(data);
            plainText = new String(data);
            System.out.println(plainText);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

}
