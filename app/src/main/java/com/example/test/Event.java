package com.example.test;

/**
 * Created by Purba on 3/31/2017.
 */

public class Event {
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
    String date;
    int image;

    public Event(String name, String date, int image) {
        this.name = name;
        this.date = date;
        this.image = image;
    }




}
