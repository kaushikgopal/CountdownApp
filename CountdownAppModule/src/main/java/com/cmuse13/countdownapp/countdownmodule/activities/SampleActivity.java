package com.cmuse13.countdownapp.countdownmodule.activities;

import android.os.Bundle;

import com.cmuse13.countdownapp.countdownmodule.R;
import com.cmuse13.countdownapp.countdownmodule.pojos.SamplePojo;

public class SampleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
    }

    private void someMethodToIncludeAPojo() {
        SamplePojo tester = new SamplePojo();
        tester.someMethod();
    }
}
