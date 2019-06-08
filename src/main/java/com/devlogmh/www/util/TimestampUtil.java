package com.devlogmh.www.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * タイムスタンプ共通クラス
 */
public class TimestampUtil {

    /**
     * Timestamp型をString型に変換して返します.
     * @param timestamp
     * @param timeFormat
     * @return String型の時間
     */
    public static String formattedTimestamp(Timestamp timestamp, String timeFormat) {
        return new SimpleDateFormat(timeFormat).format(timestamp);
    }

    /**
     * String型からTimestamp型に変換して返します。
     * @param timestamp
     * @return yyyy/mm/dd hh:mm
     */
    public static Timestamp formattedStringTime(String timestamp) {
        Timestamp formatTime = null;
        try {
            formatTime = new Timestamp(new SimpleDateFormat(Contains.TIME_FORMAT).parse(timestamp).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatTime;
    }

    /**
     * 現在時刻を返します。
     * @return
     */
    public static Timestamp currentTime() {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        return timestamp;

    }

}
