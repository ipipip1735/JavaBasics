import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * Created by Administrator on 2018/10/6.
 */

public class TemporalUnitTrial {
    public static void main(String[] args) {
        TemporalUnitTrial unit = new TemporalUnitTrial();
        unit.base();
    }

    private void base() {

        TemporalUnit unit = ChronoUnit.DAYS;
        System.out.println("getDuration() is " + unit.getDuration());

    }
}
