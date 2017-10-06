package com.example.aizat.homework3.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Aizat on 21.09.2017.
 */

public class Event implements Parcelable {

    private int id;

    private int photoId;

    private String title;

    private String description;

    private Date date;

    public Event(int id, int photoId, String title, String description, Date date) {
        this.id = id;
        this.photoId = photoId;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    private SimpleDateFormat fmtOut = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

    protected Event(Parcel in) {
        id = in.readInt();
        photoId = in.readInt();
        title = in.readString();
        description = in.readString();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public int getId() {
        return id;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return fmtOut.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(photoId);
        parcel.writeString(title);
        parcel.writeString(description);
    }
}
