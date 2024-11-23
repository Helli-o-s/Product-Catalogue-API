package com.productcatalogue.com.product_catalogue_api.model;

import java.util.List;

public class ProductDTO {

    private String id;
    private String name;
    private String description;
    private double price;
    private List<String> categories;
    private List<Ratings> ratings;

    // Constructors
    public ProductDTO(String id, String name, String description, double price, List<String> categories, List<Ratings> ratings) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categories = categories;
        this.ratings = ratings;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Ratings> getRatings() {
        return ratings;
    }

    public void setRatings(List<Ratings> ratings) {
        this.ratings = ratings;
    }

    // Rating inner class (for the example)
    public static class Ratings {
        private String userID;
        private int rating;
        private String comment;

        public Ratings(String userID, int rating, String comment) {
            this.userID = userID;
            this.rating = rating;
            this.comment = comment;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userId) {
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
}
