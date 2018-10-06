import java.time.*;

/**
 * Created by Administrator on 2018/10/5.
 */

public class InstantTrial {
    public static void main(String[] args) {
        InstantTrial instantTrial = new InstantTrial();
//        instantTrial.constructor();
//        instantTrial.compare();
//        instantTrial.zone();
//        instantTrial.offset();
//        instantTrial.gets();
        instantTrial.minus();

    }

    private void minus() {
        Instant instant = Instant.now();
//        instant.minus();


    }

    private void gets() {

        Instant instant = Instant.now();
        System.out.println("instant is " + instant);
        System.out.println("getNano is " + instant.getNano());
        System.out.println("getEpochSecond is " + instant.getEpochSecond());
        System.out.println("toEpochMilli is " +instant.toEpochMilli());



    }

    private void constructor() {


//        Instant instant = Instant.now();
//        System.out.println(instant);

//        Clock clock = Clock.system(ZoneId.of("+9"));
//        Instant instant = Instant.now(clock);
//        ZonedDateTime zonedDateTime = instant.atZone(clock.getZone());
//        System.out.println(zonedDateTime);

        //解析字符串
//        Instant instant = Instant.parse("2007-12-03T10:15:30.00Z");
//        System.out.println(instant);




        Instant instant;
        //创建时间截
        instant = Instant.ofEpochSecond(1234567890);
        System.out.println(instant);

        //创建毫秒级时间截
        instant = Instant.ofEpochMilli(1234567890);
        System.out.println(instant);



    }

    private void offset() {
        Instant instant = Instant.now();
        System.out.println(instant);

//        OffsetDateTime offsetDateTime = instant.atOffset()


//        System.out.println(zonedDateTime);
    }

    private void zone() {
        Instant instant = Instant.now();

        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Asia/Shanghai"));
//        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Z"));
//        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("+8"));
//        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC"));
//        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC+8"));
//        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("GMT"));
//        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("GMT-6"));

        System.out.println(zonedDateTime);
    }

    private void compare() {

        Instant start = Instant.now();
        System.out.println(start);

        String temp;
        for (int i = 0; i < 999; i++) {
            temp = "t" + i;
        }


        Instant end = Instant.now();
        System.out.println(end);

        System.out.println("span is " + end.compareTo(start) / 1000 / 1000);


    }
}
