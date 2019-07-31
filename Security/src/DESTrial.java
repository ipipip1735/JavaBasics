import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

/**
 * Created by Administrator on 2019/7/25 9:15.
 */

public class DESTrial {


    String cipherText;
    String plainText = "ABCD";
    String passwd = "chris";
    SecretKey secretKey;

    public static void main(String[] args) {

        DESTrial desTrial = new DESTrial();


//        desTrial.genKey();
        desTrial.secretkey();

        desTrial.encrypt();
        desTrial.decrypt();


    }
    private void secretkey() {


        //方式一
//        try {
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
//            keyGenerator.init(56, new SecureRandom(passwd.getBytes()));
//
//            secretKey = keyGenerator.generateKey();
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }


        //方式二
        byte[] key = new byte[24];
        Arrays.fill(key, (byte) 0);
        secretKey = new SecretKeySpec(key, "DESede");



    }


    private void encrypt() {

        try {
            byte[] iv = new byte[8];
            Arrays.fill(iv, (byte) 0);
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
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


            byte[] iv = new byte[8];
            Arrays.fill(iv, (byte) 0);
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
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
