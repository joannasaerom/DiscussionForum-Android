package com.example.guest.ilovediscussing.models;

import org.parceler.Parcel;

import java.util.Date;

@Parcel
public class Comment {
    String content;
    Date date;
    String pushId;
    String postId;
    String userId;
    String userName;

    public Comment(){}

    public Comment(String content, String postId, String userId, String userName) {
        this.content = content;
        this.date = new Date();
        this.postId = postId;
        this.userId = userId;
        this.userName = userName;
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

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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
