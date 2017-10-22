package com.example.aizat.alarmclock.screen.main.first;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.example.aizat.alarmclock.R;
import com.example.aizat.alarmclock.model.entity.AlarmItem;
import com.example.aizat.alarmclock.screen.base.BaseActivity;
import com.example.aizat.alarmclock.screen.main.second.SecondActivity;

public class MainActivity extends BaseActivity {

    private final static String KEY = "key";

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_bar);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @NonNull
    protected MainFragment makeFragment() {
        return MainFragment.newInstance();
    }
}
