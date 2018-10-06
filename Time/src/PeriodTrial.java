import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * Created by Administrator on 2018/10/6.
 */

public class PeriodTrial {
    public static void main(String[] args) {
        PeriodTrial periodTrial = new PeriodTrial();
        periodTrial.base();
    }

    private void base() {

//        Period period;
//        period = Period.of(1, -2, 0);
//        System.out.println(period);
//
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
