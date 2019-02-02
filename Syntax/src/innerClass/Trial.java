package innerClass;

/**
 * Created by Administrator on 2017/4/24.
 */
public class Trial {
    public static void main(String[] args) {

        OuterClass outerClass = new OuterClass();
//        System.out.println(outerClass.innerClass.name);

//        outerClass.innerClass.accessPrivate();

        outerClass.getInner();
    }
}