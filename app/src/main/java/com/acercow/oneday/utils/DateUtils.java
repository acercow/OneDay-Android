package com.acercow.oneday.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhaosen on 2018/2/27.
 */

public class DateUtils {
    public static String getFormatDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static long getTimeStamp() {
        return new Date().getTime();
    }

}
