import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Created by Administrator on 2019/7/24 17:08.
 */

public class AESTrial {
    public static void main(String[] args) {

        AESTrial aesTrial = new AESTrial();
        aesTrial.encrypt();
        aesTrial.decrypt();


    }

    private void decrypt() {

        try {
            //创建AES密钥(Key)
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//            keyGenerator.init(256);
//            SecretKey secretKey = keyGenerator.generateKey();

            //创建AES密钥(KeySpec)
            String pass = "chris";
            SecretKeySpec secretKeySpec = new SecretKeySpec(pass.getBytes(), "AES");
            secretKeySpec.getAlgorithm();


            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec, new SecureRandom());
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivspec, new SecureRandom());


            String strToEncrypt = "asdflkjxcovjlds";
            byte[]data = cipher.doFinal(strToEncrypt.getBytes());

            String  strEnc = Base64.getEncoder().encodeToString(data);
            System.out.println(strEnc);


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

    private void encrypt() {


        try {
            //创建AES密钥(Key)
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//            keyGenerator.init(256);
//            SecretKey secretKey = keyGenerator.generateKey();

            //创建AES密钥(KeySpec)
            String pass = "chris";
            SecretKeySpec secretKeySpec = new SecretKeySpec(pass.getBytes(), "AES");
            secretKeySpec.getAlgorithm();


            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec, new SecureRandom());
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivspec, new SecureRandom());


            String strToEncrypt = "asdflkjxcovjlds";
            byte[]data = cipher.doFinal(strToEncrypt.getBytes());

            String  strEnc = Base64.getEncoder().encodeToString(data);
            System.out.println(strEnc);


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
