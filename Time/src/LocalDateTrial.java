import java.time.LocalDate;

/**
 * Created by Administrator on 2018/10/10.
 */

public class LocalDateTrial {
    public static void main(String[] args) {
        LocalDateTrial localTimeTrial = new LocalDateTrial();
        LocalDateTrial.constructor();
    }

    private static void constructor() {
        LocalDate localDate = LocalDate.ofEpochDay(1);
        System.out.println(localDate);
    }
}
