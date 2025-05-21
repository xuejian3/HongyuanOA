package com.mario.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class LocalDateTimeUtils {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static final String NO_SEP_DATE_FORMAT = "YYYYMMdd";

    public static final String NO_SEP_DATETIME_FORMAT = "yyyyMMddHHmmss";

    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 字符串转LocalDate
     * @param dateStr
     * @return
     */
    public static Date str2LocalDate(String dateStr){
        if (""== dateStr)
    {
        return null;
    }

        return Date.from(str2LocalDate(dateStr, DEFAULT_DATE_FORMAT)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * 字符串转LocalDateTime
     * @param dateStr
     * @return
     */
    public static LocalDateTime str2LocalDateTime(String dateStr){
        return str2LocalDateTime(dateStr, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 字符串转LocalDate
     * @param dateStr
     * @return
     */
    public static LocalDate str2LocalDate(String dateStr, String pattern){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(dateStr, fmt);
    }

    /**
     * 字符串转LocalDate  例 11-Nov-1996
     * LocalDateTimeUtils.str2LocalDate(bvnVerifyDO.getDateOfBirth(),"dd-MMM-uuuu", Locale.ENGLISH)
     * @param dateStr
     * @param pattern
     * @param locale
     * @return
     */
    public static LocalDate str2LocalDate(String dateStr, String pattern, Locale locale){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern, locale);
        return LocalDate.parse(dateStr, fmt);
    }

    /**
     * 字符串转LocalDateTime
     * @param dateStr
     * @return
     */
    public static LocalDateTime str2LocalDateTime(String dateStr, String pattern){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateStr, fmt);
    }

    /**
     * Date日期转换为字符串日期
     * @param date
     * */
    public static String date2Str(Date date){
        return date2Str(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * Date日期转换为字符串日期
     * @param date
     * @param pattern 需转换的日期格式
     * */
    public static String date2Str(Date date, String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        Instant instant = date.toInstant();
        LocalDateTime ldt = instant
                .atZone(ZoneId.of("CET"))
                .toLocalDateTime();
        return  ldt.format(formatter);
    }

    /**
     * Date转LocalDateTime
     * */
    public static LocalDateTime date2LDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Date转LocalDate
     * */
    public static LocalDate date2LD(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * LocalDate转Date
     * */
    public static Date LD2Date(LocalDate localDate){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * current LocalDateTime to String
     * @param pattern
     * */
    public static String LDT2String(LocalDateTime localDateTime, String pattern){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return df.format(localDateTime);
    }

    /**
     * LocalDateTime to String
     * @param pattern
     * @param localDate
     * */
    public static String LD2String(LocalDate localDate, String pattern){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return df.format(localDate);
    }

    /**
     * 获取两个日期的差
     * @param startTime
     * @param endTime
     * @param unit 单位
     * */
    public static long getGapDay(LocalDate startTime, LocalDate endTime, ChronoUnit unit) {
        Period period = Period.between(startTime, endTime);
        if (unit == ChronoUnit.YEARS) {
            return period.getYears();
        }
        if (unit == ChronoUnit.MONTHS) {
            return period.getYears() * 12 + period.getMonths();
        }
        return unit.between(startTime, endTime);
    }

    /**
     * 获取localDate零点的时间
     * @param localDate
     * */
    public static String getTodayStartTime(LocalDate localDate){
        LocalDateTime todayStart = LocalDateTime.of(localDate, LocalTime.MIN);
        return todayStart.format(DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT));
    }

    /**
     * 时间戳转LocalDateTime
     * */
    public static LocalDateTime timestamp2LocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * LocalDateTime转时间戳
     * */
    public static long localDateTime2Timestamp(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

}