import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Created by Administrator on 2018/10/10 8:43.
 */

public class LocalDateTrial {
    public static void main(String[] args) {
        LocalDateTrial localDateTimeTrial = new LocalDateTrial();
//        localDateTimeTrial.constructor();
        localDateTimeTrial.base();
    }

    private void base() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println("lengthOfMonth is " + localDate.lengthOfMonth());
        System.out.println("lengthOfYear is " + localDate.lengthOfYear());
    }

    private void constructor() {
        System.out.println("MIN is " + LocalDate.EPOCH);
        System.out.println("MIN is " + LocalDate.MIN);
        System.out.println("MAX is " + LocalDate.MAX);


    }

}
