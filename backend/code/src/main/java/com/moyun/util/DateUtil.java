package com.moyun.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * 格式化日期时间
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(FORMATTER);
    }
    
    /**
     * 解析日期时间字符串
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.trim().isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(dateTimeStr, FORMATTER);
    }
    
    /**
     * 获取当前时间
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
    
    /**
     * 计算两个时间之间的天数差
     */
    public static long daysBetween(LocalDateTime start, LocalDateTime end) {
        return java.time.Duration.between(start, end).toDays();
    }
    
    /**
     * 计算两个时间之间的小时差
     */
    public static long hoursBetween(LocalDateTime start, LocalDateTime end) {
        return java.time.Duration.between(start, end).toHours();
    }
    
    /**
     * 计算两个时间之间的分钟差
     */
    public static long minutesBetween(LocalDateTime start, LocalDateTime end) {
        return java.time.Duration.between(start, end).toMinutes();
    }
} 