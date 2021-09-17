import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Administrator on 2019/7/29.
 */

public class CertificateTrial {

    public static void main(String[] args) {
        CertificateTrial certificateTrial = new CertificateTrial();

//        certificateTrial.certificate();//创建证书对象
//        certificateTrial.principal();//创建证书主体信息
//        certificateTrial.verify();//验证证书
//        certificateTrial.certPath();//创建信任链对象
        certificateTrial.trustManager();//创建受信管理器


    }

    private void trustManager() {

        try (InputStream inStream = Files.newInputStream(Path.of("Security/res/test/chain.p7b"))) {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");//创建工厂对象
            Collection collection = cf.generateCertificates(inStream);//获取证书对象集
            X509Certificate[] x509Certificates = (X509Certificate[]) collection.toArray(X509Certificate[]::new);

            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(null, null);
            keyStore.setCertificateEntry("root", x509Certificates[1]);

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("PKIX");
            // trustManagerFactory.init(keyStore);
            trustManagerFactory.init((KeyStore) null);


            X509TrustManager trustManager = (X509TrustManager) trustManagerFactory.getTrustManagers()[0];
            System.out.println(trustManager.getAcceptedIssuers().length + "trustManager.getAcceptedIssuers() = " + trustManager.getAcceptedIssuers());


            trustManager.checkServerTrusted(x509Certificates, "RSA");


        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

    private void principal() {

//        X500Principal x500Principal1 = new X500Principal("CN=");
//        System.out.println("x500Principal1 = " + x500Principal1);
//
//        X500Principal x500Principal2 = new X500Principal("CN=, OU=b");
//        System.out.println("x500Principal2 = " + x500Principal2);
//
//        X500Principal x500Principal3 = new X500Principal("CN=a, CN=b");
//        System.out.println("x500Principal3 = " + x500Principal3);

        HashMap<String, String> attr = new HashMap<>();
        attr.put("AA", "1.2.3.4.");
        X500Principal x500Principal4 = new X500Principal("CN=a, CN=b", attr);
        System.out.println("x500Principal4 = " + x500Principal4);

    }

    private void certPath() {

        //方式一
        try (InputStream inStream = Files.newInputStream(Path.of("Security/res/ca/chain.crt"))) {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");//创建工厂对象
            Collection collection = cf.generateCertificates(inStream);//获取证书对象集

            ArrayList crts = new ArrayList();
            crts.addAll(collection);//转为List容器

            CertPath certPath = cf.generateCertPath(crts); //创建证书链
            System.out.println("certPath = " + certPath);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }


        //方式二
//        try (InputStream inStreamRootCA = Files.newInputStream(Path.of("Security/res/test/root.cer"));
//             InputStream inStreamChain = Files.newInputStream(Path.of("Security/res/test/chain.p7b"))) {
//            CertificateFactory cf = CertificateFactory.getInstance("X.509");//创建工厂对象
//            X509Certificate rootCA = (X509Certificate) cf.generateCertificate(inStreamRootCA);//获取根CA
//            Collection chain = cf.generateCertificates(inStreamChain);//获取证书对象集
//            System.out.println("chain = " + chain);
//
//            CertPathBuilder certPathBuilder = CertPathBuilder.getInstance("PKIX");
//
//            //信任锚点
//            TrustAnchor trustAnchor = new TrustAnchor(rootCA, null);
//            X509CertSelector selector = new X509CertSelector();
////            targetConstraints.setCertificate((X509Certificate) chain.toArray()[0]);
//            selector.setSubject("CN=privateobject.com");
//            PKIXParameters params = new PKIXBuilderParameters(Collections.singleton(trustAnchor), selector);
//
//
//            //证书吊销检测器
//            PKIXRevocationChecker rc = (PKIXRevocationChecker) certPathBuilder.getRevocationChecker();
//            rc.setOptions(EnumSet.of(PKIXRevocationChecker.Option.PREFER_CRLS));
//            params.addCertPathChecker(rc);
////            params.setRevocationEnabled(false);
//
//
//            //设置证书仓库（仓库中包含所有证书，后面构建证书路径时都源于此仓库）
//            CollectionCertStoreParameters ccsp = new CollectionCertStoreParameters(chain);
//            CertStore certStore = CertStore.getInstance("Collection", ccsp);
//            params.addCertStore(certStore);//增加仓库
//
//            //构建器证书路径（遍历检查）
//            CertPathBuilderResult certPathBuilderResult = certPathBuilder.build(params);
//            CertPath certPath = certPathBuilderResult.getCertPath();
//            System.out.println("certPath = " + certPath);
//
//
//            //使用验证器（验证器实际上没什么用）
//            CertPathValidator cpv = CertPathValidator.getInstance("PKIX");
//            rc = (PKIXRevocationChecker)cpv.getRevocationChecker();
//            rc.setOptions(EnumSet.of(PKIXRevocationChecker.Option.SOFT_FAIL));
//            CertPathValidatorResult cpvr = cpv.validate(certPath, params);
//            System.out.println("cpvr = " + cpvr);
//
//
//
//
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InvalidAlgorithmParameterException e) {
//            e.printStackTrace();
//        } catch (CertPathBuilderException e) {
//            e.printStackTrace();
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        }
//        catch (CertPathValidatorException e) {
//            e.printStackTrace();
//        }


        //方式三：Java API不支持解析信任链文件创建CertPath
//        try (InputStream inStream = Files.newInputStream(Path.of("Security\\res\\ca\\chain.crt"))) {
//            CertificateFactory cf = CertificateFactory.getInstance("X.509");
//            CertPath certPath = cf.generateCertPath(inStream);
//
//            List list = certPath.getCertificates();
////            System.out.println(list.size());
//
////            System.out.println();
////            System.out.println("getEncoded's length is " + cert.getEncoded().length);
////            System.out.println("getPublicKey is " + cert.getPublicKey());
////            System.out.println("getType is " + cert.getType());
////            System.out.println(cert);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (CertificateEncodingException e) {
//            e.printStackTrace();
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        }

    }

    private void verify() {

        try (InputStream chainInStream = Files.newInputStream(Path.of("Security\\res\\chain.crt"));
             InputStream serverInStream = Files.newInputStream(Path.of("Security\\res\\88.crt"))) {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate ca = cf.generateCertificate(chainInStream);//创建CA证书对象
            Certificate server = cf.generateCertificate(serverInStream);//创建服务器证书对象


            PublicKey publicKey = ca.getPublicKey();//获取CA证书的公钥
            server.verify(publicKey);//使用CA公钥验证服务器证书的签名


        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }


    }

    private void certificate() {

        //读取证书
        try (InputStream inStream = Files.newInputStream(Path.of("Security\\res\\88.crt"))) {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");//获取证书工厂实例
            Certificate cert = cf.generateCertificate(inStream);//创建证书对象

            System.out.println("getEncoded's length is " + cert.getEncoded().length);//获取证书数据长度
            System.out.println("getPublicKey is " + cert.getPublicKey());//获取证书公钥
            System.out.println("getType is " + cert.getType());//获取证书类型
            System.out.println(cert);//打印证书完整信息


        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }


        //读取信任链文件（包含多个证书）
//        try (InputStream inStream = Files.newInputStream(Path.of("Security\\res\\ca\\chains.pem"))) {
//            CertificateFactory cf = CertificateFactory.getInstance("X.509");//获取证书工厂实例
//            Collection certificates = cf.generateCertificates(inStream);//创建证书对象数组
//            System.out.println(certificates.size());//打印数组尺寸
//
////            for (Object certificate : certificates) System.out.println(certificate);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        }


    }

}
