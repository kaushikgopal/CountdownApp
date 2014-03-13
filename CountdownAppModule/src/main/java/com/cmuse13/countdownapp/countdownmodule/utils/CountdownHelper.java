package com.cmuse13.countdownapp.countdownmodule.utils;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class CountdownHelper {

    public static String getDaysToGo(DateTime fromDate, DateTime toDate){

        int daysToGo = Days.daysBetween(fromDate, toDate).getDays();

        if (daysToGo < 0)
            return "0";

        return String.valueOf(daysToGo);
    }
}
