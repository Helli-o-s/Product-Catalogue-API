package com.productcatalogue.com.product_catalogue_api.controller;

import com.productcatalogue.com.product_catalogue_api.exception.ProductNotFoundException;
import com.productcatalogue.com.product_catalogue_api.model.ProductDTO;
import com.productcatalogue.com.product_catalogue_api.model.RatingDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.productcatalogue.com.product_catalogue_api.model.Product;
import com.productcatalogue.com.product_catalogue_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get a product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // Update a product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    // Search a product
    @GetMapping("/search")
    public ResponseEntity<PagedModel<EntityModel<ProductDTO>>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<String> categories,
            @RequestParam(required = false) Map<String, String> attributes,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            Pageable pageable) {

        // Use Spring Data's PagedModel for stable JSON serialization
        Page<ProductDTO> productPage = productService.searchProducts(name, categories, attributes, pageable);

        // Map products to EntityModel<ProductDTO> for HATEOAS support
        PagedModel<EntityModel<ProductDTO>> pagedModel = PagedModel.of(
                productPage.stream().map(EntityModel::of).collect(Collectors.toList()),
                new PagedModel.PageMetadata(pageable.getPageSize(), productPage.getNumber(), productPage.getTotalElements())
        );

        return ResponseEntity.ok(pagedModel);
    }
    @PostMapping("/{productId}/ratings")
    public ResponseEntity<Product> addRating(
            @PathVariable String productId,
            @RequestBody RatingDTO ratingDTO) {

        // Add the rating to the product
        Product updatedProduct = productService.addRating(productId, ratingDTO);

        // Return the updated product
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException ex) {
        // Return an error response with a custom message
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}



