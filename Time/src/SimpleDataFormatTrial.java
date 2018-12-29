import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * Created by Administrator on 2018/12/29 13:16.
 */

public class SimpleDataFormatTrial {
    public static void main(String[] args) {

        SimpleDataFormatTrial simpleDataFormatTrial = new SimpleDataFormatTrial();

        simpleDataFormatTrial.parse();
//        simpleDataFormatTrial.format();

    }

    private void format() {
        Date date = new Date(System.currentTimeMillis());
        String pattern = "y/M/d H:m:s XXX";

//        TimeZone timeZone = TimeZone.getTimeZone("GMT+7");
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        simpleDateFormat.setTimeZone(timeZone);
        String r = simpleDateFormat.format(date);

        System.out.println(r);
    }

    private void parse() {

        try {
            //以周为单位
//            String source = "2002-w27-3";
//            String pattern = "yy-'w'w-u";

            //GTM
//            String source = "2018/12/29 14:28:34 GMT+07:00";
//            String pattern = "y/m/d H:m:s z";

            //RFC822
//            String source = "2018/12/29 14:28:34 +0700";
//            String pattern = "y/m/d H:m:s z";

            //ISO8601
//            String source = "2018/12/29 14:28:34 Z"; //0时区
//            String source = "2018/12/29 14:28:34 +08"; //两位
//            String source = "2018/12/29 14:28:34 -0800"; //4位
            String source = "2018/12/29 14:28:34 +08:00"; //4位
            String pattern = "y/m/d H:m:s X";



            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date date = simpleDateFormat.parse(source);

            System.out.println(date);


        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
