import java.util.Arrays;

/**
 * Created by Administrator on 2020/3/5 5:38.
 */
public class ArraysTrial {
    public static void main(String[] args) {
        ArraysTrial arraysTrial = new ArraysTrial();
        arraysTrial.spliterator();
//        arraysTrial.deepToString();


    }

    private void deepToString() {
        String[] strings = new String[]{"aa", "bb"};


        for (String s : strings) System.out.println(s);

        System.out.println(Arrays.deepToString(strings));
    }

    private void spliterator() {
        String[] strings = new String[]{"aa", "bb", "cc"};


        //统计尺寸
//        System.out.println(Arrays.spliterator(strings).estimateSize());


        //遍历
//        Arrays.spliterator(strings).forEachRemaining(System.out::println);


        //反复取当前元素
        for (int i = 0; i < strings.length; i++) {
            Arrays.spliterator(strings).tryAdvance(System.out::println);
        }

    }
}
