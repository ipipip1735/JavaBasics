import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by Administrator on 2018/10/10 8:43.
 */

public class LocalDateTimeTrial {
    public static void main(String[] args) {
        LocalDateTimeTrial localDateTimeTrial = new LocalDateTimeTrial();
        localDateTimeTrial.constructor();
//        localDateTimeTrial.base();
    }

    private void constructor() {
//        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("+8"));
//        System.out.println(localDateTime.getNano());


        LocalDateTime localDateTime = LocalDateTime.parse("-2011-08-05T15:20:30.987654321");
        System.out.println(localDateTime.getNano());

    }

    private void base() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("+8"));
        System.out.println(localDateTime.getNano());
    }
}
