import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * Created by Administrator on 2018/10/6.
 */

public class PeriodTrial {
    public static void main(String[] args) {
        PeriodTrial periodTrial = new PeriodTrial();
//        periodTrial.base();
//        periodTrial.from();
        periodTrial.between();
    }

    private void between() {

        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(2000, Month.JANUARY, 1);
        Period p = Period.between(birthday, today);

        long p2 = ChronoUnit.DAYS.between(birthday, today);

        System.out.println("You are " + p.getYears() + " years, " + p.getMonths() +
                " months, and " + p.getDays() +
                " days old. (" + p2 + " days total)");
    }

    private void from() {
        Period days = Period.ofDays(2);
        System.out.println(days);
    }

    private void base() {

//        Period period;
//        period = Period.of(1, -2, 0);
//        System.out.println(period);

//        period = Period.ofWeeks(2);
//        System.out.println(period);




        Period period;

        period = Period.parse("P1Y2D");
        System.out.println(period);

        period = Period.parse("P1M1W");
        System.out.println(period);

        period = Period.parse("-P1Y+1M-1D");
        System.out.println(period);


    }
}
