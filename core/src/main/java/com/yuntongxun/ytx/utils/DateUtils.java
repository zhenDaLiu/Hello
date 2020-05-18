package com.yuntongxun.ytx.utils;

import cn.hutool.core.date.BetweenFormater;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具类
 * @author tangxy
 * @date 2019-03-27
 **/
public class DateUtils {
    /**
     * 一分钟60秒
     */
    private static final int SECONDS = 60;

    /**
     * 格式化等级枚举
     *
     * @author Looly
     */
    public static enum Level {

        /** 天 */
        DAY("天"),
        /** 小时 */
        HOUR("时"),
        /** 分钟 */
        MINUTE("分"),
        /** 秒 */
        SECOND("秒"),
        /** 毫秒 */
        MILLSECOND("毫秒");

        /** 级别名称 */
        private String name;

        /**
         * 构造
         * @param name 级别名称
         */
        private Level(String name) {
            this.name = name;
        }

        /**
         * 获取级别名称
         * @return 级别名称
         */
        public String getName() {
            return this.name;
        }
    }

    /**
     * 转换为utc日期格式
     * @param dateStr
     * @return
     */
    public static String toUtcDate(String dateStr){
        Date dateObject = DateUtil.parse(dateStr);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        return df.format(dateObject) ;
    }

    /**
     * 转换秒为时分秒
     * @param seconds
     * @return
     */
    public static String formatSecond(int seconds){
        StringBuilder result = new StringBuilder();
        // 小时
        int hour = 0;
        // 分钟
        int middle = 0;
        // 秒
        int second = 0;

        if(seconds > SECONDS){
            middle = seconds/SECONDS;
            second = seconds % SECONDS;
            if(middle > SECONDS){
                hour = middle / SECONDS;
                middle = middle % SECONDS;
            }
        }else{
            second = seconds;
        }
        if(hour > 0){
            result.append(hour).append("h ");
        }
        if(middle > 0){
            result.append(middle).append("min ");
        }
        if(second > 0){
            result.append(second).append("s");
        }
        return result.toString();
    }

    /**
     * 转换秒时间戳为yyyy-MM-dd HH:mi:ss
     * @param unixTime
     * @return
     */
    public static String formatUnixTime(long unixTime){
        return DateUtil.date(unixTime * 1000).toString(DatePattern.NORM_DATETIME_PATTERN);
    }

    /**
     * 转换秒为时分秒
     * @param seconds
     * @return
     */
    public static String getFormatTimeStr(int seconds){
        Long secL = Long.valueOf(seconds) * 1000;
        return formatTime(secL, BetweenFormater.Level.SECOND,Boolean.TRUE);
    }

    /**
     * 转换秒为时分秒 - 参数为long
     * @param seconds
     * @return
     */
    public static String getFormatTimeStr(Long seconds){
        Long secL = seconds * 1000;
        return formatTime(secL, BetweenFormater.Level.SECOND,Boolean.TRUE);
    }

    /**
     * 格式换化毫秒为时分秒类型
     * @param betweenMs
     * @param levelType
     * @param isShowZeroHour 是否显示为零的小时
     * @return
     */
    public static String formatTime(long betweenMs,BetweenFormater.Level levelType,Boolean isShowZeroHour){
        StringBuilder sb = new StringBuilder();

        if(betweenMs > 0){
            int level = levelType.ordinal();
            long day = betweenMs / DateUnit.DAY.getMillis();
            long hour = betweenMs / DateUnit.HOUR.getMillis() - day * 24;
            long minute = betweenMs / DateUnit.MINUTE.getMillis() - day * 24 * 60 - hour * 60;
            long second = betweenMs / DateUnit.SECOND.getMillis() - ((day * 24 + hour) * 60 + minute) * 60;
            long millisecond = betweenMs - (((day * 24 + hour) * 60 + minute) * 60 + second) * 1000;

            if( 0 != day && level >= Level.DAY.ordinal()){
                sb.append(day).append(Level.DAY.getName());
            }
            if( -1 < hour && level >= Level.HOUR.ordinal()){
                if(isShowZeroHour){
                    sb.append(hour).append(Level.HOUR.getName());
                }
            }
            if( 0 != minute && level >= Level.MINUTE.ordinal()){
                sb.append(minute).append(Level.MINUTE.getName());
            }
            if( 0 != second && level >= Level.SECOND.ordinal()){
                sb.append(second).append(Level.SECOND.getName());
            }
            if( 0 != millisecond && level >= Level.MILLSECOND.ordinal()){
                sb.append(millisecond).append(Level.MILLSECOND.getName());
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getFormatTimeStr(4033345));
    }

}
