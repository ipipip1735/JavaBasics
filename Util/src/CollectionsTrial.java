import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2018/12/2.
 */

public class CollectionsTrial {
    public static void main(String[] args) {
        CollectionsTrial collectionsTrial = new CollectionsTrial();
//        collectionsTrial.sync();
//        collectionsTrial.swap();
//        collectionsTrial.sort();
//        collectionsTrial.reverse();
//        collectionsTrial.rotate();
//        collectionsTrial.disjoint();
//        collectionsTrial.max();
//        collectionsTrial.copy();
        collectionsTrial.singletonList();
    }

    private void singletonList() {

        List<String> list = Collections.singletonList("ok");
        System.out.println("list = " + list);
        list = Collections.singletonList("no");
        System.out.println("list = " + list);
    }

    private void copy() {

        //填充复制
        List<String> list = Collections.nCopies(2, "-");
        System.out.println(list.size() + "|" + list);

    }

    private void max() {
        //版本一
//        List<String> list = Arrays.asList("aa", "bb", "dd", "cc");
//        System.out.println(Collections.max(list));

        //版本二
//        List<String> list = Arrays.asList("aa", "bb", "dd", "cc");
//        System.out.println(Collections.max(list, Collections.reverseOrder()));

        //版本三：和版本二等价，Comparator.reverseOrder()是间接调用Collections.reverseOrder()
        List<String> list = Arrays.asList("aa", "bb", "dd", "cc");
        System.out.println(Collections.max(list, Comparator.reverseOrder()));
    }

    private void disjoint() {
        List<String> list1 = Arrays.asList("aa", "bb", "cc", "dd");
        List<String> list2 = Arrays.asList("bb", "gg");
//        List<String> list2 = Arrays.asList("ee", "gg");
        System.out.println(Collections.disjoint(list1, list2));

    }

    private void rotate() {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        System.out.println(list);

//        Collections.rotate(list, 1);
//        System.out.println(list);

//        Collections.rotate(list, 2);
//        System.out.println(list);
        Collections.rotate(list, 3);
        System.out.println(list);
//        Collections.rotate(list, 0);
//        System.out.println(list);
    }


    private void sort() {

        List<BigDecimal> list = Arrays.asList(BigDecimal.ZERO, BigDecimal.TEN, BigDecimal.ONE);
        System.out.println(list);

//        Collections.sort(list);
        Collections.sort(list, BigDecimal::compareTo);
        System.out.println(list);

    }

    private void reverse() {

        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        System.out.println(list);

        Collections.reverse(list);
        System.out.println(list);

    }

    private void swap() {

        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        System.out.println(list);

        Collections.swap(list, 1, 2);
        System.out.println(list);
    }


    private void sync() {

        HashMap<String, Integer> hashMap = new HashMap<>();
        Map synchronizedMap = Collections.synchronizedMap(hashMap);
        synchronizedMap.put(1, null);


    }
}
