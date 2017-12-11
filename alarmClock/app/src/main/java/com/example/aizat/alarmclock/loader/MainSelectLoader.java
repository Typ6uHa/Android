package com.example.aizat.alarmclock.loader;

import android.content.Context;
import android.database.Cursor;

import com.example.aizat.alarmclock.model.database.DatabaseHelper;
import com.example.aizat.alarmclock.model.database.SQLiteCursorLoader;

public class MainSelectLoader extends SQLiteCursorLoader {

    private DatabaseHelper databaseHelper;

    public MainSelectLoader(Context context, DatabaseHelper databaseHelper) {
        super(context);
        this.databaseHelper = databaseHelper;
    }

    @Override
    protected Cursor loadCursor() {
        return databaseHelper.selectAlarmItemsCursor();
    }
}