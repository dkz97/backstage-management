package dkz97.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 日期转字符串,需要传入日期，以及想变成什么格式
     */
    public static String dataToString(Date date,String pattern) {
        // 创建SimpleDateFormat对象，创建的时候传入想要的格式规则
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        // 然后使用format 传入时间，把时间转换成想要的格式
        String dateStr = dateFormat.format(date);
        return dateStr;
    }

    /**
     * 字符串转换为日期
     */
    public static Date stringToDate(String dateStr,String pattern) throws ParseException {
        // 也是创建SimpleDateFormat对象，然后传入格式的规则
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        // 然后使用parse方法转为时间
        Date date = dateFormat.parse(dateStr);
        return date;
    }
}
