package cc.qianglovepei.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Created by chutao on 16-4-22.
 */
public class DateUtil {

    /***
     * 将d转换成中国日期格式,如2016-1-22转成2016年1月22日
     *
     * @param d
     * @return
     */
    public static String getChineseDateStr(Date d) {
        Objects.requireNonNull(d);
        DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(d);
    }

    /***
     * 将日期字符串转换为java.util.Date类型
     *
     * @param dateStr
     * @param formatStr
     * @return
     * @throws ParseException
     */
    public static Date dateStrParse(String dateStr, String formatStr) {
        try {
            DateFormat format = new SimpleDateFormat(formatStr);
            return format.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date todayDate() {

        return toDate(new Date());
    }

    public static Date toDate(Date dateTime) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static String convertDateToStr(Date d, String formatStr) {
        Objects.requireNonNull(d);
        Objects.requireNonNull(formatStr);
        DateFormat format = new SimpleDateFormat(formatStr);
        return format.format(d);
    }


    public static Date clearTimeInfo(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date addDay(Date d, int dayNum) {
        Objects.requireNonNull(d);
        Calendar cld = Calendar.getInstance();
        cld.setTime(d);
        cld.add(Calendar.DATE, dayNum);
        return cld.getTime();
    }

    public static Date addMinute(Date d, int minute) {
        Objects.requireNonNull(d);
        Calendar cld = Calendar.getInstance();
        cld.setTime(d);
        cld.add(Calendar.MINUTE, minute);
        return cld.getTime();
    }
}
