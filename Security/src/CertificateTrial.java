import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Administrator on 2019/7/29.
 */

public class CertificateTrial {

    public static void main(String[] args) {
        CertificateTrial certificateTrial = new CertificateTrial();

        certificateTrial.certificate();//创建证书对象
//        certificateTrial.verify();//验证证书

        certificateTrial.certPath();//创建信任链对象





    }

    private void certPath() {

        //方式一
        try (InputStream inStream = Files.newInputStream(Path.of("Security\\res\\ca\\chain.crt"))) {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");//创建工厂对象
            Collection collection = cf.generateCertificates(inStream);//获取证书对象集

            ArrayList crts = new ArrayList();
            crts.addAll(collection);//转为List容器

            CertPath certPath = cf.generateCertPath(crts); //创建信任链对象


        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }


        //方式二：Java API不支持解析信任链文件创建CertPath
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
