package com.example.guest.ilovediscussing.models;

import org.parceler.Parcel;

@Parcel
public class User {
    String name;
    String email;
    String uId;

    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }


    public String getUsername() {
        return name;
    }

    public void setUsername(String username) {
        this.name = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }
}
