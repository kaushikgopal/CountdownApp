package com.cmuse13.countdownapp.countdownmodule.activities;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.cmuse13.countdownapp.countdownmodule.R;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity
        extends BaseActivity {

    private TextView mDaysToGo;
    private int tmp = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        startCountdown();
    }

    private void startCountdown() {

        mDaysToGo = (TextView) findViewById(R.id.days_to_go);

        final Handler mHandler = new Handler();


        new Timer().scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                // do something
                mHandler.post(new Runnable(){
                    @Override
                    public void run(){
                        mDaysToGo.setText(getDaysToGo());
                    }
                });

            }
        }, 0, 1000);
    }

    private String getDaysToGo() {
        DateTime today = new DateTime();
        DateTime quarterEnd = new DateTime(2014, 4, 1, 0, 0);

        return String.valueOf(Days.daysBetween(today, quarterEnd).getDays());
    }

}
