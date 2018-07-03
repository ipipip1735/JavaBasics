/**
 * Created by Administrator on 2017/5/4.
 */
public class main {

    public static void main(String[] args) {
        int k;

        try {
            k = Class.forName("MyAnnotationTest").getField("kk").getAnnotation(MyAnnotation.class).value();
        System.out.println(k);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
