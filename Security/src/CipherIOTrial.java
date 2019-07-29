import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Created by Administrator on 2019/7/28.
 */

public class CipherIOTrial {

    public static void main(String[] args) {
        CipherIOTrial cipherIOTrial = new CipherIOTrial();

//        cipherIOTrial.inputStream();//读取文件并加密

        cipherIOTrial.createEncyptFile();//读取文件，加密后入文件
        cipherIOTrial.outputStream();//读取密文，解密后保存到文件
    }

    private void outputStream() {


        try {
            byte[] iv = new byte[16];
            Arrays.fill(iv, (byte) 0);
            IvParameterSpec ivspec = new IvParameterSpec(iv);//初始化向量

            byte[] AESkey = new byte[32]; //AES算法密钥长度
            Arrays.fill(AESkey, (byte) 0);
            SecretKey secretKey = new SecretKeySpec(AESkey, "AES");


            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec, new SecureRandom());

            try (InputStream inputStream = Files.newInputStream(Paths.get("Security\\res\\message.enc"));
                 OutputStream outputStream = Files.newOutputStream(Paths.get("Security\\res\\message.dnc"));
                 CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher)) {

                byte[] buffer = new byte[1024];
                int length = 0;
                while ((length = inputStream.read(buffer)) != -1) {
                    System.out.println("length is " + length);
                    cipherOutputStream.write(buffer, 0, length);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

    }


    private void createEncyptFile() {


        try {
            byte[] iv = new byte[16];
            Arrays.fill(iv, (byte) 0);
            IvParameterSpec ivspec = new IvParameterSpec(iv);//初始化向量

            byte[] AESkey = new byte[32]; //AES算法密钥长度
            Arrays.fill(AESkey, (byte) 0);
            SecretKey secretKey = new SecretKeySpec(AESkey, "AES");


            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec, new SecureRandom());

            try (InputStream inputStream = Files.newInputStream(Paths.get("Security\\res\\message"));
                 CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
                 OutputStream outputStream = Files.newOutputStream(Paths.get("Security\\res\\message.enc"))) {
                byte[] buffer = new byte[1024];
                int length = 0;
                while ((length = cipherInputStream.read(buffer)) != -1) {
                    System.out.println("length is " + length);
                    outputStream.write(buffer, 0, length);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }


    private void inputStream() {


        try {
            byte[] iv = new byte[16];
            Arrays.fill(iv, (byte) 0);
            IvParameterSpec ivspec = new IvParameterSpec(iv);//初始化向量

            byte[] AESkey = new byte[32]; //AES算法密钥长度
            Arrays.fill(AESkey, (byte) 0);
            SecretKey secretKey = new SecretKeySpec(AESkey, "AES");


            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec, new SecureRandom());
            byte[] result = null;

            try (InputStream inputStream = Files.newInputStream(Paths.get("Security\\res\\message"));
                 CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher)) {
                byte[] buffer = new byte[1024];

                int size = inputStream.available() / 16 * 16 + 16;
                System.out.println("size is " + size);
                ByteBuffer byteBuffer = ByteBuffer.allocate(size);
                int length = 0;
                while ((length = cipherInputStream.read(buffer)) != -1) {
                    System.out.println(length + "/" + byteBuffer.capacity());
                    byteBuffer.put(buffer, 0, length);
                }
                result = byteBuffer.array();
                System.out.println("available is " + cipherInputStream.available());

            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(result.length);

            String s = new String(result);
            System.out.println(s);

            System.out.println("---------------");

            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            System.out.println(new String(cipher.doFinal(result)));


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
