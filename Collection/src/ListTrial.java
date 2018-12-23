import java.util.*;
import java.util.function.Consumer;

public class ListTrial {


    public static void main(String[] args) {
        ListTrial listTrial = new ListTrial();
        listTrial.basic();
    }

    private void basic() {
        List<String> list = new ArrayList<String>();
        list.add("aa");
        list.add("b");
        list.add("ca");
//        System.out.println(list.get(0));

//        list.forEach(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println("s is " + s);
//            }
//        });
//
//        list.forEach(s -> System.out.println("s is " + s));

        Consumer consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("s is " + s);
            }
        }.andThen(s -> System.out.println("t is " + s));
        ;


        list.forEach(consumer);
    }


}
