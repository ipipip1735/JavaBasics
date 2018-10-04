import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;
import java.util.List;

/**
 * Created by Administrator on 2018/7/4.
 */

public class VarHandleTrial {
    public static void main(String[] args) {

        VarHandleTrial varHandleTrial = new VarHandleTrial();
//        varHandleTrial.base();
//        varHandleTrial.array();
        varHandleTrial.coordinate();
        varHandleTrial.bind();

    }

    private void coordinate() {
        VarHandle avh = MethodHandles.arrayElementVarHandle(Integer[].class);
//        accessModeType(VarHandle.AccessMode.GET);


    }

    private void bind() {

        try {
            Man man = new Man(23, "jerry", new Car());
            MethodHandle methodHandle = MethodHandles.lookup().bind(man, "add",
                    MethodType.methodType(boolean.class, Car.class));

            methodHandle.invoke(new Car(222333, "Cadillac"));



        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
//        MethodType typeGet = accessModeType(VarHandle.AccessMode.GET);

    }


    private void array() {
//        Man[] sa ;
//        sa[0] = new Man(21, "ss");
//        sa[1] = new Man(3, "jor");
//        VarHandle avh = MethodHandles.arrayElementVarHandle(Integer[].class);
//        System.out.println(avh.coordinateTypes());
//        boolean r = avh.compareAndSet(sa, 1, "bb", "new");


//        System.out.println(sa[0]);
//        System.out.println(sa[1]);
    }

    private void base() {

//        MethodType methodType = MethodType.methodType(Man.class, Car.class, String.class);
//        List<Class<?>> list = methodType.parameterList();
//        System.out.println(list);


    }
}


class Car {
    long num;
    String name;

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car() {
        long num = 10101000;
        String name = "toyota";
    }

    public Car(long num, String name) {

        this.num = num;
        this.name = name;
    }
}

class Man {

    int age = 10;
    String name = "sam";
    Car car = new Car();

    public Man(int age, String name, Car car) {
        this.car = car;
        this.age = age;
        this.name = name;
    }



    public boolean add(Car car) {
        System.out.println(car.name);
        return true;
    }

}