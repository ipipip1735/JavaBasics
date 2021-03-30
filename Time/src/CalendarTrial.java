import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Administrator on 2018/12/28.
 */

public class CalendarTrial {
    public static void main(String[] args) {

        CalendarTrial calendarTrial = new CalendarTrial();
//        calendarTrial.zoneID();
//        calendarTrial.constructor();
//        calendarTrial.format();
        calendarTrial.getDate();

    }

    private void getDate() {

        long absSeconds = Math.abs(167069600000L - 1617067909275L);
        System.out.println("absSeconds = " + absSeconds);

        String[] dhms = new String[3];


        long seconds = absSeconds / 1000 % 60;
        long minutes = absSeconds / (1000 * 60) % 60;
        long hours = absSeconds / (1000 * 60 * 60) % 24;


        String temp = Long.valueOf(hours).toString();
        System.out.println("temp = " + temp);
        dhms[0] = temp.length() < 2 ? "0" + temp : temp;

        temp = Long.valueOf(minutes).toString();
        System.out.println("temp = " + temp);
        dhms[1] = temp.length() < 2 ? "0" + temp : temp;

        temp = Long.valueOf(seconds).toString();
        System.out.println("temp = " + temp);
        dhms[2] = temp.length() < 2 ? "0" + temp : temp;


        System.out.println(dhms[0] + "时" + dhms[1] + "分" + dhms[2] + "秒");
    }


    private void format() {

        TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
        Date date = new Date(System.currentTimeMillis());

        DateFormat dataFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
        dataFormat.setTimeZone(timeZone);

        StringBuffer stringBuffer = new StringBuffer(1024);
        FieldPosition fieldPosition = new FieldPosition(DateFormat.Field.DAY_OF_WEEK);
        dataFormat.format(date, stringBuffer, fieldPosition);
        System.out.println(stringBuffer);

        String part = stringBuffer.substring(fieldPosition.getBeginIndex(), fieldPosition.getEndIndex());
        System.out.println(part);
    }

    private void zoneID() {

        //打印所支持的所有时区ID
//        for (String id : TimeZone.getAvailableIDs()) {
//            System.out.println(id);
//        }


        //方式一
//        TimeZone timeZone = TimeZone.getTimeZone("GMT+08:00");
//        Calendar calendar = Calendar.getInstance(timeZone);
//        calendar.setTimeInMillis(System.currentTimeMillis());
//
//        DateFormat dataFormat = DateFormat.getInstance();
//        dataFormat.setCalendar(calendar);
//        String date = dataFormat.format(calendar.getTime());
//        System.out.println(date);


        //方式二
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
        Date date = new Date(System.currentTimeMillis());

        DateFormat dataFormat = DateFormat.getInstance();
        dataFormat.setTimeZone(timeZone);
        String result = dataFormat.format(date);
        System.out.println(result);

    }

    private void constructor() {

        TimeZone timeZone = TimeZone.getTimeZone("GMT+7");
        Calendar calendar = Calendar.getInstance(timeZone);

        DateFormat dataFormat1 = DateFormat.getInstance();
        DateFormat dataFormat2 = DateFormat.getDateInstance(DateFormat.LONG);
        DateFormat dataFormat3 = DateFormat.getDateInstance(DateFormat.FULL, Locale.ENGLISH);
        DateFormat dataFormat4 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG);
        dataFormat1.setCalendar(calendar);
        dataFormat2.setCalendar(calendar);
        dataFormat3.setCalendar(calendar);
        dataFormat4.setCalendar(calendar);
        System.out.println(dataFormat1.format(calendar.getTime()));
        System.out.println(dataFormat2.format(calendar.getTime()));
        System.out.println(dataFormat3.format(calendar.getTime()));
        System.out.println(dataFormat4.format(calendar.getTime()));


    }
}
