import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2019/7/29.
 */

public class CertificateTrial {

    public static void main(String[] args) {
        CertificateTrial certificateTrial = new CertificateTrial();

//        certificateTrial.certificate();
//        certificateTrial.verify();

        certificateTrial.certPath();
    }

    private void certPath() {

        //方式一
        try (InputStream inStream = Files.newInputStream(Path.of("Security\\res\\ca\\chain.crt"))) {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Collection collection = cf.generateCertificates(inStream);

            ArrayList<Certificate> crts = new ArrayList();
            crts.addAll(collection);

            CertPath certPath = cf.generateCertPath(crts);


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

    private void link() {


    }

    private void verify() {

        try (InputStream chainInStream = Files.newInputStream(Path.of("Security\\res\\chain.crt"));
             InputStream serverInStream = Files.newInputStream(Path.of("Security\\res\\88.crt"))) {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate ca = cf.generateCertificate(chainInStream);
            Certificate server = cf.generateCertificate(serverInStream);


            server.verify(ca.getPublicKey());


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

//        try (InputStream inStream = Files.newInputStream(Path.of("Security\\res\\88.crt"))) {
//            CertificateFactory cf = CertificateFactory.getInstance("X.509");
//            Certificate cert = cf.generateCertificate(inStream);
//
//            System.out.println("getEncoded's length is " + cert.getEncoded().length);
//            System.out.println("getPublicKey is " + cert.getPublicKey());
//            System.out.println("getType is " + cert.getType());
//            System.out.println(cert);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        }


        try (InputStream inStream = Files.newInputStream(Path.of("Security\\res\\ca\\chains.pem"))) {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Collection certificates = cf.generateCertificates(inStream);
            System.out.println(certificates.size());

//            for (Object certificate : certificates) System.out.println(certificate);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }


    }

}
