package com.example.guest.ilovediscussing.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Category {
    String name;
    String pushId;

    public Category() {}

    public Category(String name){

        this.name = name;

    }

    public String getName(){
        return name;
    }


    public String getPushId(){
        return pushId;
    }

    public void setPushId(String pushId){
        this.pushId = pushId;
    }


}
