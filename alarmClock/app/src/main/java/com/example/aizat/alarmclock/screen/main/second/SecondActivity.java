package com.example.aizat.alarmclock.screen.main.second;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.example.aizat.alarmclock.R;
import com.example.aizat.alarmclock.model.entity.AlarmItem;
import com.example.aizat.alarmclock.screen.base.BaseActivity;

/**
 * Created by Aizat on 21.10.2017.
 */

public class SecondActivity extends BaseActivity {

    private final String ALARM_ITEM_REQUEST = "fsfasad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private Bundle getData() {
        Intent intent = getIntent();
        Bundle dataForFragment = new Bundle();
        dataForFragment.putParcelable(ALARM_ITEM_REQUEST,intent.getParcelableExtra(ALARM_ITEM_REQUEST));
        return dataForFragment;
    }

    @NonNull
    @Override
    protected Fragment makeFragment() {
        return SecondFragment.newInstance(getData());
    }
}
