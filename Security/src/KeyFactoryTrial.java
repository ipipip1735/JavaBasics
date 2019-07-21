import java.lang.reflect.Method;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;

import static java.security.KeyFactory.getInstance;

/**
 * Created by Administrator on 2019/7/21 17:03.
 */

public class KeyFactoryTrial {
    public static void main(String[] args) {
        KeyFactoryTrial keyFactoryTrial = new KeyFactoryTrial();

        keyFactoryTrial.create();
//        rsa();
        pkcs8();


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

    private static void pkcs8() {


        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec();

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
