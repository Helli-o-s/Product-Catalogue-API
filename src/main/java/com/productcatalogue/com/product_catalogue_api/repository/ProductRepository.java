package com.productcatalogue.com.product_catalogue_api.repository;

import com.mongodb.lang.Nullable;
import com.productcatalogue.com.product_catalogue_api.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface ProductRepository extends MongoRepository<Product, String> {
    // You can add custom query methods if needed
    Page<Product> findByAttributesContaining(Map<String, String> attributes, Pageable pageable);

    // Find by name with pagination
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Find by categories with pagination
    Page<Product> findByCategoriesIn(List<String> categories, Pageable pageable);

    // Find all products with pagination
    @Nullable
    Page<Product> findAll(Pageable pageable);
}
