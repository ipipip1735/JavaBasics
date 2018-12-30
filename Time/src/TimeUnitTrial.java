import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/12/30.
 */
public class TimeUnitTrial {
    public static void main(String[] args) {
        TimeUnitTrial timeUnitTrial = new TimeUnitTrial();
        timeUnitTrial.convert();
    }

    private void convert() {
        long millis = 3621000L;
        String s = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        System.out.println(s);

        String t = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis)%60,
                TimeUnit.MILLISECONDS.toSeconds(millis)%60);
        System.out.println(t);
    }
}
