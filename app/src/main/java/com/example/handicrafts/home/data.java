package com.example.handicrafts.home;

public class data {

    String user_name;
    String user_review;
    int review_id;
    int user_rating;

    public data(String user_name, String user_review, int review_id, int user_rating) {
        this.user_name = user_name;
        this.user_review = user_review;
        this.review_id = review_id;
        this.user_rating = user_rating;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_review() {
        return user_review;
    }

    public void setUser_review(String user_review) {
        this.user_review = user_review;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public int getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(int user_rating) {
        this.user_rating = user_rating;
    }
}
