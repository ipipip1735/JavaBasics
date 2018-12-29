import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Administrator on 2018/12/28.
 */

public class DateFormatTrial {
    public static void main(String[] args) {
        DateFormatTrial dateFormatTrial = new DateFormatTrial();
        dateFormatTrial.format();
//        dateFormatTrial.parse();
    }

    private void format() {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+07:00");
        Date date = new Date(System.currentTimeMillis());

//        DateFormat dataFormat = DateFormat.getInstance();
        DateFormat dataFormat = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.LONG, Locale.ENGLISH);
        dataFormat.setTimeZone(timeZone);
        String r = dataFormat.format(date);
        System.out.println(r);
    }


    private void parse() {

        try {

            TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
            DateFormat dataFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
            dataFormat.setTimeZone(timeZone);

            String source = "2018年12月29日星期六 中国标准时间 上午6:25:16";
            Date date = dataFormat.parse(source);
            System.out.println(date);



        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
