import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2018/10/20.
 */
public class SetTrial {
    public static void main(String[] args) {
        SetTrial setTrial = new SetTrial();
//        setTrial.removeSet();
        setTrial.convert();
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
