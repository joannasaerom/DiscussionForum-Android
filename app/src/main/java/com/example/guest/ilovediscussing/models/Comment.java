package com.example.guest.ilovediscussing.models;

import java.util.Date;

/**
 * Created by Guest on 12/5/16.
 */
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
