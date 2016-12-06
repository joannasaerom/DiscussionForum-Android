package com.example.guest.ilovediscussing.models;

import org.parceler.Parcel;

@Parcel
public class Category {
    String name;

    public Category() {}

    public Category(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
