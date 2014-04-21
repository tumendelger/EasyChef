/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data.utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author tumee
 */
public final class DateTimeUtils {

    private Date now;
    private Date startTime;
    private SimpleDateFormat formatter;

    public DateTimeUtils() {
    }

    /**
     * Extract time part from date time string
     *
     * @param dateTime
     * @return time part in string
     * @throws java.text.ParseException
     */
    public String getTimeFormatted(String dateTime) throws ParseException {
        formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.parse(dateTime).toString();
    }

    public String getTimeFormatted(Time dateTime) throws ParseException {
        formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(dateTime);
    }

    /**
     * Simple method to calculate current time for required format
     *
     * @param format
     * @param required time format in String e.g yy-mm-dd hh:MM:ss.sss
     * @return current time in required format
     */
    public String getCurrentTime(String format) {
        this.formatter = new SimpleDateFormat(format);
        now = new Date(System.currentTimeMillis());
        return formatter.format(now);
    }

    /**
     * Calculate time difference from given time to current time
     *
     * @param Time
     * @param startTime
     * @return difference in minutes
     * @throws java.text.ParseException
     */
    public int getTimeDifferenceInMinutes(String Time) throws ParseException {
        formatter = new SimpleDateFormat("HH:mm:ss");
        now = new Date(System.currentTimeMillis());
        String timeNow = formatter.format(now);

        this.startTime = formatter.parse(Time);
        now = formatter.parse(timeNow);

        long diff = now.getTime() - startTime.getTime();

        return (int) TimeUnit.MILLISECONDS.toMinutes(diff);
    }
}
