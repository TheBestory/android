package com.thebestory.android.util;

import android.util.Log;

import com.thebestory.android.model.Story;


import org.joda.time.Period;

import java.util.Date;
import java.util.Locale;

import humanize.Humanize;
import humanize.time.TimeMillis;

/**
 * Created by Alex on 18.12.2016.
 */

public class TimeConverter {

    public static String absoluteTime (Story story) {
        if (story == null || story.publishDate == null) {
            return "null";
        }
        return Humanize.format("{0, joda.time, full.date.time}", story.publishDate, Locale.US);
    }

    public static String relativeTime(Story story, Date nowDate) {
        if (story == null || story.publishDate == null || nowDate == null) {
            return "null";
        }
        Period period = new Period(story.publishDate.getTime(), nowDate.getTime());
        /*
        PLEASE DONT DELETE THIS. IT MAKE ME CRY :C
        if (period.getYears() < 1) {
            if (period.getMonths() < 1){
                if (period.getWeeks() < 1){
                    if (period.getDays() < 1) {
                        if (period.getHours() < 1) {
                            if (period.getMinutes() < 1) {
                                return "Several seconds ago";
                            }

                            if (period.getMinutes() == 1) {
                                return "Minute ago";
                            }
                            return Integer.toString(period.getMinutes()) + " minutes ago";
                        }

                        if (period.getHours() == 1) {
                            return "Hour ago";
                        }

                        return Integer.toString(period.getHours()) + " hours ago";
                    }

                    if (period.getDays() == 1) {
                        return "Yesterday";
                    }

                    return Integer.toString(period.getDays()) + " days ago";
                }

                if (period.getWeeks() == 1) {
                    return "Weak ago";
                }

                return Integer.toString(period.getWeeks()) + " weaks ago";
            }

            if (period.getMonths() == 1) {
                return "Month ago";
            }

            return Integer.toString(period.getMonths()) + " months ago";
        }

        if (period.getYears() == 1) {
            return "Year ago";
        }

        return Integer.toString(period.getYears()) + " years ago";*/
        TimeMillis timeMillis;
        if (period.getMonths() == 0) {

            if (period.getWeeks() == 0) {

                if (period.getDays() == 0) {

                    if (period.getHours() == 0) {
                        if (period.getMinutes() == 0) {
                            return "just now";
                        } else if (period.getMinutes() == 1) {
                            return "moments ago";
                        } else if (period.getMinutes() < 5) {
                            return period.getMinutes() + " minutes ago";
                        } else {
                            timeMillis = TimeMillis.MINUTE;
                        }
                    } else {
                        timeMillis = TimeMillis.HOUR;
                    }
                } else {
                    timeMillis = TimeMillis.DAY;
                }
            } else {
                timeMillis = TimeMillis.WEEK;
            }
        } else {
            timeMillis = TimeMillis.MONTH;
        }
        return Humanize.naturalTime(nowDate, story.publishDate, timeMillis, Locale.US);
    }
}