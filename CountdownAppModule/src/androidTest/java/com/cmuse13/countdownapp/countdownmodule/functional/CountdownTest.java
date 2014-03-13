package com.cmuse13.countdownapp.countdownmodule.functional;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.cmuse13.countdownapp.countdownmodule.R;
import com.cmuse13.countdownapp.countdownmodule.activities.MainActivity;

import static android.test.ViewAsserts.assertOnScreen;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;

public class CountdownTest
        extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mActivity;

    @SuppressWarnings("deprecation")
    public CountdownTest() {
        super("com.cmuse13.countdownapp.countdownmodule", MainActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();

        mActivity = getActivity();
    }

    public void testLabel() {
        onView(withId(R.id.days_to_go_label)).check(matches(withText("Days to go")));
    }

    public void testDaysToGoViewExists() {
        TextView daysToGoView = (TextView) getActivity().findViewById(R.id.days_to_go);
        assertOnScreen(mActivity.getWindow().getDecorView(), daysToGoView);
    }
}