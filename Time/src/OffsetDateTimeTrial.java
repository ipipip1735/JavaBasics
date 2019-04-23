import java.time.*;
import java.time.chrono.Chronology;
import java.time.temporal.*;

/**
 * Created by Administrator on 2018/11/8.
 */

public class OffsetDateTimeTrial {
    public static void main(String[] args) {
        OffsetDateTimeTrial offsetDateTimeTrial = new OffsetDateTimeTrial();
//        OffsetDateTimeTrial.constructor();
//        offsetDateTimeTrial.to();
//        offsetDateTimeTrial.with();
        offsetDateTimeTrial.get();
//        offsetDateTimeTrial.compare();
//        offsetDateTimeTrial.query();
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

    private void get() {
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println(offsetDateTime);

        //get系列方法
        System.out.println("get|YEAR is " + offsetDateTime.get(ChronoField.YEAR));
        System.out.println("get|YEAR_OF_ERA is " + offsetDateTime.get(ChronoField.YEAR_OF_ERA));//公元多少年
        System.out.println("get|MONTH_OF_YEAR is " + offsetDateTime.get(ChronoField.MONTH_OF_YEAR));
        System.out.println("---------------");
        System.out.println("get|ALIGNED_WEEK_OF_YEAR is " + offsetDateTime.get(ChronoField.ALIGNED_WEEK_OF_YEAR));//一个年的第几周
        System.out.println("get|ALIGNED_WEEK_OF_MONTH is " + offsetDateTime.get(ChronoField.ALIGNED_WEEK_OF_MONTH));//一个月的第几周
        System.out.println("---------------");
        System.out.println("get|DAY_OF_YEAR is " + offsetDateTime.get(ChronoField.DAY_OF_YEAR));//一年的第几天
        System.out.println("get|DAY_OF_WEEK is " + offsetDateTime.get(ChronoField.DAY_OF_WEEK));//一周的第几天
        System.out.println("---------------");
        System.out.println("get|DAY_OF_MONTH is " + offsetDateTime.get(ChronoField.DAY_OF_MONTH));//一个月的第几天
        System.out.println("get|OFFSET_SECONDS is " + offsetDateTime.get(ChronoField.OFFSET_SECONDS));//时区偏移量，单位秒，比如+8就是3600*8=28800

        //getLong系列方法，精度更高，功能与get一样
        System.out.println("getLong|YEAR is " + offsetDateTime.getLong(ChronoField.YEAR));
        System.out.println("getLong|YEAR_OF_ERA is " + offsetDateTime.getLong(ChronoField.YEAR_OF_ERA));//公元多少年
        System.out.println("getLong|MONTH_OF_YEAR is " + offsetDateTime.getLong(ChronoField.MONTH_OF_YEAR));
        System.out.println("getLong|DAY_OF_YEAR is " + offsetDateTime.getLong(ChronoField.DAY_OF_YEAR));//一年的第几天
        System.out.println("getLong|DAY_OF_WEEK is " + offsetDateTime.getLong(ChronoField.DAY_OF_WEEK));//一周的第几天
        System.out.println("getLong|DAY_OF_MONTH is " + offsetDateTime.getLong(ChronoField.DAY_OF_MONTH));//一个月的第几天
        System.out.println("---------------");
        System.out.println("getLong|ALIGNED_WEEK_OF_YEAR is " + offsetDateTime.get(ChronoField.ALIGNED_WEEK_OF_YEAR));//一个年的第几周
        System.out.println("getLong|ALIGNED_WEEK_OF_MONTH is " + offsetDateTime.get(ChronoField.ALIGNED_WEEK_OF_MONTH));//一个月的第几周
        System.out.println("---------------");
        System.out.println("getLong|INSTANT_SECONDS is " + offsetDateTime.getLong(ChronoField.INSTANT_SECONDS));//本地化后的时间戳，单位秒(不能使用get，因为精度不够，必须使用getLong)
        System.out.println("getLong|OFFSET_SECONDS is " + offsetDateTime.getLong(ChronoField.OFFSET_SECONDS));//时区偏移量，单位秒，比如+8就是3600*8=28800


        //工具方法
//        System.out.println("getOffset     is " + offsetDateTime.getOffset());
//        System.out.println("getHour       is " + offsetDateTime.getHour());
//        System.out.println("getMinute     is " + offsetDateTime.getMinute());
//        System.out.println("getSecond     is " + offsetDateTime.getSecond());
//        System.out.println("getNano       is " + offsetDateTime.getNano());
//        System.out.println("getYear       is " + offsetDateTime.getYear());
//        System.out.println("getMonth      is " + offsetDateTime.getMonth());
//        System.out.println("getMonthValue is " + offsetDateTime.getMonthValue());
//        System.out.println("getDayOfMonth is " + offsetDateTime.getDayOfMonth());
//        System.out.println("getDayOfWeek  is " + offsetDateTime.getDayOfWeek());
//        System.out.println("getDayOfYear  is " + offsetDateTime.getDayOfYear());

    }

    private void with() {
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println(offsetDateTime);
        System.out.println(offsetDateTime.withHour(1).withMinute(25).withSecond(55));
    }

    private void to() {
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println(offsetDateTime);
        System.out.println("    toString is " + offsetDateTime.toString());
        System.out.println("toOffsetTime is " + offsetDateTime.toOffsetTime());

        System.out.println("      toInstant is " + offsetDateTime.toInstant());
        System.out.println("  toEpochSecond is " + offsetDateTime.toEpochSecond());
        System.out.println("toZonedDateTime is " + offsetDateTime.toZonedDateTime());
        System.out.println("toLocalDateTime is " + offsetDateTime.toLocalDateTime());
        System.out.println("    toLocalDate is " + offsetDateTime.toLocalDate());
        System.out.println("    toLocalTime is " + offsetDateTime.toLocalTime());

    }

    private void query() {
        //使用工具类
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        Chronology chronology = offsetDateTime.query(TemporalQueries.chronology());
        System.out.println("chronology is " + chronology);

        LocalDate localDate = offsetDateTime.query(TemporalQueries.localDate());
        System.out.println("localDate is " + localDate);

        LocalTime localTime = offsetDateTime.query(TemporalQueries.localTime());
        System.out.println("localTime is " + localTime);

        ZoneOffset zoneOffset = offsetDateTime.query(TemporalQueries.offset());
        System.out.println("zoneOffset is " + zoneOffset);

        TemporalUnit temporalUnit = offsetDateTime.query(TemporalQueries.precision());
        System.out.println("temporalUnit is " + temporalUnit);

        ZoneId zoneId = zoneOffset.query(TemporalQueries.zone());
        System.out.println("zoneId is " + zoneId);


        //实现TemporalQuery接口
//        Instant instant = Instant.now();
//        TemporalQuery<String> temporalQuery = new TemporalQuery<>(){
//            @Override
//            public String queryFrom(TemporalAccessor temporal) {
//                System.out.println("---temporal---");
//                System.out.println(temporal);
//                return "end";
//            }
//        };
//
//        String result = instant.query(temporalQuery);
//        System.out.println(result);


    }

    private static void constructor() {

        OffsetDateTime offsetDateTime;

//        offsetDateTime = OffsetDateTime.now();
//        System.out.println(offsetDateTime);
//        offsetDateTime = OffsetDateTime.now(Clock.system(ZoneId.of("+8")));
//        System.out.println(offsetDateTime);
//        offsetDateTime = OffsetDateTime.now(ZoneId.of("+8"));
//        System.out.println(offsetDateTime);

    }
}
