import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

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
        plainText = "0123456789abcdefghijklnmopqrstuvwxyzABCDEFGHIJKLNMOPQRSTUVWXYZ";
        passwd = "chris";
        salt = new byte[8];
        new SecureRandom().nextBytes(salt);
    }

    public static void main(String[] args) {

        AESTrial aesTrial = new AESTrial();

        aesTrial.secretkey();


//        aesTrial.encryptOnce();
//        aesTrial.decryptOnce();

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
        byte[] DESkey = new byte[16]; //DES算法密钥长度
        byte[] DESedekey = new byte[24]; //DESede算法密钥长度
        byte[] AESkey = new byte[32]; //AES算法密钥长度
        Arrays.fill(AESkey, (byte) 0);

        secretKey = new SecretKeySpec(AESkey, "AES");

    }


    private void encryptOnce() {

        try {
            byte[] iv = new byte[16];
            Arrays.fill(iv, (byte) 0);

            IvParameterSpec ivspec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec, new SecureRandom());

            byte[] data = cipher.doFinal(plainText.getBytes());
            System.out.println("data length is " + data.length);

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

    private void decryptOnce() {

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


    private void encrypt() {

        try {
            byte[] iv = new byte[16];
            Arrays.fill(iv, (byte) 0);

            IvParameterSpec ivspec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec, new SecureRandom());

            try (InputStream inputStream = Files.newInputStream(Paths.get("Security\\res\\message"))) {
                int size =  inputStream.available() / 16 * 16 + 16;
                System.out.println("available is " + inputStream.available() + "/" +size);
                ByteBuffer byteBuffer = ByteBuffer.allocate(size);
                int length;
                byte[] buffer = new byte[1024];
                byte[] data;

                while ((length = inputStream.read(buffer)) != -1) {
                    data = cipher.update(buffer, 0, length);
                    byteBuffer.put(data);
                }
                data = cipher.doFinal();
                byteBuffer.put(data);

                data = byteBuffer.rewind().array();

                cipherText = new String(Base64.getEncoder().encode(data));

            } catch (IOException e) {
                e.printStackTrace();
            }

//            byte[] data = cipher.doFinal();


//            cipherText = new String(Base64.getEncoder().encode(data));
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

            //方式一
//            System.out.println(data.length);
//            data = cipher.doFinal(data);


            //方式二
            ByteBuffer byteBuffer = ByteBuffer.allocate(data.length);
                System.out.println("data is " + data.length);
                System.out.println("byteBuffer is " + byteBuffer.capacity());

            for (int i = 0; i < data.length; i+=16) {
                byte[] temp = cipher.update(data, i, 16);//每次处理16字节，因为密文数据是16字节对齐的
                System.out.println("temp is " + temp.length);
                byteBuffer.put(temp);

            }
            data = byteBuffer.put(cipher.doFinal()).array();


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
