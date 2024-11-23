package com.productcatalogue.com.product_catalogue_api.exception;


public class ProductNotFoundException extends RuntimeException {

    // Constructor with a message
    public ProductNotFoundException(String message) {
        super(message);
    }

    // Constructor with a message and cause
    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

