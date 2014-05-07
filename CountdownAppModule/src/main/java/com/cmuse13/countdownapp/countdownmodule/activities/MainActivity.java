package com.cmuse13.countdownapp.countdownmodule.activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmuse13.countdownapp.countdownmodule.R;
import com.cmuse13.countdownapp.countdownmodule.fragments.BaseFragment;
import com.cmuse13.countdownapp.countdownmodule.fragments.SettingsDialogFragment;
import com.cmuse13.countdownapp.countdownmodule.utils.CountdownHelper;

import org.joda.time.DateTime;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity
        extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                openSettingsDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSettingsDialog() {
        SettingsDialogFragment fragment = new SettingsDialogFragment();
        fragment.show(getFragmentManager(), "settingsDialogFragment");
    }

    public static class MainFragment
            extends BaseFragment {

        private TextView mDaysToGo;

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_main, container, true);
        }

        @Override
        public void onResume() {
            super.onResume();
            startCountdown();
        }

        private void startCountdown() {
            mDaysToGo = (TextView) getView().findViewById(R.id.days_to_go);
            final DateTime today = new DateTime();
            final int quarterEndMonth = (((((today.getMonthOfYear() - 1)) / 3 + 1) * 3) + 1) % 12;
            final DateTime quarterEnd = new DateTime(2014, quarterEndMonth, 1, 0, 0);
            final Handler mHandler = new Handler();
            final int periodLength = 90;

            new Timer().scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {
                    // do something
                    mHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            int daysRemaining = CountdownHelper.getDaysToGo(today,
                                                                            quarterEnd);
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
                    break;
                case YELLOW:
                    finalColor = getResources().getColor(R.color.yellow);
                    break;
                case RED:
                    finalColor = getResources().getColor(R.color.red);
                    break;
            }

            getView().setBackgroundColor(finalColor);
        }
    }

}
