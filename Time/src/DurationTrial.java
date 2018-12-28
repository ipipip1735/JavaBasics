import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * Created by Administrator on 2018/10/6.
 */

public class DurationTrial {
    public static void main(String[] args) {
        DurationTrial durationTrial = new DurationTrial();
//        durationTrial.constructor();
        durationTrial.of();
//        durationTrial.between();
//        durationTrial.from();
//        durationTrial.to();
    }

    private void between() {
        Instant start = Instant.EPOCH;
        Instant end = Instant.now();


        long ns = Duration.between(start, end).toNanos();
        System.out.println(ns);
    }

    private void of() {

        Duration duration = Duration.ofNanos(938902109000000l);
        System.out.println(duration.toDays());
        System.out.println(duration.toMinutes());
        System.out.println(duration);

//        Period period = Period.;
//        System.out.println(period);
    }

    private void to() {
        Duration duration = Duration.ofHours(2);
        System.out.println(duration.toSeconds());
    }


    private void from() {

//        Period period = Period.of(1, 1, 2);
//        for (TemporalUnit unit : period.getUnits()) {
//            System.out.println("unit is " + unit);
//        }

//        Duration duration = Duration.of(24 * 3600, ChronoUnit.SECONDS);
//        for (TemporalUnit unit : duration.getUnits()) {
//            System.out.println("unit is " + unit);
//        }
//        System.out.println(duration);

        Duration day = Duration.ofDays(1);
        Duration duration = Duration.from(day);
        System.out.println(duration);

    }

    private void constructor() {

        Duration duration;
//        duration = Duration.ofDays(1); //天
//        System.out.println(duration);
//
//        duration = Duration.ofHours(1); //时
//        System.out.println(duration);
//
//        duration = Duration.ofMinutes(1); //分
//        System.out.println(duration);
//
//        duration = Duration.ofSeconds(1); //秒
//        System.out.println(duration);
//        duration = Duration.ofSeconds(1, 123456789); //秒
//        System.out.println(duration);
//
//        duration = Duration.ofMillis(1); //毫秒
//        System.out.println(duration);
//
//        duration = Duration.ofNanos(1); //纳秒
//        System.out.println(duration);
//
//
//        duration = Duration.of(1, ChronoUnit.DAYS); //纳秒
//        System.out.println(duration);


        duration = Duration.parse("p3d");
        System.out.println(duration);

        duration = Duration.parse("P1DT1H2S");
        System.out.println(duration);

        duration = Duration.parse("Pt-2H");
        System.out.println(duration);

        duration = Duration.parse("PT-6H+18M");
        System.out.println(duration);

        duration = Duration.parse("PT3.123456789S");
        System.out.println(duration);


    }
}
