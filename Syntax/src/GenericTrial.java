import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/12 8:40.
 */

public class GenericTrial {
    public static void main(String[] args) {
        GenericTrial genericTrial = new GenericTrial();
//        genericTrial.method();//方法泛型
//        genericTrial.Class();//绑定类型

        genericTrial.up();//泛型上界
//        genericTrial.multipleBounds();//复合绑定
    }

    private void up() {
        Up up = new Up();
        up.setT(new D());
        System.out.println(up.getT());
    }

    private void multipleBounds() {

        //方法一
        BoundABC<A> boundABC = new BoundABC<>(new A());
        boundABC.doRunTest();

        //方法二
        BoundAB<A> boundAB = new BoundAB<>(new A());
        boundAB.doRunTest();

        //方法三
        BoundBC<A> boundBC = new BoundBC<>(new A());
        boundBC.doRunTest();

    }

    private void Class() {

        List<?> list = new ArrayList<>();
//        list.add(new GGC());

    }


    private void method() {


        GGC ggc = new GGC();
//        ggc.<Double>see("ssss");


    }
}


class GGC {
    public <T> Double see(T g) {
        return Double.valueOf(11.1d);
    }
}


class Up<T extends D> {
    T t;

    public Up() {
    }

    public void setT(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }
}

class BoundAB<T extends A & B> {

    private T objRef;

    public BoundAB(T obj) {
        this.objRef = obj;
    }

    public void doRunTest() {
        this.objRef.displayClass();
    }
}

class BoundBC<T extends B & C> {

    private T objRef;

    public BoundBC(T obj) {
        this.objRef = obj;
    }

    public void doRunTest() {
        this.objRef.displayClass();
    }
}

class BoundABC<T extends A & B & C> {

    private T objRef;

    public BoundABC(T obj) {
        this.objRef = obj;
    }

    public void doRunTest() {
        this.objRef.displayClass();
    }
}

interface B {
    public void displayClass();
}

interface C {
    public void showClass();
}

class A implements B, C {
    public void displayClass() {
        System.out.println("Inside super class A");
    }

    @Override
    public void showClass() {

    }
}

class D {
    int age;
}