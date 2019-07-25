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

    String cipherText;
    String plainText = "ABCD";
    String passwd = "chris";

    public static void main(String[] args) {

        AESTrial aesTrial = new AESTrial();

//        for (int i = 0; i < 20; i++) {
//
//            aesTrial.passwd += aesTrial.passwd;
//        }
//        System.out.println(aesTrial.passwd);


        aesTrial.pbekey();
//        aesTrial.genKey();
        aesTrial.encrypt();
//        aesTrial.decrypt();


    }

    private void pbekey() {



    }

    private void genKey() {
        //创建AES密钥(Key类型)
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256, new SecureRandom(passwd.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();

            System.out.println(secretKey.getAlgorithm());
            System.out.println(secretKey.getFormat());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        //创建AES密钥(KeySpec类型)
//        String passwd = "chris";
//        SecretKeySpec secretKeySpec = new SecretKeySpec(passwd.getBytes(), "AES");
//        secretKeySpec.getAlgorithm();


    }

    private void decrypt() {


        try {
            //创建AES密钥(Key)
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            SecretKey secretKey = keyGenerator.generateKey();

            //创建AES密钥(KeySpec)
            SecretKeySpec secretKeySpec = new SecretKeySpec(passwd.getBytes(), "AES");
            secretKeySpec.getAlgorithm();


            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec, new SecureRandom());
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivspec, new SecureRandom());


            byte[] data = Base64.getDecoder().decode(cipherText);
            cipher.doFinal(data);

//            plainText = new String(data);
//            System.out.println(plainText);


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








//        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//        IvParameterSpec ivspec = new IvParameterSpec(iv);
//
//        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//        KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
//        SecretKey tmp = factory.generateSecret(spec);
//        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
//
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
//        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));





    }

    private void encrypt() {


        try {
            //创建AES密钥(Key)
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            SecretKey secretKey = keyGenerator.generateKey();

            //创建AES密钥(KeySpec)
            SecretKeySpec secretKeySpec = new SecretKeySpec(passwd.getBytes(), "AES");
            secretKeySpec.getAlgorithm();


            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec, new SecureRandom());
//            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivspec, new SecureRandom());

            System.out.println("getAlgorithm is "+  cipher.getAlgorithm());


            byte[] data = cipher.doFinal(plainText.getBytes());

            cipherText = Base64.getEncoder().encodeToString(data);
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




//        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//        IvParameterSpec ivspec = new IvParameterSpec(iv);
//
//        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//        KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
//        SecretKey tmp = factory.generateSecret(spec);
//        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
//
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
//        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));





    }
}
