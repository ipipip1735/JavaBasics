import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.ValueRange;

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
//        instantTrial.with();
//        instantTrial.gets();
//        instantTrial.minus();
//        instantTrial.range();

    }

    private void with() {

        Instant instant = Instant.now();

//        Duration duration = Duration.ofSeconds(10);
//        Instant i = Instant.
//        instant.with()
    }

    private void range() {
        ValueRange valueRange;
        Instant instant = Instant.now();
        System.out.println("instant is " + instant);

        valueRange = instant.range(ChronoField.NANO_OF_SECOND );
        System.out.println("---NANO_OF_SECOND---");
        System.out.println("getMaximum is " + valueRange.getMaximum());
        System.out.println("getMinimum is " + valueRange.getMinimum());
        System.out.println("getSmallestMaximum is " + valueRange.getSmallestMaximum());
        System.out.println("getLargestMinimum is " + valueRange.getLargestMinimum());


        valueRange = instant.range(ChronoField.INSTANT_SECONDS );
        System.out.println("---INSTANT_SECONDS---");
        System.out.println("getMaximum is " + valueRange.getMaximum());
        System.out.println("getMinimum is " + valueRange.getMinimum());
        System.out.println("getSmallestMaximum is " + valueRange.getSmallestMaximum());
        System.out.println("getLargestMinimum is " + valueRange.getLargestMinimum());




    }

    private void minus() {

        Instant instant = Instant.now();
        Instant result;

        System.out.println(instant.getEpochSecond());

        result = instant.minus(Duration.ofMinutes(1));
        System.out.println(result.getEpochSecond());

        result = result.plus(Duration.ofSeconds(30));
        System.out.println(result.getEpochSecond());

    }

    private void gets() {

        Instant instant = Instant.now();
        System.out.println("instant is " + instant);
        System.out.println("getNano is " + instant.getNano());
        System.out.println("getEpochSecond is " + instant.getEpochSecond());
        System.out.println("toEpochMilli is " +instant.toEpochMilli());


//        Instant instant = Instant.now();
//        System.out.println(instant);
//        System.out.println("NANO_OF_SECOND is " + instant.get(ChronoField.NANO_OF_SECOND));
//        System.out.println("MICRO_OF_SECOND is " + instant.get(ChronoField.MICRO_OF_SECOND));
//        System.out.println("MILLI_OF_SECOND is " + instant.get(ChronoField.MILLI_OF_SECOND));
//        System.out.println("INSTANT_SECONDS is " + instant.get(ChronoField.INSTANT_SECONDS)); //不支持，报错

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
        System.out.println(instant);


//        System.out.println(zonedDateTime);
//        zonedDateTime = instant.atZone(ZoneId.of("Z"));
//        System.out.println(zonedDateTime);
//        zonedDateTime = instant.atZone(ZoneId.of("+8"));
//        System.out.println(zonedDateTime);
//        zonedDateTime = instant.atZone(ZoneId.of("UTC"));
//        System.out.println(zonedDateTime);
//        zonedDateTime = instant.atZone(ZoneId.of("UTC+8"));
//        System.out.println(zonedDateTime);
//        zonedDateTime = instant.atZone(ZoneId.of("GMT"));
//        System.out.println(zonedDateTime);
//        zonedDateTime = instant.atZone(ZoneId.of("GMT-6"));
//        System.out.println(zonedDateTime);


        //支持夏令时
//        ZonedDateTime zonedDateTime;
//        zonedDateTime = instant.atZone(ZoneId.of("Europe/Paris"));
//        System.out.println(zonedDateTime);
//        zonedDateTime = instant.atZone(ZoneId.of("Europe/Paris"));
//        System.out.println(zonedDateTime);

        //不支持夏令时
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.of("+8"));
        System.out.println(offsetDateTime);



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
