package com.example.guest.ilovediscussing.models;

import org.parceler.Parcel;

import java.util.Date;


@Parcel
public class Post {
    String title;
    String content;
    Date date;
    String pushId;


    public Post(){}

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.date = new Date();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
