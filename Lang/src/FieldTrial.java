import java.lang.reflect.Field;

/**
 * Created by Administrator on 2018/10/30.
 */
public class FieldTrial {

    public static void main(String[] args) {
        FieldTrial fieldTrial = new FieldTrial();
        fieldTrial.staticField();
//        fieldTrial.noStaticField();
    }

    private void noStaticField() {

        F ff = new F(1, false, "aaaaa");
        Field[] fields = F.class.getFields();
        for (Field f : fields) {
            try {
                System.out.println("NO-Static|" + f.getName() + " = " + f.get(ff));//获取字段的值
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    private void staticField() {

        Field[] fields = FF.class.getFields();
        for (Field f : fields) {
            try {
                System.out.println("Static|" + f.getName() + " = " + f.get(null)); //获取静态字段的值
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
}


class F {
    private int k;
    protected boolean bb;
    public String ss = "ss";  //只有public能反射
    static String tt = "tt";
    public static String mm = "mm";

    public F(int k, boolean bb, String ss) {
        this.k = k;
        this.bb = bb;
        this.ss = ss;
    }
}

class FF {
    final private int k = 0;
    final protected boolean bb = true;
    final static public String ss = "ssssssss";  //只有public能反射
//    public String tt = "tt";//无法读取非静态字段，抛异常
}