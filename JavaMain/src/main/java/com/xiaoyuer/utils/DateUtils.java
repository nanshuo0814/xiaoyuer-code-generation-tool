package com.xiaoyuer.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMMSS = "yyyy/MM/dd HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDD = "yyyy/MM/dd";

    public static String format(Date date, String patten) {
        return new SimpleDateFormat(patten).format(date);
    }

    public Date parse(String date, String patten) {
        try {
            new SimpleDateFormat(patten).format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
