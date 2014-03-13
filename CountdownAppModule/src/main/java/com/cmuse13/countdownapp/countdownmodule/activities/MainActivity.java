package com.cmuse13.countdownapp.countdownmodule.activities;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.cmuse13.countdownapp.countdownmodule.R;
import com.cmuse13.countdownapp.countdownmodule.utils.CountdownHelper;

import org.joda.time.DateTime;

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
        final DateTime quarterEnd = new DateTime(2014, 4, 1, 0, 0);
        final Handler mHandler = new Handler();

        new Timer().scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                // do something
                mHandler.post(new Runnable(){
                    @Override
                    public void run(){
                        // test: funtional test

                        mDaysToGo.setText(CountdownHelper.getDaysToGo(new DateTime(), quarterEnd));
                    }
                });

            }
        }, 0, 1000);
    }
}
