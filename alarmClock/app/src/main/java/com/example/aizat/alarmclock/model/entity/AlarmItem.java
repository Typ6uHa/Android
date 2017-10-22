package com.example.aizat.alarmclock.model.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.sql.Blob;

/**
 * Created by Aizat on 21.10.2017.
 */

public class AlarmItem {

    private String time;

    private int isSwitchedOn;

    private String description;

    public AlarmItem() {
    }

    public AlarmItem(String time, String description, int isSwitchedOn) {
        this.time = time;
        this.isSwitchedOn = isSwitchedOn;
        this.description = description;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int isSwitchedOn() {
        return isSwitchedOn;
    }

    public void setSwitchedOn(int switchedOn) {
        isSwitchedOn = switchedOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
