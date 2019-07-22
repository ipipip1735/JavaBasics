import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import java.util.stream.Collectors;

import static java.security.KeyFactory.getInstance;

/**
 * Created by Administrator on 2019/7/21 17:03.
 */

public class KeyFactoryTrial {
    public static void main(String[] args) {
        KeyFactoryTrial keyFactoryTrial = new KeyFactoryTrial();

//        keyFactoryTrial.create();
//        rsa();
//        pkcs8();
//        sign();
        verify();


//        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
//                Base64.decode(privateKey));
//        KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
//        PrivateKey priKey = keyf.generatePrivate(priPKCS8);
//
//        java.security.Signature signature = java.security.Signature
//                .getInstance(getAlgorithms(rsa2));
//
//        signature.initSign(priKey);
//        signature.update(content.getBytes(DEFAULT_CHARSET));
//        Base64.encode(signed);


        //----------
//        X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(bobEncodedPubKey);
//        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
//        PublicKey bobPubKey = keyFactory.generatePublic(bobPubKeySpec);
//        Signature sig = Signature.getInstance("DSA");
//        sig.initVerify(bobPubKey);
//        sig.update(data);
//        sig.verify(signature);


    }

    private static void verify() {

        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            String publicKey = Files.lines(Paths.get("Security/res/pub.pem"))
                    .filter(s -> !s.contains("-"))
                    .collect(Collectors.joining());
            X509EncodedKeySpec pubPKCS8 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
            PublicKey pubKey = keyFactory.generatePublic(pubPKCS8);
//            RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(pubPKCS8)
//            PublicKey pubKey = keyFactory.generatePublic(pubPKCS8);




            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(pubKey);

            InputStream inputStream = Files.newInputStream(Paths.get("Security/res/message"));
            byte[] buffer = new byte[1024];
            int len;
            while (inputStream.available() != 0) {
                len = inputStream.read(buffer);
                signature.update(buffer);
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

    private static void sign() {


        //            String publicKey = Files.lines(Paths.get("Security/res/pub.pem"))
//                    .filter(s -> !s.contains("-"))
//                    .collect(Collectors.joining());
//            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
//            PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);


        try {

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            String data = "asdfsadf";
            String privateKey = Files.lines(Paths.get("Security/res/pri.pem.pk8"))
                    .filter(s -> !s.contains("-"))
                    .collect(Collectors.joining());
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
            PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);


            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(priKey);
            signature.update(data.getBytes());
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

    private static void pkcs8() {


        try {

            String privateKey = Files.lines(Paths.get("Security/res/pri.pem"))
                    .filter(s -> !s.contains("-"))
                    .collect(Collectors.joining());
//            System.out.println(privateKey);

//            String publicKey = Files.lines(Paths.get("Security/res/pub.pem"))
//                    .filter(s -> !s.contains("-"))
//                    .collect(Collectors.joining());
//            System.out.println(publicKey);

            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void rsa() {

//        RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec();


    }

    private void create() {

        try {
            KeyFactory keyFactory = getInstance("RSA");
            System.out.println("getProvider is " + keyFactory.getProvider());
            System.out.println("getAlgorithm is " + keyFactory.getAlgorithm());


//            System.out.println("generatePublic is " + keyFactory.generatePublic());
//            System.out.println("generatePrivate is " + keyFactory.generatePrivate());
//            System.out.println("getKeySpec is " + keyFactory.getKeySpec());
//            System.out.println("translateKey is " + keyFactory.translateKey());


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
