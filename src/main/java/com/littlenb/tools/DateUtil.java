package com.littlenb.tools;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间格式化工具类
 *
 * @author svili
 */
public class DateUtil {

    /**
     * 默认的时间格式。如：2018-01-01 10:20:30
     */
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 简单的时间格式，如:2018-01-01
     */
    public static final String SIMPLE_PATTERN = "yyyy-MM-dd";


    /* 默认格式化：yyyy-MM-dd HH:mm:ss */
    public static String format(Date date) {
        return format(date, DEFAULT_PATTERN);
    }

    /* 普通格式化：yyyy-MM-dd */
    public static String formatSimple(Date date) {
        return format(date, SIMPLE_PATTERN);
    }


    /* 自定义格式化：pattern */
    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date.getTime(), pattern);
    }

    /* 默认格式化：yyyy-MM-dd HH:mm:ss */
    public static Date parse(String date) {
        return parse(date, DEFAULT_PATTERN);
    }

    /* 普通格式化：yyyy-MM-dd */
    public static Date parseSimple(String date) {
        return parse(date, SIMPLE_PATTERN);
    }

    /* 自定义格式化：pattern */
    public static Date parse(String date, String pattern) {
        try {
            return DateUtils.parseDateStrictly(date, new String[]{pattern});
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /* 年份 */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /* 月份 */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /* 月份第一天 */
    public static String getFirstDay(Date month) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(month);
        // 获取当月的第一天
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return formatSimple(cale.getTime());
    }

    /* 月份最后一天 */
    public static String getLastDay(Date month) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(month);
        // 获取当月的最后一天 即,下个月月初的前一天
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return formatSimple(cale.getTime());
    }

    /* 星期 */
    public static int getWeek(Date day) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(day);
        // 在美国 星期日是一个星期的第一天
        if (cale.get(Calendar.DAY_OF_WEEK) == 1) {
            return 7;
        }
        return cale.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /* 获取日期 */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }

}