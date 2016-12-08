package com.example.guest.ilovediscussing.models;

import org.parceler.Parcel;

import java.util.Date;

@Parcel
public class Comment {
    String content;
    Date date;
    String pushId;
    String postId;


    public Comment(){}

    public Comment(String content, String postId) {
        this.content = content;
        this.date = date;
        this.postId = postId;
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
}
