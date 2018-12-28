import java.time.*;
import java.time.chrono.Chronology;
import java.time.temporal.*;

/**
 * Created by Administrator on 2018/10/5.
 */

public class InstantTrial {
    public static void main(String[] args) {
        InstantTrial instantTrial = new InstantTrial();
        instantTrial.constructor();
//        instantTrial.compare();
//        instantTrial.zone();
//        instantTrial.offset();
//        instantTrial.with();
//        instantTrial.gets();
//        instantTrial.minus();
//        instantTrial.range();
//        instantTrial.util();
//        instantTrial.query();

//        interval();
    }

    private static void interval() {
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(System.currentTimeMillis());

        long startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
        long endTime = System.nanoTime();
        System.out.println("elapse is " + (endTime - startTime) + "ns");
    }

    private void query() {
        //使用工具类
//        Instant instant = Instant.now();
//        Chronology chronology = instant.query(TemporalQueries.chronology());
//        System.out.println("chronology is " + chronology);
//
//        LocalDate localDate = instant.query(TemporalQueries.localDate());
//        System.out.println("localDate is " + localDate);
//
//        LocalTime localTime = instant.query(TemporalQueries.localTime());
//        System.out.println("localTime is " + localTime);
//
//        ZoneOffset zoneOffset = instant.query(TemporalQueries.offset());
//        System.out.println("zoneOffset is " + zoneOffset);
//
//        TemporalUnit temporalUnit = instant.query(TemporalQueries.precision());
//        System.out.println("temporalUnit is " + temporalUnit);
//
//        ZoneId zoneId = instant.query(TemporalQueries.zone());
//        System.out.println("zoneId is " + zoneId);



        //实现TemporalQuery接口
        Instant instant = Instant.now();
        TemporalQuery<String> temporalQuery = new TemporalQuery<>(){
            @Override
            public String queryFrom(TemporalAccessor temporal) {
                System.out.println("---temporal---");
                System.out.println(temporal);
                return "end";
            }
        };

        String result = instant.query(temporalQuery);
        System.out.println(result);



    }


    private void util() {

        Instant start = Instant.ofEpochSecond(12345);
        System.out.println(start);

        Instant end = Instant.ofEpochSecond(12345 + 3600 * 24);
        System.out.println(end);

        System.out.println("NANOS     is " + start.until(end, ChronoUnit.NANOS));
        System.out.println("MICROS    is " + start.until(end, ChronoUnit.MICROS));
        System.out.println("MILLIS    is " + start.until(end, ChronoUnit.MILLIS));
        System.out.println("SECONDS   is " + start.until(end, ChronoUnit.SECONDS));
        System.out.println("MINUTES   is " + start.until(end, ChronoUnit.MINUTES));
        System.out.println("HOURS     is " + start.until(end, ChronoUnit.HOURS));
        System.out.println("HALF_DAYS is " + start.until(end, ChronoUnit.HALF_DAYS));
        System.out.println("DAYS      is " + start.until(end, ChronoUnit.DAYS));



    }

    private void with() {

//        Instant instant1 = Instant.now();
//        System.out.println(instant1);
//        Instant instant2 = Instant.ofEpochSecond(123);
//        System.out.println(instant2);
//
////        Instant instant3 = instant1.with(instant2);
//        Instant instant3 = (Instant) instant2.adjustInto(instant1);
//        System.out.println(instant3);





        Instant instant1 = Instant.now();
        System.out.println(instant1);
        Instant instant2 = instant1.with(ChronoField.INSTANT_SECONDS, 1);
        System.out.println(instant2);

        Instant instant3;

        instant3 = instant1.with(ChronoField.MILLI_OF_SECOND, 1);
        System.out.println(instant3);
        instant3 = instant1.with(ChronoField.MILLI_OF_SECOND, 111);
        System.out.println(instant3);

        instant3 = instant1.with(ChronoField.MICRO_OF_SECOND, 222);
        System.out.println(instant3);
        instant3 = instant1.with(ChronoField.MICRO_OF_SECOND, 111222);
        System.out.println(instant3);

        instant3 = instant1.with(ChronoField.NANO_OF_SECOND, 333);
        System.out.println(instant3);
        instant3 = instant1.with(ChronoField.NANO_OF_SECOND, 111222333);
        System.out.println(instant3);


    }

    private void range() {
        ValueRange valueRange;
        Instant instant = Instant.now();
        System.out.println("instant is " + instant);

//        valueRange = instant.range(ChronoField.NANO_OF_SECOND );
//        System.out.println("---NANO_OF_SECOND---");
//        System.out.println("getMaximum is " + valueRange.getMaximum());
//        System.out.println("getMinimum is " + valueRange.getMinimum());
//        System.out.println("getSmallestMaximum is " + valueRange.getSmallestMaximum());
//        System.out.println("getLargestMinimum is " + valueRange.getLargestMinimum());


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

    }

    private void constructor() {


//        Instant instant = Instant.now();
//        System.out.println(instant);

//        Clock clock = Clock.system(ZoneId.of("+9"));
//        Instant instant = Instant.now(clock);
//        ZonedDateTime zonedDateTime = instant.atZone(clock.getZone());
//        System.out.println(zonedDateTime);

        //解析字符串
//        Instant instant = Instant.parse("2007-12-03T10:15:03Z");
//        System.out.println(instant);






//        Instant instant;
//        //创建时间截
//        instant = Instant.ofEpochSecond(1234567890);
//        System.out.println(instant);
//        instant = Instant.ofEpochSecond(1234567890,987654321);
//        System.out.println(instant);

//        //创建毫秒级时间截
//        instant = Instant.ofEpochMilli(987654321);
//        System.out.println(instant);





        //类型转换
//        OffsetDateTime offsetDateTime = OffsetDateTime.now();
//        System.out.println(offsetDateTime);
//
//        Instant instant = Instant.from(offsetDateTime);
//        System.out.println(instant);

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
