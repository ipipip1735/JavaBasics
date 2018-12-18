import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2018/10/3.
 */
public class LambdaTrial {

    public static XX out = new XX();


    public static void main(String[] args) {
//        forFunction();
        forFun();



//        LambdaTrial.out.look("ddd");
    }

    private static void forFun() {
        //引用静态方法
//        Fun funStatic = XX::view;
//        Object o = funStatic.show("oo");
//        System.out.println(o);

        //引用实例方法
        XX x = new XX(25);
        Fun fun = x::look;
        fun.show("ttt");
        Object o = fun.show("ttt");
        System.out.println(o);


        //引用构造函数
//        Fun fun = XX::new;
//        Object o = fun.show("ttt"); //有返回值，返回值为对象
//        System.out.println(o);

    }

    private static void forFunction() {
        //引用实例对象方法
//        Function<XX, Object> mapper = XX::getAge;
//        XX x = new XX();
//        Object o  =mapper.apply(x);
//        System.out.println(o);


        //引用接口实现类方法
//        IXX ixx = new IXX(){
//            @Override
//            public int show() {
//                System.out.println("Ixx - show()");
//                return 1111111;
//            }
//        };
//        Function<IXX, Object> mapperImpl = IXX::show;
//        Object r = mapperImpl.apply(ixx);
//        System.out.println(r);


//        StringTest stringTest = System.out::println;
//        stringTest(1);




        Consumer<Integer> action = LambdaTrial.out::strTest;
        action.accept(Integer.valueOf(11));

    }
}


class XX {
    int age = 11;

    public XX() {
        System.out.println("construct ");
    }

    public XX(int age) {
        System.out.println("construct " + age);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public static long view(String s) {
        System.out.println("view " + s);
        return 123;
    }

    public long look(String s) {
        System.out.println("look " + s);
        System.out.println("age is " + age);
        return 123;
    }

    public void strTest(Integer i) {
        System.out.println(this.age);
        System.out.println("string test");
    }
}


interface Fun {
    public long show(String s);
}

interface IXX {
    public int show();
}