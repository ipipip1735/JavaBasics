package innerClass;

/**
 * Created by Administrator on 2017/4/24.
 */
public class Trial {
    public static void main(String[] args) {

        //方式一：通过外部类实例访问内部类成员
        OuterClass outerClass = new OuterClass();
//        System.out.println(outerClass.innerClass.name);



        //方式二：实例化内部类后，可以访问公有成员
//        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
//        innerClass.accessPrivate();


    }
}