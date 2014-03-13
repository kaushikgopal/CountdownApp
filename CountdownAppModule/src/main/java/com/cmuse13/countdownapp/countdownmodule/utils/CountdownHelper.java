package com.cmuse13.countdownapp.countdownmodule.utils;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class CountdownHelper {

    public enum COLOR {
        RED, GREEN, YELLOW
    }

    public static int getDaysToGo(DateTime fromDate, DateTime toDate){

        int daysToGo = Days.daysBetween(fromDate, toDate).getDays();

        if (daysToGo < 0)
            return 0;

        return daysToGo;
    }

    public static COLOR getColorForDaysToGo(int daysRemaining, int periodLength) {

        float periodCovered = daysRemaining / ((float) periodLength);

        if (periodCovered >= 0 && periodCovered < 0.33)
            return COLOR.RED;

        if (periodCovered >= 0.33 && periodCovered < 0.66)
            return COLOR.YELLOW;

        return COLOR.GREEN;
    }
}
