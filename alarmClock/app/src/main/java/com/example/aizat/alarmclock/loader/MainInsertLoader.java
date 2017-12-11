package com.example.aizat.alarmclock.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.aizat.alarmclock.model.database.DatabaseHelper;
import com.example.aizat.alarmclock.model.entity.AlarmItem;

public  class MainInsertLoader extends AsyncTaskLoader {

    private DatabaseHelper databaseHelper;

    private AlarmItem alarmItem;

    public MainInsertLoader(Context context, DatabaseHelper databaseHelper, AlarmItem alarmItem) {

        super(context);
        this.databaseHelper = databaseHelper;
        this.alarmItem = alarmItem;
    }

    @Override
    public Object loadInBackground() {
        databaseHelper.insertAlarmItem(alarmItem);
        return null;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}