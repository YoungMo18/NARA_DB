package com.NARAHeritageLab.Server_side_NHRL_DB.Service;

import com.NARAHeritageLab.Server_side_NHRL_DB.Entity.Material;
import com.NARAHeritageLab.Server_side_NHRL_DB.Entity.Product;
import com.NARAHeritageLab.Server_side_NHRL_DB.Entity.Test;
import com.NARAHeritageLab.Server_side_NHRL_DB.Projection.ProductProjection;
import com.NARAHeritageLab.Server_side_NHRL_DB.Repository.MaterialRepository;
import com.NARAHeritageLab.Server_side_NHRL_DB.Repository.ProductRepository;
import com.NARAHeritageLab.Server_side_NHRL_DB.Repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository; // Repository layer dependency injection for Product

    @Autowired
    private MaterialRepository materialRepository; // Repository layer dependency injection for Material

    @Autowired
    private TestRepository testRepository; // Repository layer dependency injection for Test

    // Retrieve all products in a projected form
    public List<ProductProjection> getAllProductsProjection() {
        return productRepository.findAllProductsProjection();
    }

    // Retrieve a specific product by its ID
    public ProductProjection getProductById(int id) {
        return productRepository.findProductById(id);
    }

    // Save a new or updated product to the database
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Delete a product from the database by its ID
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    // Save a list of products to the database (possibly for batch operations)
    public List<Product> saveAllProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    // Update an existing product in the database
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    // Retrieve the ID of a product by its name
    public Integer getProductIdByProductName(String productName) {
        Product product = productRepository.findByProductName(productName);
        return (product != null) ? product.getId() : null;
    }

    // Add a material to a product by their IDs
    public void addMaterialToProduct(Integer productId, Integer materialId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new RuntimeException("Material not found"));

        if (product.getMaterials().contains(material)) {
            throw new IllegalArgumentException("This material is already associated with the product.");
        }

        product.getMaterials().add(material); // Associate the material with the product
        productRepository.save(product); // Save the updated product
    }

    // Add a test to a product by their IDs
    public void addTestToProduct(Integer productId, Integer testId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));

        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new IllegalArgumentException("Test not found with ID: " + testId));

        if (product.getTests().contains(test)) {
            throw new IllegalArgumentException("This test is already associated with the product.");
        }

        product.getTests().add(test); // Associate the test with the product
        productRepository.save(product); // Save the updated product
    }

    // Remove a material from a product by their IDs
    public void removeMaterialFromProduct(Integer productId, Integer materialId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));

        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new IllegalArgumentException("Material not found with ID: " + materialId));

        if (!product.getMaterials().contains(material)) {
            throw new IllegalArgumentException("Material is not associated with the product.");
        }

        product.getMaterials().remove(material); // Disassociate the material from the product
        productRepository.save(product); // Save the updated product
    }

    // Remove a test from a product by their IDs
    public void removeTestFromProduct(Integer productId, Integer testId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));

        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new IllegalArgumentException("Test not found with ID: " + testId));

        if (!product.getTests().contains(test)) {
            throw new IllegalArgumentException("Test is not associated with the product.");
        }

        product.getTests().remove(test); // Disassociate the test from the product
        productRepository.save(product); // Save the updated product
    }
}
