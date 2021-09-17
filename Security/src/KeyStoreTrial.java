import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2019/7/23 13:40.
 */

public class KeyStoreTrial {

    private String storePasswd = "sp";
    private String privateKeyPasswd = "pp";
    private KeyStore ks;
    private Path path = Path.of("Security/res/keyStore");

    public static void main(String[] args) {

        KeyStoreTrial keyStoreTrial = new KeyStoreTrial();


        keyStoreTrial.load();//加载Key容器
//        keyStoreTrial.build();//加载Key容器


//        keyStoreTrial.setPrivateKeyEntry();
//        keyStoreTrial.getPrivateKeyEntry();


//        keyStoreTrial.setSecretKeyEntry();
//        keyStoreTrial.getSecretKeyEntry();

//        keyStoreTrial.setTrustedCertificateEntry();
//        keyStoreTrial.getTrustedCertificateEntry();

    }

//    private void build() {
//
//        try {
//
//
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        }
//    }

    private void getTrustedCertificateEntry() {

        try {

            if (ks.size() == 0 && !ks.isKeyEntry("prikey")) {
                System.out.println("Failure");
                return;
            }

            Certificate certificate;

            //获取证书（方式一）
            KeyStore.TrustedCertificateEntry trustedCertificateEntry = (KeyStore.TrustedCertificateEntry) ks.getEntry("root", null);
            certificate = trustedCertificateEntry.getTrustedCertificate();
            System.out.println(certificate);

            //获取证书（方式二）
            certificate = ks.getCertificate("root");
            System.out.println(certificate);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableEntryException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

    }

    private void setTrustedCertificateEntry() {


        try (InputStream inStream = Files.newInputStream(Path.of("Security/res/ca/root/ca.crt"))) {

            //解析证书
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate certificate = cf.generateCertificate(inStream);

            //保存信任证书(方式一)
            ks.setCertificateEntry("root", certificate);

            //保存信任证书(方式二)
            KeyStore.TrustedCertificateEntry trustedCertificateEntry = new KeyStore.TrustedCertificateEntry(certificate);
            ks.setEntry("root", trustedCertificateEntry, null);


            //保存KeyStore
            char[] password = storePasswd.toCharArray();
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
            char[] password = privateKeyPasswd.toCharArray();
            KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(password);


            //生成对称密钥
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecretKey mySecretKey = keyGenerator.generateKey();


            // 保存对称密钥
            KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(mySecretKey);
            ks.setEntry("secretKeyAlias", skEntry, protParam);


            //保存KeyStore
            password = storePasswd.toCharArray();
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

            if (ks.size() == 0 && !ks.isKeyEntry("prikey")) {
                System.out.println("Failure");
                return;
            }

            //设置保护密码
            char[] password = privateKeyPasswd.toCharArray();
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
            //创建KeyStore
            String type = KeyStore.getDefaultType();//默认是PKCS12
            ks = KeyStore.getInstance(type); //等价于KeyStore.getInstance("PKCS12")
//            ks = KeyStore.getInstance("JKS");//无法存储对称加密密钥
//            ks = KeyStore.getInstance("JCEKS");

            //使用构建器创建KeyStore
//            File file = new File("Security/res/keyStore");
//            KeyStore.ProtectionParameter protectionParameter = new KeyStore.PasswordProtection(null);
//            ks = KeyStore.Builder
//                    .newInstance(type, null, file, protectionParameter)
//                    .getKeyStore();


            //加载Key容器
            char[] password = storePasswd.toCharArray();//容器密码
            if (Files.exists(path)) {
                try (InputStream inputStream = Files.newInputStream(path)) {
                    ks.load(inputStream, password);//加载容器
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (CertificateException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            } else {
                ks.load(null, password);//加载空容器
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
        //方式一
        storePriKey();
    }

    private void storePriKey() {
        try {

            //设置保护密码
            char[] password = privateKeyPasswd.toCharArray();
            KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(password);


            //生成私钥
            PrivateKey privateKey = null;
            try {
                String privateKeyPKCS8 = Files.lines(Paths.get("Security/res/ca/third/pri.key.pkcs8"))
                        .filter(s -> !s.contains("-"))
                        .collect(Collectors.joining());

                PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyPKCS8));

                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                privateKey = keyFactory.generatePrivate(priPKCS8);
                System.out.println(privateKey);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }


            //生成证书链
            Certificate[] certificates = null;
            try (InputStream inStream = Files.newInputStream(Path.of("Security/res/ca/chain.crt"))) {
//            try (InputStream inStream = Files.newInputStream(Path.of("Security/res/ca/chain.pem"))) {
                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                Collection collection = cf.generateCertificates(inStream);

                certificates = Stream.of(collection.toArray()).toArray(n -> new Certificate[n]);
                System.out.println(certificates[0]);

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


            //保存KeyStore
            password = storePasswd.toCharArray();
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

            if (ks.size() == 0 && !ks.isKeyEntry("prikey")) {
                System.out.println("Failure");
                return;
            }

            //设置保护密码
            char[] password = privateKeyPasswd.toCharArray();
            KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(password);

            // 获取私钥
            KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry("prikey", protParam);
            PrivateKey privateKey = pkEntry.getPrivateKey();
            System.out.println(privateKey);


            //获取证书数组
            Certificate[] certificates = ks.getCertificateChain("priKey");
            System.out.println("chain's size is " + certificates.length);

            //获取服务器证书
            Certificate certificate = ks.getCertificate("priKey");
            System.out.println(certificate);


        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableEntryException e) {
            e.printStackTrace();
        }
    }
}
