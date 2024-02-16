package com.example.handicrafts.categories;

public class data {
    String cat_text;
    int cat_image;

    public data(String cat_text, int cat_image) {
        this.cat_text = cat_text;
        this.cat_image = cat_image;
    }

    public data() {
    }

    public String getCat_text() {
        return cat_text;
    }

    public void setCat_text(String cat_text) {
        this.cat_text = cat_text;
    }

    public int getCat_image() {
        return cat_image;
    }

    public void setCat_image(int cat_image) {
        this.cat_image = cat_image;
    }
}
