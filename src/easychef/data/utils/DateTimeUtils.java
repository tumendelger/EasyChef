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
import java.util.logging.Logger;

/**
 * Reusable class useful for converting times and dates finding difference
 * between dates and times
 *
 * @author tumee
 */
public final class DateTimeUtils {

    private Date now;
    private Date startTime;
    private SimpleDateFormat formatter;
    private final static Logger logger = Logger.getLogger(DateTimeUtils.class.getName());

    public DateTimeUtils() {
    }

    /**
     * Extract time part from date time string
     *
     * @param dateTime String form of the date in supported formats
     * @return String Time part of the date as "HH:mm:ss" format
     * @throws java.text.ParseException
     */
    public String getTimeFormatted(String dateTime) throws ParseException {
        formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.parse(dateTime).toString();
    }

    /**
     * Returns "HH:mm:ss" formatted string of given Time object
     *
     * @param dateTime java.sql.Time object
     * @return String
     * @throws ParseException
     */
    public String getTimeFormatted(Time dateTime) throws ParseException {
        formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(dateTime);
    }

    /**
     * Returns formatted String of current time Pass supported format of the
     * date time object and convert current time
     *
     * @param format String
     * @return String representation of the current time in given format
     */
    public String getCurrentTime(String format) {
        this.formatter = new SimpleDateFormat(format);
        now = new Date(System.currentTimeMillis());
        return formatter.format(now);
    }

    /**
     * Calculate time difference from given time to current time
     *
     * @param time String formatted DateTime
     * @return int difference in minutes current time to given time
     * @throws java.text.ParseException
     */
    public int getTimeDifferenceInMinutes(String time) throws ParseException {

        formatter = new SimpleDateFormat("HH:mm:ss");
        now = new Date(System.currentTimeMillis());
        String timeNow = formatter.format(now);

        this.startTime = formatter.parse(time);
        now = formatter.parse(timeNow);

        long diff = now.getTime() - startTime.getTime();

        logger.info(String.format("Calculate difference in minutes between %s & %s is: %d", timeNow, time, TimeUnit.MILLISECONDS.toMinutes(diff)));

        return (int) TimeUnit.MILLISECONDS.toMinutes(diff);
    }
}
