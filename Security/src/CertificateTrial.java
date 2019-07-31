/**
 * Created by Administrator on 2019/7/29.
 */

public class CertificateTrial {

    public static void main(String[] args) {
        CertificateTrial certificateTrial = new CertificateTrial();

        certificateTrial.creat();
    }

    private void creat() {

        try (InputStream inStream = new FileInputStream("fileName-of-cert")) {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate)cf.generateCertificate(inStream);
        }
    }

}
