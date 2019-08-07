import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2019/7/21 17:03.
 */

public class DSATrial {
    public static void main(String[] args) {
        DSATrial rsaTrial = new DSATrial();



//        rsaTrial.publicKeyX509();
        rsaTrial.privateKeyPKCS8();
//        rsaTrial.sign();
//        rsaTrial.verify();


    }

    private void publicKeyX509() {

        try {
            String publicKeyX509 = Files.lines(Paths.get("Security/res/dsa.pub.pem"))//读取公钥文件
                    .filter(s -> !s.contains("-"))//去掉头尾标记行
                    .collect(Collectors.joining());//拼接成一行字符串

            byte[] key = Base64.getDecoder().decode(publicKeyX509);//Base64解码
            X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(key);//创建KeySpec对象


            KeyFactory keyFactory = KeyFactory.getInstance("DSA");//实例化工厂
            PublicKey publicKey = keyFactory.generatePublic(pubX509);//转换KeySpec为key对象
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
            String privateKeyPKCS8 = Files.lines(Paths.get("Security/res/dsa.pri.pkcs8"))//读取私钥文件
                    .filter(s -> !s.contains("-"))//去掉头尾标记行
                    .collect(Collectors.joining());//拼接成一行字符串

            byte[] key = Base64.getDecoder().decode(privateKeyPKCS8);//Base64解码
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(key);//创建KeySpec对象

            KeyFactory keyFactory = KeyFactory.getInstance("DSA");//实例化工厂
            PrivateKey privateKey = keyFactory.generatePrivate(priPKCS8);//转换KeySpec为key对象
            System.out.println(privateKey);


            //将私钥Base64编码后保存到文件（这里仅仅是为了演示）
            byte[] base64 = Base64.getEncoder().encode(privateKey.getEncoded());
            Files.write(Paths.get("Security/res/dsa.key"), base64);


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
            KeyFactory keyFactory = KeyFactory.getInstance("DSA");//实例化工厂
            String publicKey = Files.lines(Paths.get("Security/res/dsa.pub.pem"))//读取公钥文件
                    .filter(s -> !s.contains("-"))//去掉头尾标记行
                    .collect(Collectors.joining());//拼接成一行字符串

            byte[] key = Base64.getDecoder().decode(publicKey);//Base64解码
            X509EncodedKeySpec pubPKCS8 = new X509EncodedKeySpec(key);//创建KeySpec对象
            PublicKey pubKey = keyFactory.generatePublic(pubPKCS8);//转换KeySpec为key对象
            System.out.println(pubKey);


            Signature signature = Signature.getInstance("SHA256withDSA");//实例化签名对象
            signature.initVerify(pubKey);//初始化签名对象

            InputStream inputStream = Files.newInputStream(Paths.get("Security/res/message"));//读取信息
            byte[] buffer = new byte[1024];
            int len;
            while (inputStream.available() != 0) {
                len = inputStream.read(buffer);
                signature.update(buffer, 0, len);//逐K更新摘要
                for (int i = 0; i < len; i++) {
                    System.out.print(buffer[i] + ", ");
                }
                System.out.println("");
            }
            inputStream.close();


            byte[] signData = Files.readAllBytes(Paths.get("Security/res/signData"));//读取签名
            boolean verifies = signature.verify(signData);//验证签名
            System.out.println("signature verifies: " + verifies);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

    }

    private void sign() {

        try {

            KeyFactory keyFactory = KeyFactory.getInstance("DSA");//实例化工厂

            String privateKey = Files.lines(Paths.get("Security/res/dsa.pri.pkcs8"))//读取私钥文件
                    .filter(s -> !s.contains("-"))//去掉头尾标记行
                    .collect(Collectors.joining());//拼接成一行字符串

            byte[] key = Base64.getDecoder().decode(privateKey);//Base64解码
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(key);//创建KeySpec对象
            PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);//转换KeySpec为key对象
            System.out.println(priKey);


            Signature signature = Signature.getInstance("SHA256withDSA");//实例化签名对象
            signature.initSign(priKey);//初始化签名对象


            InputStream inputStream = Files.newInputStream(Paths.get("Security/res/message"));//读取信息
            byte[] buffer = new byte[1024];
            int len;
            while (inputStream.available() != 0) {
                len = inputStream.read(buffer);
                signature.update(buffer, 0, len);//逐K更新摘要
                for (int i = 0; i < len; i++) {
                    System.out.print(buffer[i] + ", ");
                }
                System.out.println("");
            }
            inputStream.close();


            byte[] signData = signature.sign();//签名
            Files.write(Paths.get("Security/res/signData"), signData);//保存签名二进制数据到文件
            System.out.println(Base64.getEncoder().encodeToString(signData));//也打印到控制台


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
