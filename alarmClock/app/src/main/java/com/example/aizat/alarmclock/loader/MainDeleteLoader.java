package com.example.aizat.alarmclock.loader;

/**
 * Created by Aizat on 20.11.2017.
 */

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.aizat.alarmclock.model.database.DatabaseHelper;


public class MainDeleteLoader extends AsyncTaskLoader {

    private DatabaseHelper databaseHelper;


    public MainDeleteLoader(Context context, DatabaseHelper databaseHelper) {
        super(context);
        this.databaseHelper = databaseHelper;
    }

    @Override
    public Object loadInBackground() {
        databaseHelper.clearAlarmItems();
        return null;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
