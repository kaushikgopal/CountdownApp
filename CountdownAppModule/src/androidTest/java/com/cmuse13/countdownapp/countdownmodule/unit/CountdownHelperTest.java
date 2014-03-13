package com.cmuse13.countdownapp.countdownmodule.unit;

import android.test.AndroidTestCase;

import com.cmuse13.countdownapp.countdownmodule.utils.CountdownHelper;

import org.joda.time.DateTime;

import static org.fest.assertions.api.Assertions.assertThat;


public class CountdownHelperTest extends AndroidTestCase {

    public void testGetDaysToGo_ForYears() {
        DateTime fromDate = new DateTime(2014, 1, 1, 0, 0);
        DateTime toDate = new DateTime(2015, 1, 1, 0, 0);

        assertThat(CountdownHelper.getDaysToGo(fromDate, toDate)).isEqualTo("365");
    }

    public void testGetDaysToGo_ForMonths() {
        DateTime fromDate = new DateTime(2014, 1, 1, 0, 0);
        DateTime toDate = new DateTime(2014, 2, 1, 0, 0);

        assertThat(CountdownHelper.getDaysToGo(fromDate, toDate)).isEqualTo("31");
    }

    public void testGetDaysToGo_ForDays() {
        DateTime fromDate = new DateTime(2014, 2, 1, 0, 0);
        DateTime toDate = new DateTime(2014, 2, 10, 0, 0);

        assertThat(CountdownHelper.getDaysToGo(fromDate, toDate)).isEqualTo("9");
    }


    public void testGetDaysToGo_ForIncorrectFromToDates() {
        DateTime fromDate = new DateTime(2014, 2, 13, 0, 0);
        DateTime toDate = new DateTime(2014, 2, 10, 0, 0);

        assertThat(CountdownHelper.getDaysToGo(fromDate, toDate)).isEqualTo("0");
    }
}
