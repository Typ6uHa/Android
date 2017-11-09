package com.example.aizat.alarmclock.model.table;

import android.support.annotation.NonNull;

import com.example.aizat.alarmclock.model.entity.AlarmItem;

/**
 * Created by Aizat on 21.10.2017.
 */

public class AlarmItemTable {
    public static final String NAME = "persons";

    public static final String COLUMN_ID = "_id";

    public static final String COLUMN_TIME = "time";

    public static final String COLUMN_DESCRIPTION = "description";

    public static final String COLUMN_SWITCH = "switch";


    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_TIME + " TEXT NOT NULL , "
                + COLUMN_DESCRIPTION + " TEXT NOT NULL, "
                + COLUMN_SWITCH + " BLOB"
                + ");";
    }

    @NonNull
    public static String getSelectQuery(){
        return "SELECT * FROM " + NAME + ";";
    }

    @NonNull
    public static String getInsertQuery(AlarmItem alarmItem){
        return "INSERT INTO " + NAME+ "("+ COLUMN_TIME +","+ COLUMN_DESCRIPTION+ "," + COLUMN_SWITCH + "," + COLUMN_ID + ") VALUES("
                +"\'" + alarmItem.getTime() + "\',"
                + alarmItem.getDescription() + ","
                +"\'" + alarmItem.isSwitchedOn() + "\'" + ", "
                + alarmItem.getId()
                + ");";
    }

    @NonNull
    public static String updateAlarm(AlarmItem alarmItem){
        return "UPDATE " + NAME + " SET "+
                COLUMN_TIME + " = " +"\'"+ alarmItem.getTime() + "\', " +
                COLUMN_DESCRIPTION + " = " + alarmItem.getDescription() + ", " +
                COLUMN_SWITCH + " = " + alarmItem.isSwitchedOn() +
                " WHERE " + COLUMN_ID + " = " + alarmItem.getId();
    }

    public static String updateSwitch(AlarmItem alarmItem){
        return "UPDATE " + NAME + " SET "+
                COLUMN_SWITCH + " = " + alarmItem.isSwitchedOn() +
                " WHERE " + COLUMN_ID + " = " + alarmItem.getId();
    }

    @NonNull
    public static String getDeleteQuery(){
        return "DELETE FROM " + NAME + ";";
    }
}
