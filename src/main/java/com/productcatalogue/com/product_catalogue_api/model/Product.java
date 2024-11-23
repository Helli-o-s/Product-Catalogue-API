package com.productcatalogue.com.product_catalogue_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

@Document(collection = "products")
public class Product {

    @Id
    private String id;

    @NotEmpty
    private String name;

    private String description;

    private double price;

    private List<String> categories;  // Nested Category object

    private List<Map<String,String>> attributes; // Nested list of Attributes

    private Availability availability; // Nested List of Availability

    private List<Ratings> ratings; // Nested List of Ratings

    // Getters and Setters

    public static class Availability {
        private boolean instock;
        private int quantity;

        public boolean getInstock() {
            return instock;
        }
        public void setInstock(boolean instock) {
            this.instock = instock;
        }
        public int getQuantity() {
            return quantity;
        }
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

    }
    public static class Ratings {
        private String userID;
        private int rating;
        private String comment;

        public Ratings(String userID, String comment,int rating) {
            this.comment = comment;
            this.rating = rating;
            this.userID = userID;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }
    }

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

    public List<Map<String,String>> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Map<String,String>> attributes) {
        this.attributes = attributes;
    }
    public Availability getAvailability() {
        return availability;
    }
    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public List<Ratings> getRatings() {
        return ratings;
    }

    public void setRatings(List<Ratings> ratings) {
        this.ratings = ratings;
    }
}
