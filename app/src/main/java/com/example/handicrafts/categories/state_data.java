package com.example.handicrafts.categories;

public class state_data {

    int image, drop;
    String name;

    public state_data(int image, int drop, String name) {
        this.image = image;
        this.drop = drop;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getDrop() {
        return drop;
    }

    public void setDrop(int drop) {
        this.drop = drop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

