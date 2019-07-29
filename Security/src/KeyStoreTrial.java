import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Base64;

/**
 * Created by Administrator on 2019/7/23 13:40.
 */

public class KeyStoreTrial {

    private PrivateKey privateKey;
    private PublicKey publicKey;
    String stoPasswd = "sp";
    String priPasswd = "pp";

    public static void main(String[] args) {

        KeyStoreTrial keyStoreTrial = new KeyStoreTrial();
//        keyStoreTrial.key();
//        keyStoreTrial.pair();

        keyStoreTrial.store();

    }


    private void store() {

        try {
//            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            KeyStore ks = KeyStore.getInstance("JKS");
//            KeyStore ks = KeyStore.getInstance("PKCS12");

            //读取Key容器
            Path path = Path.of("Security\\res\\keys");
            char[] password = stoPasswd.toCharArray();
            if (Files.exists(path)) {
                try (InputStream inputStream = Files.newInputStream(path)) {
                    ks.load(inputStream, password);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (CertificateException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            } else {
                ks.load(null, password);
            }


            password = priPasswd.toCharArray();
            KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(password);




            //保存私钥
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

//            KeyStore.PrivateKeyEntry keyEntry =
//            KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) ks.setEntry("prikey");
//            PrivateKey privateKey = pkEntry.getPrivateKey();
//            System.out.println(privateKey);


            // 获取私钥
//            KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry("prikey", protParam);
//            PrivateKey privateKey = pkEntry.getPrivateKey();
//            System.out.println(privateKey);


            // 保存对称密钥
//            javax.crypto.SecretKey mySecretKey;
//            KeyStore.SecretKeyEntry skEntry =
//                    new KeyStore.SecretKeyEntry(mySecretKey);
//            ks.setEntry("secretKeyAlias", skEntry, protParam);

//
//            // store away the keystore
//            try (FileOutputStream fos = new FileOutputStream("newKeyStoreName")) {
//                ks.store(fos, password);
//            }


            //保存Key容器
            password = stoPasswd.toCharArray();
            try (OutputStream outputStream = Files.newOutputStream(path)) {
                ks.store(outputStream, password);
            }


        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void key() {

        //创建AES密钥(Key)
//        try {
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//            keyGenerator.init(256);
//            SecretKey secretKey = keyGenerator.generateKey();
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }


        //创建AES密钥(KeySpec)
        String pass = "i'm chris";
        SecretKeySpec secretKeySpec = new SecretKeySpec(pass.getBytes(), "AES");
        secretKeySpec.getAlgorithm();


    }


    private void pair() {
        try {

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("PKCS11");
            keyPairGenerator.initialize(2048, random);


            KeyPair pair = keyPairGenerator.generateKeyPair();
            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();


            System.out.println(privateKey);
            System.out.println(publicKey);


//            byte[] base64 = Base64.getEncoder().encode(privateKey.getEncoded());
//            Files.write(Paths.get("Security/res/dsa.key"), base64);
//            Files.write(Paths.get("Security/res/dsa.key"), privateKey.getEncoded());


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }
}
