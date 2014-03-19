package com.cmuse13.countdownapp.countdownmodule.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmuse13.countdownapp.countdownmodule.R;

public class SettingsDialogFragment extends DialogFragment {

    protected Dialog mDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDialog = super.onCreateDialog(savedInstanceState);
        return mDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_settings, container, true);
    }

    @Override
    public void onStart() {
        super.onStart();

//        mDialog.getWindow().setWindowAnimations(R.style.wp_dialog_animation);
        mDialog.getWindow()
               .setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


}
