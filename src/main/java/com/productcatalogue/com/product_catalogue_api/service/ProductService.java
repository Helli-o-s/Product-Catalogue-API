package com.productcatalogue.com.product_catalogue_api.service;

import com.productcatalogue.com.product_catalogue_api.exception.ProductNotFoundException;
import com.productcatalogue.com.product_catalogue_api.model.RatingDTO;
import com.productcatalogue.com.product_catalogue_api.model.Product;
import com.productcatalogue.com.product_catalogue_api.model.ProductDTO;
import com.productcatalogue.com.product_catalogue_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    // Create a new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Update a product
    public Product updateProduct(String id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return productRepository.save(product);
        }
        return null;
    }

    // Delete a product
    public boolean deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
    //Search For Product
    public Page<ProductDTO> searchProducts(String name, List<String> categories, Map<String, String> attributes, Pageable pageable) {
        // Fetch paginated products from the repository
        Page<Product> productPage = productRepository.findAll(pageable);

        // Map Page<Product> to Page<ProductDTO>
        assert productPage != null;
        return productPage.map(product -> new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategories(),
                mapRatings(product.getRatings())  // Map Product.Ratings to ProductDTO.Ratings
        ));
    }

    // Helper method to map Product.Rating to ProductDTO.Rating
    private List<ProductDTO.Ratings> mapRatings(List<Product.Ratings> ratings) {
        return ratings.stream()
                .map(rating -> new ProductDTO.Ratings(
                        rating.getUserID(),
                        rating.getRating(),
                        rating.getComment()
                ))
                .collect(Collectors.toList());
    }
    @Transactional
    public Product addRating(String productId, RatingDTO ratingDTO) {
        // Find the product by ID
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        // Create a new Rating object from RatingDTO
        Product.Ratings newRating = new Product.Ratings(
                ratingDTO.getUserID(),
                ratingDTO.getComment(),
                ratingDTO.getRating()
        );

        // Add the new rating to the product's ratings list
        product.getRatings().add(newRating);

        // Save the updated product back to the database
        return productRepository.save(product);
    }
}
