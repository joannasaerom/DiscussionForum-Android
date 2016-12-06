package com.example.guest.ilovediscussing.models;

import org.parceler.Parcel;

import java.util.Date;

@Parcel
public class Post {
    String title;
    String content;
    Date date;

    public Post(){}

    public Post(String title, String content, Date date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
