package com.example.guest.ilovediscussing.models;

import org.parceler.Parcel;

import java.util.Date;

@Parcel
public class Comment {
    String content;
    Date date;

    public Comment(){}

    public Comment(String content, Date date) {
        this.content = content;
        this.date = date;
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
