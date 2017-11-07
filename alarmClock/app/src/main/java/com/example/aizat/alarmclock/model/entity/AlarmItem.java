package com.example.aizat.alarmclock.model.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.sql.Blob;

/**
 * Created by Aizat on 21.10.2017.
 */

public class AlarmItem implements Parcelable {

    private String time;

    private int isSwitchedOn;

    private String description;

    private int id;

    public AlarmItem (){

    }

    public AlarmItem(String time, String description,int isSwitchedOn, int id) {
        this.time = time;
        this.description = description;
        this.isSwitchedOn = isSwitchedOn;
        this.id = id;
    }

    protected AlarmItem(Parcel in) {
        time = in.readString();
        description = in.readString();
        isSwitchedOn = in.readInt();
        id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(time);
        dest.writeString(description);
        dest.writeInt(isSwitchedOn);
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AlarmItem> CREATOR = new Creator<AlarmItem>() {
        @Override
        public AlarmItem createFromParcel(Parcel in) {
            return new AlarmItem(in);
        }

        @Override
        public AlarmItem[] newArray(int size) {
            return new AlarmItem[size];
        }
    };

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int isSwitchedOn() {
        return isSwitchedOn;
    }

    public void setSwitchedOn(int isSwitchedOn) {
        this.isSwitchedOn = isSwitchedOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
