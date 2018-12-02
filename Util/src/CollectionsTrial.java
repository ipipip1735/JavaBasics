import java.util.*;

/**
 * Created by Administrator on 2018/12/2.
 */

public class CollectionsTrial {
    public static void main(String[] args) {
        CollectionsTrial collectionsTrial = new CollectionsTrial();
//        collectionsTrial.sync();
//        collectionsTrial.swap();
//        collectionsTrial.reverse();
//        collectionsTrial.rotate();
        collectionsTrial.disjoint();
    }

    private void disjoint() {
//        List<String> list1 = Arrays.asList("aa", "bb", "cc", "dd");
//        List<String> list2 = Arrays.asList("bb", "gg");
//        List<String> list2 = Arrays.asList("ee", "gg");
//        System.out.println(Collections.disjoint(list1, list2));

    }

    private void rotate() {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        System.out.println(list);

        Collections.rotate(list, 1);
        System.out.println(list);

//        Collections.rotate(list, 2);
//        System.out.println(list);
//        Collections.rotate(list, 3);
//        System.out.println(list);
//        Collections.rotate(list, 0);
//        System.out.println(list);
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
