import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import java.util.stream.Collectors;

import static java.security.KeyFactory.getInstance;

/**
 * Created by Administrator on 2019/7/21 17:03.
 */

public class RSATrial {
    public static void main(String[] args) {
        RSATrial rsaTrial = new RSATrial();



        rsaTrial.publicKeyX509();
//        rsaTrial.privateKeyPKCS8();
//        rsaTrial.sign();
//        rsaTrial.verify();


    }

    private void publicKeyX509() {

        try {
            String publicKeyX509 = Files.lines(Paths.get("Security/res/rsa.pub.pem"))
                    .filter(s -> !s.contains("-"))
                    .collect(Collectors.joining());

            X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyX509));


            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(pubX509);
            System.out.println(publicKey);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    private void privateKeyPKCS8() {

        try {
            String privateKeyPKCS8 = Files.lines(Paths.get("Security/res/rsa.pri.pkcs8"))
                    .filter(s -> !s.contains("-"))
                    .collect(Collectors.joining());

            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyPKCS8));

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(priPKCS8);
            System.out.println(privateKey);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

    private void verify() {

        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            String publicKey = Files.lines(Paths.get("Security/res/rsa.pub.pem"))
                    .filter(s -> !s.contains("-"))
                    .collect(Collectors.joining());
            X509EncodedKeySpec pubPKCS8 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
            PublicKey pubKey = keyFactory.generatePublic(pubPKCS8);
            System.out.println(pubKey);



            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(pubKey);

            InputStream inputStream = Files.newInputStream(Paths.get("Security/res/message"));
            byte[] buffer = new byte[1024];
            int len;
            while (inputStream.available() != 0) {
                len = inputStream.read(buffer);
                signature.update(buffer, 0, len);
                for (int i = 0; i < len; i++) {
                    System.out.print(buffer[i] + ", ");
                }
                System.out.println("");
            }
            inputStream.close();


            byte[] signData = Files.readAllBytes(Paths.get("Security/res/signData"));
            boolean verifies = signature.verify(signData);
            System.out.println("signature verifies: " + verifies);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }

    }

    private void sign() {

        try {

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            String privateKey = Files.lines(Paths.get("Security/res/rsa.pri.pkcs8"))
                    .filter(s -> !s.contains("-"))
                    .collect(Collectors.joining());

            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
            PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);
            System.out.println(priKey);


            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(priKey);


            InputStream inputStream = Files.newInputStream(Paths.get("Security/res/message"));
            byte[] buffer = new byte[1024];
            int len;
            while (inputStream.available() != 0) {
                len = inputStream.read(buffer);
                signature.update(buffer, 0, len);
                for (int i = 0; i < len; i++) {
                    System.out.print(buffer[i] + ", ");
                }
                System.out.println("");
            }
            inputStream.close();


            byte[] signData = signature.sign();
            Files.write(Paths.get("Security/res/signData"), signData);
            System.out.println(Base64.getEncoder().encodeToString(signData));


        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

    }
}
