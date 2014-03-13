package com.cmuse13.countdownapp.countdownmodule.activities;

import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmuse13.countdownapp.countdownmodule.R;
import com.cmuse13.countdownapp.countdownmodule.utils.CountdownHelper;

import org.joda.time.DateTime;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity
        extends BaseActivity {

    private TextView mDaysToGo;


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
        final int periodLength = 90;

        new Timer().scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                // do something
                mHandler.post(new Runnable(){
                    @Override
                    public void run(){
                        int daysRemaining = CountdownHelper.getDaysToGo(new DateTime(), quarterEnd);
                        mDaysToGo.setText(String.valueOf(daysRemaining));
                        changeBackgroundColor(daysRemaining, periodLength);
                    }
                });

            }
        }, 0, 1000);
    }

    private void changeBackgroundColor(int daysRemaining, int periodLength) {
        CountdownHelper.COLOR color = CountdownHelper.getColorForDaysToGo(daysRemaining,
                                                                          periodLength);
        int finalColor = 0;

        switch (color) {
            case GREEN:
                finalColor = getResources().getColor(R.color.green);
            case YELLOW:
                finalColor = getResources().getColor(R.color.yellow);
            case RED:
                finalColor = getResources().getColor(R.color.red);
        }

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        mainLayout.setBackgroundColor(finalColor);
    }
}
