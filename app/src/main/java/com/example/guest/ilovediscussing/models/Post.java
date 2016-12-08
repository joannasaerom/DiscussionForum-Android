package com.example.guest.ilovediscussing.models;

import org.parceler.Parcel;

import java.util.Date;


@Parcel
public class Post {
    String title;
    String content;
    Date date;
    String categoryId;
    String pushId;
    String userId;
    String userName;


    public Post(){}

    public Post(String title, String content, String categoryId, String userId, String userName) {
        this.title = title;
        this.content = content;
        this.date = new Date();
        this.categoryId = categoryId;
        this.userId = userId;
        this.userName = userName;
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }
}
