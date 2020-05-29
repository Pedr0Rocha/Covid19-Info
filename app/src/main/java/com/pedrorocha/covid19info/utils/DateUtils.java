package com.pedrorocha.covid19info.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

public class DateUtils {

    @Inject
    public DateUtils() {

    }

    public String formatToAPIParameters(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public Date getYesterday() {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public Date getDaysBefore(int days) {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DATE, -days);
        return calendar.getTime();
    }

    public Date setToStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    public Date setToEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }

}
