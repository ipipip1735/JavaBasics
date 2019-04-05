import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;

public class ListTrial {


    public static void main(String[] args) {
        ListTrial listTrial = new ListTrial();
//        listTrial.basic();
        listTrial.copyOnWriteArrayList();//并行读写


    }

    private void copyOnWriteArrayList() {
        List<String> list = new ArrayList<>(); //非线程安全，抛异常
//        List<String> list = new CopyOnWriteArrayList<>(); //线程安全，支持并行读写


        for (int i = 0; i < 5; i++) { //initial
            list.add("aaa" + i);
        }

        int i = 0;
        for (String s : list){
            System.out.println(list.get(i));
            System.out.println(list.set(i, "ooo"));//可以set，但不能add()或remove()
            System.out.println(list.remove(i)); //抛异常
            System.out.println(list.add("kkk"));//抛异常
        }

        for (String s : list) { //print
            System.out.println(s);
        }
    }

    private void basic() {
        List<String> list = new ArrayList<String>();
        list.add("aa");
        list.add("b");
        list.add("ca");
//        System.out.println(list.get(0));

        //方式一
        Consumer consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("s is " + s);
            }
        }.andThen(s -> System.out.println("t is " + s));
        list.forEach(consumer);

        //方式二：使用匿名类
//        list.forEach(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println("s is " + s);
//            }
//        });

        //方式三：使用Lambda表达式
//        list.forEach(s -> System.out.println("s is " + s));


        //search
        System.out.println("id is " + list.indexOf("b"));
        System.out.println("id is " + list.indexOf("b"));

        //toArray
        String[] strings = list.toArray(new String[0]);
        System.out.println("length is " + strings.length);
    }


}
