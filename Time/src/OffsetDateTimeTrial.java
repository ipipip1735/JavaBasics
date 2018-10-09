import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;

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

        System.out.println("getOffset     is " + offsetDateTime.getLong());
//        System.out.println(offsetDateTime.get(ChronoField.YEAR));
//        System.out.println(offsetDateTime.get(ChronoField.DAY_OF_WEEK));
//        System.out.println(offsetDateTime.get(ChronoField.DAY_OF_YEAR));



        System.out.println("getOffset     is " + offsetDateTime.getOffset());
        System.out.println("getHour       is " + offsetDateTime.getHour());
        System.out.println("getMinute     is " + offsetDateTime.getMinute());
        System.out.println("getSecond     is " + offsetDateTime.getSecond());
        System.out.println("getNano       is " + offsetDateTime.getNano());
        System.out.println("getYear       is " + offsetDateTime.getYear());
        System.out.println("getMonth      is " + offsetDateTime.getMonth());
        System.out.println("getMonthValue is " + offsetDateTime.getMonthValue());
        System.out.println("getDayOfMonth is " + offsetDateTime.getDayOfMonth());
        System.out.println("getDayOfWeek  is " + offsetDateTime.getDayOfWeek());
        System.out.println("getDayOfYear  is " + offsetDateTime.getDayOfYear());

    }

    private void with() {
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println(offsetDateTime);
        System.out.println(offsetDateTime.withHour(0));
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