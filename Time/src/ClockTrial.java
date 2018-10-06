import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

/**
 * Created by Administrator on 2018/10/5.
 */

public class ClockTrial {
    public static void main(String[] args) {
        ClockTrial clockTrial = new ClockTrial();
        clockTrial.constructor();
//        clockTrial.tick();
//        clockTrial.offset();
    }

    private void offset() {

        Clock base = Clock.systemDefaultZone();
        System.out.println(base.millis());

        Clock clock = Clock.offset(base, Duration.ofMinutes(1));
        System.out.println(clock.millis());

    }

    private void tick() {
        Clock clock;

//        while (true) {
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            clock = Clock.systemDefaultZone();
//            System.out.println(clock.millis());
//
//            //以分钟为精度来读取时间
//            long s = clock.tickMinutes(clock.getZone()).millis();
//            System.out.println(s);
//        }

        while (true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clock = Clock.systemDefaultZone();
            System.out.println(clock.millis()); //读取当前时间

            //以年为精度来读取时间
            long s = clock.tick(clock, Duration.ofDays(2)).millis();
            System.out.println(s);
        }


    }

    private void constructor() {
//        Clock clock = Clock.system(ZoneId.of("+8"));
//        System.out.println(clock.getZone());


//        Clock clock = Clock.systemUTC();
//        System.out.println(clock.getZone());


//        Clock clock = Clock.systemDefaultZone();
//        System.out.println(clock.getZone());



        Clock clock = Clock.fixed(Instant.now(), ZoneId.of("-5"));//时区变为华盛顿时区，即西5
        System.out.println(clock);
        System.out.println(clock.getZone());

    }
}
