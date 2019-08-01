import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2019/7/23 13:40.
 */

public class KeyStoreTrial {

    private PrivateKey privateKey;
    private PublicKey publicKey;
    private String stoPasswd = "sp";
    private String priPasswd = "pp";
    private KeyStore ks;
    private Path path = Path.of("Security\\res\\keys");

    public static void main(String[] args) {

        KeyStoreTrial keyStoreTrial = new KeyStoreTrial();
//        keyStoreTrial.key();
        keyStoreTrial.pair();


//        keyStoreTrial.load();//加载Key容器

//        keyStoreTrial.setPrivateKeyEntry();
//        keyStoreTrial.getPrivateKeyEntry();


//        keyStoreTrial.setSecretKeyEntry();
//        keyStoreTrial.getSecretKeyEntry();

//        keyStoreTrial.setTrustedCertificateEntry();
//        keyStoreTrial.getTrustedCertificateEntry();


    }

    private void getTrustedCertificateEntry() {

        try {
            Certificate certificate;

            //获取证书（方式一）
            KeyStore.TrustedCertificateEntry trustedCertificateEntry = (KeyStore.TrustedCertificateEntry) ks.getEntry("ca", null);
            certificate = trustedCertificateEntry.getTrustedCertificate();
            System.out.println(certificate);

            //获取证书（方式二）
            certificate = ks.getCertificate("ca");
            System.out.println(certificate);
            System.out.println(ks.getCertificateAlias(certificate));
//            ks.getCertificateChain()


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableEntryException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

    }

    private void setTrustedCertificateEntry() {


        try (InputStream inStream = Files.newInputStream(Path.of("Security\\res\\ca\\ca\\ca.crt"))) {

            //生成证书
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate certificate = cf.generateCertificate(inStream);

            //保存信任证书(方式一)
//            ks.setCertificateEntry("ca", certificate);


            //保存信任证书(方式二)
            KeyStore.TrustedCertificateEntry trustedCertificateEntry = new KeyStore.TrustedCertificateEntry(certificate);
            ks.setEntry("ca", trustedCertificateEntry, null);



            //保存Key容器
            char[] password = stoPasswd.toCharArray();
            try (OutputStream outputStream = Files.newOutputStream(path)) {
                ks.store(outputStream, password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }


    }

    private void setSecretKeyEntry() {

        try {

            //设置保护密码
            char[] password = priPasswd.toCharArray();
            KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(password);


            //生成对称密钥
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            SecretKey mySecretKey = keyGenerator.generateKey();


            // 保存对称密钥
            KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(mySecretKey);
            ks.setEntry("secretKeyAlias", skEntry, protParam);


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

    private void getSecretKeyEntry() {

        try {

            //设置保护密码
            char[] password = priPasswd.toCharArray();
            KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(password);

            //获取对称密钥
            KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry) ks.getEntry("secretKeyAlias", protParam);
            SecretKey mySecretKey = secretKeyEntry.getSecretKey();
            System.out.println(mySecretKey.getFormat());
            System.out.println(mySecretKey.getAlgorithm());


        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableEntryException e) {
            e.printStackTrace();
        }

    }

    private void load() {

        try {
//            ks = KeyStore.getInstance(KeyStore.getDefaultType()); //默认是PKCS12
//            ks = KeyStore.getInstance("PKCS12");

//            ks = KeyStore.getInstance("JKS");//无法存储对称加密密钥
            ks = KeyStore.getInstance("JCEKS");

            //加载Key容器
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


    private void setPrivateKeyEntry() {


        try {

            //设置保护密码
            char[] password = priPasswd.toCharArray();
            KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(password);


            //生成私钥
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();

            //生成信任链
            Certificate[] certificates = null;
            try (InputStream inStream = Files.newInputStream(Path.of("Security\\res\\ca\\chain.pem"))) {
                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                Collection collection = cf.generateCertificates(inStream);

                certificates = Stream.of(collection.toArray()).toArray(n -> new Certificate[n]);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (CertificateEncodingException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();
            }

            // 存储私钥
            KeyStore.PrivateKeyEntry keyEntry = new KeyStore.PrivateKeyEntry(privateKey, certificates);
            ks.setEntry("prikey", keyEntry, protParam);


            //保存Key容器
            password = stoPasswd.toCharArray();
            try (OutputStream outputStream = Files.newOutputStream(path)) {
                ks.store(outputStream, password);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();
            }

        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    private void getPrivateKeyEntry() {

        try {

            char[] password = priPasswd.toCharArray();
            KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(password);

            // 获取私钥
            KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry("prikey", protParam);
            privateKey = pkEntry.getPrivateKey();
            System.out.println(privateKey);


        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableEntryException e) {
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
