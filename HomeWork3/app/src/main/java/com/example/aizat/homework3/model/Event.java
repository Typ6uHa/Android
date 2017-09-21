package com.example.aizat.homework3.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Aizat on 21.09.2017.
 */

public class Event {

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

    SimpleDateFormat fmtOut = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

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
}
