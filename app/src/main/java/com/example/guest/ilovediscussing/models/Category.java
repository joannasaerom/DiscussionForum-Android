package com.example.guest.ilovediscussing.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Category {
    String name;
    List<Post> posts = new ArrayList<>();

    public Category() {}

    public Category(String name){

        this.name = name;

    }

    public String getName(){
        return name;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
