/**
 * Created by Administrator on 2018/12/17 13:58.
 */

public class StaticTrial {
    static int k;

    public static void main(String[] args) {

        StaticTrial.k = 665;
        StaticTrial staticTrial1 = new StaticTrial();
        StaticTrial staticTrial2 = new StaticTrial();
        System.out.println(staticTrial1.k);
        System.out.println(staticTrial2.k);
        staticTrial1.k = 5;
        System.out.println(staticTrial1.k);
        System.out.println(staticTrial2.k);
        StaticTrial.k = 559;
        System.out.println(staticTrial1.k);
        System.out.println(staticTrial2.k);
    }
}
