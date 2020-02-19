import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by Administrator on 2020/2/18 7:02.
 */
public class PropertiesTrial {

    public static void main(String[] args) {
        PropertiesTrial propertiesTrial = new PropertiesTrial();
//        propertiesTrial.read();
//        propertiesTrial.put();
        propertiesTrial.set();
    }

    private void set() {

        Properties properties = new Properties();
        properties.setProperty("xx", "yy");
        properties.setProperty("one", "111");
        properties.list(System.out);//打印属性集

        System.out.println(properties.get("xx"));
    }

    private void put() {

        Properties properties = new Properties();
        properties.put("xx", "yy");//增加属性
        properties.put("ss", "tt");
        properties.put("pp", "qq");


        properties.list(System.out);//打印属性集

        System.out.println(properties.stringPropertyNames());//获取所有属性名

        properties.remove("pp");//删除属性
        Enumeration enumeration = properties.propertyNames();//获取所有属性名
        while (enumeration.hasMoreElements()) {//遍历所有属性名
            System.out.println(enumeration.nextElement());
        }





    }

    private void read() {

        try(FileInputStream fileInputStream = new FileInputStream("Util/res/xx.properties");
            FileOutputStream fileOutputStream = new FileOutputStream("Util/res/xx.xml")) {

            Properties properties = new Properties();
            properties.load(fileInputStream);//读取文本格式
            properties.list(System.out);

            properties.storeToXML(fileOutputStream, "sss");//保存为XML格式

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try(FileInputStream fileInputStream = new FileInputStream("Util/res/xx.xml")) {
//
//            Properties properties = new Properties();
//            properties.loadFromXML(fileInputStream);//读取XML格式
//            properties.list(System.out);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



    }

}
