package com.productcatalogue.com.product_catalogue_api.model;

public class RatingDTO {
    private String userID;
    private int rating;  // Rating value, for example between 1 and 5
    private String comment;  // Optional comment

    // Getters and setters

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

