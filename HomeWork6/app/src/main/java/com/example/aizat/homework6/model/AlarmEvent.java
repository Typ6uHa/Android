package com.example.aizat.homework6.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Switch;

import java.util.Date;

/**
 * Created by Aizat on 15.10.2017.
 */

public class AlarmEvent implements Parcelable {

    private int id;

    private Switch aSwitch;

    private String description;

    private String date;

    protected AlarmEvent(Parcel in) {
        id = in.readInt();
        description = in.readString();
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(description);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AlarmEvent> CREATOR = new Creator<AlarmEvent>() {
        @Override
        public AlarmEvent createFromParcel(Parcel in) {
            return new AlarmEvent(in);
        }

        @Override
        public AlarmEvent[] newArray(int size) {
            return new AlarmEvent[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Switch getaSwitch() {
        return aSwitch;
    }

    public void setaSwitch(Switch aSwitch) {
        this.aSwitch = aSwitch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
