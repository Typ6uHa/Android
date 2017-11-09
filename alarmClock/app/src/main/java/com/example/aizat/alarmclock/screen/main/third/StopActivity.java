package com.example.aizat.alarmclock.screen.main.third;

import android.app.Fragment;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.aizat.alarmclock.screen.base.BaseActivity;

/**
 * Created by Aizat on 09.11.2017.
 */

public class StopActivity extends BaseActivity {

    @NonNull
    @Override
    protected Fragment makeFragment() {
        return StopFragment.newInstance();
    }
}
