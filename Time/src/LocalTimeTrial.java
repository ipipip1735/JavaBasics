import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * Created by Administrator on 2018/10/10 8:43.
 */

public class LocalTimeTrial {
    public static void main(String[] args) {
        LocalTimeTrial localDateTimeTrial = new LocalTimeTrial();
        localDateTimeTrial.constructor();

    }

    private void constructor() {

        System.out.println(LocalTime.now());

//        System.out.println("MAX is " + LocalTime.MAX);
//        System.out.println("MIN is " + LocalTime.MIN);
//        System.out.println("MIDNIGHT is " + LocalTime.MIDNIGHT);
//        System.out.println("NOON is " + LocalTime.NOON);


//        LocalTime localTime = LocalTime.ofInstant(
//                Instant.ofEpochSecond(Instant.now().getEpochSecond(), 987654321),
//                ZoneId.of("+8"));
//        System.out.println(localTime);


        System.out.println(LocalTime.parse("23:15:30.987654321"));


    }
}
