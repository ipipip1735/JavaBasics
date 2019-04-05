import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2018/10/20.
 */
public class SetTrial {
    public static void main(String[] args) {
        SetTrial setTrial = new SetTrial();
//        setTrial.removeSet();
//        setTrial.convert();
        setTrial.copyOnWriteArraySet();//并行读写
    }

    private void copyOnWriteArraySet() {

        Set<String> set = new HashSet<>(); //非线程安全，抛异常
//        Set<String> set = new CopyOnWriteArraySet<>(); //线程安全，支持并行读写


        for (int i = 0; i < 5; i++) { //initial
            set.add("aaa" + i);
        }

        int i = 0;
        for (String s : set){
            System.out.println(s);
            System.out.println(set.add(s + "-")); //抛异常
//            System.out.println(set.remove(s));
        }

        for (String s : set) { //print
            System.out.println(s);
        }
    }

    private void convert() {

        //toArray
//        Set<Long> longs = new HashSet<>(Arrays.asList(1l, 2l, 3l, 4l, 5l));
//        long[] ls = longs.stream()
//                .mapToLong(Long::longValue)
//                .toArray();
//        for (long l : ls) {
//            System.out.println(l);
//        }




        //toSet
        Long[] ls = {1l, 22l, 343l};
        Set<Long> longSet = new HashSet<>(Arrays.asList(ls));

    }

    private void removeSet() {

        Set<Long> longs = new HashSet<>(Arrays.asList(1l, 2l, 3l, 4l, 5l));
        long[] ls = longs.stream()
                .mapToLong(Long::longValue)
                .toArray();



        //下面的代码是错误的，根本无法删除，循环时被Set锁定了
//        for (long l : longs) {
//            System.out.println(l);
//            longs.remove(l);
//        }

        //正确的做法
        Set<Long> temp = (Set<Long>) ((HashSet<Long>) longs).clone();

        for (long l : temp) {
            System.out.println(l);
            longs.remove(l);
        }
        temp = null;
        System.out.println(longs);

    }
}
