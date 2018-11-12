/**
 * Created by Administrator on 2018/10/26 11:08.
 */

public class RetryTrial {
    public static void main(String[] args) {


        retry:
        for (int i = 10; i < 15; i++) {
            System.out.println(i);


            for (int j = 20; j < 25; j++) {
                System.out.println(j);

                for (int k = 30; k < 35; k++) {
                    System.out.println(k);
                    if (k == 33) break retry;
                }
            }

        }


    }
}
