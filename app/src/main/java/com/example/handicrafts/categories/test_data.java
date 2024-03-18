package com.example.handicrafts.categories;

public class test_data {
    int image;
    String price, name,dicount;

    public test_data(int image, String price, String name, String dicount) {
        this.image = image;
        this.price = price;
        this.name = name;
        this.dicount = dicount;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDicount() {
        return dicount;
    }

    public void setDicount(String dicount) {
        this.dicount = dicount;
    }
}
