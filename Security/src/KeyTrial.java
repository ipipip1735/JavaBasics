import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.util.Base64;

/**
 * Created by Administrator on 2019/7/23 13:40.
 */

public class KeyTrial {
    public static void main(String[] args) {

        KeyTrial keyTrial = new KeyTrial();
//        keyTrial.key();
        keyTrial.pair();
//        keyTrial.store();

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

    private void store() {
        KeyStore ks = null;
        try {
            ks = KeyStore.getInstance("JKS");

//        FileInputStream ksfis = new FileInputStream(ksName);
//        BufferedInputStream ksbufin = new BufferedInputStream(ksfis);

//            ks.load(ksbufin, spass);
//            PrivateKey priv = (PrivateKey) ks.getKey(alias, kpass);


        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }

    private void pair() {
        try {

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            SecureRandom random = SecureRandom.getInstance("Windows-PRNG");
            keyPairGenerator.initialize(1024, random);


            KeyPair pair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();


            System.out.println(privateKey);
            System.out.println(publicKey);


            byte[] base64 = Base64.getEncoder().encode(privateKey.getEncoded());
            Files.write(Paths.get("Security/res/dsa.key"), base64);
//            Files.write(Paths.get("Security/res/dsa.key"), privateKey.getEncoded());


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
