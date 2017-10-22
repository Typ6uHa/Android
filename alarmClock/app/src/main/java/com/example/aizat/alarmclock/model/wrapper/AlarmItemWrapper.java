package com.example.aizat.alarmclock.model.wrapper;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.aizat.alarmclock.model.entity.AlarmItem;
import com.example.aizat.alarmclock.model.table.AlarmItemTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aizat on 21.10.2017.
 */

public class AlarmItemWrapper extends CursorWrapper {

    public AlarmItemWrapper(Cursor cursor) {
        super(cursor);
    }
    @NonNull
    public List<AlarmItem> getAlarmItems(){
        List<AlarmItem> alarmItems = new ArrayList<>();
        moveToFirst();
        while (!isBeforeFirst() && !isAfterLast()){
            alarmItems.add(getAlarmItem());
            moveToNext();
        }
        return alarmItems;
    }

    @Nullable
    private AlarmItem getAlarmItem(){
        if(!isBeforeFirst() && !isAfterLast()) {
            AlarmItem alarmItem = new AlarmItem();
            alarmItem.setTime(getString(getColumnIndex(AlarmItemTable.COLUMN_TIME)));
            alarmItem.setDescription(getString(getColumnIndex(AlarmItemTable.COLUMN_DESCRIPTION)));
            alarmItem.setSwitchedOn(getInt(getColumnIndex(AlarmItemTable.COLUMN_SWITCH)));
        }
        return null;
    }
}
