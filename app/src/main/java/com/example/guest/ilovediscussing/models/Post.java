package com.example.guest.ilovediscussing.models;

import java.util.Date;

/**
 * Created by Guest on 12/5/16.
 */
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
