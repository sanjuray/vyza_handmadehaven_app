package com.example.handicrafts.login;

public class GenderSpinnerDataClass {
    String genderText;
    int gender_image;

    public GenderSpinnerDataClass(String genderText, int Genderimage) {
        this.genderText = genderText;
        this.gender_image = Genderimage;
    }



    public String getGender_text() {
        return genderText;
    }

    public void setGender_text(String genderText) {
        this.genderText = genderText;
    }

    public int getGender_image() {
        return gender_image;
    }

    public void setGender_image(int Genderimage) {
        this.gender_image = Genderimage;
    }
}


