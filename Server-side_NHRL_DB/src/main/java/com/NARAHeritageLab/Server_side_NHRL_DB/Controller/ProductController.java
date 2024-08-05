package com.NARAHeritageLab.Server_side_NHRL_DB.Controller;

import com.NARAHeritageLab.Server_side_NHRL_DB.Entity.Product;
import com.NARAHeritageLab.Server_side_NHRL_DB.Projection.ProductProjection;
import com.NARAHeritageLab.Server_side_NHRL_DB.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Endpoint to get all products
    @GetMapping
    public ResponseEntity<List<ProductProjection>> getAllProducts() {
        List<ProductProjection> products = productService.getAllProductsProjection();
        if (products == null || products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 if no products found
        }
        return new ResponseEntity<>(products, HttpStatus.OK); // Return 200 with the list of products
    }

    // Endpoint to get a specific product by its ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductProjection> getProductById(@PathVariable int id) {
        ProductProjection product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if the product is not found
        }
        return new ResponseEntity<>(product, HttpStatus.OK); // Return 200 with the product data
    }

    // Endpoint to get the ID of a product by its name
    @GetMapping("/id")
    public ResponseEntity<Integer> getProductIdByProductName(@RequestParam String productName) {
        Integer productId = productService.getProductIdByProductName(productName);
        if (productId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if the product name is not found
        }
        return new ResponseEntity<>(productId, HttpStatus.OK); // Return 200 with the product ID
    }

    // Endpoint to create a new product
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            Product savedProduct = productService.saveProduct(product);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED); // Return 201 with the saved product
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while creating the product: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 with an error message
        }
    }

    // Endpoint to update an existing product by its ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Product product) {
        try {
            product.setId(id); // Set the ID to ensure the correct product is updated
            Product updatedProduct = productService.updateProduct(product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK); // Return 200 with the updated product
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while updating the product: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 with an error message
        }
    }

    // Endpoint to delete a product by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Product successfully deleted.", HttpStatus.OK); // Return 200 when the product is deleted
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("Product not found with ID: " + id, HttpStatus.NOT_FOUND); // Return 404 if the product is not found
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while deleting the product: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 with an error message
        }
    }

    // Endpoint to add a material to a product
    @PostMapping("/{productId}/materials/{materialId}")
    public ResponseEntity<String> addMaterialToProduct(@PathVariable Integer productId, @PathVariable Integer materialId) {
        try {
            productService.addMaterialToProduct(productId, materialId);
            return new ResponseEntity<>("Material successfully added to product.", HttpStatus.OK); // Return 200 when the material is added
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // Return 404 if the product or material is not found
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 with an error message
        }
    }

    // Endpoint to add a test to a product
    @PostMapping("/{productId}/tests/{testId}")
    public ResponseEntity<String> addTestToProduct(@PathVariable Integer productId, @PathVariable Integer testId) {
        try {
            productService.addTestToProduct(productId, testId);
            return new ResponseEntity<>("Test successfully added to product.", HttpStatus.OK); // Return 200 when the test is added
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST); // Return 400 if the product or test is not found
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 with an error message
        }
    }

    // Endpoint to remove a material from a product
    @DeleteMapping("/{productId}/materials/{materialId}")
    public ResponseEntity<String> deleteMaterialFromProduct(@PathVariable Integer productId, @PathVariable Integer materialId) {
        try {
            productService.removeMaterialFromProduct(productId, materialId);
            return new ResponseEntity<>("Material successfully removed from product.", HttpStatus.OK); // Return 200 when the material is removed
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("Product or Material not found.", HttpStatus.NOT_FOUND); // Return 404 if the product or material is not found
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while removing the material from the product: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 with an error message
        }
    }

    // Endpoint to remove a test from a product
    @DeleteMapping("/{productId}/tests/{testId}")
    public ResponseEntity<String> deleteTestFromProduct(@PathVariable Integer productId, @PathVariable Integer testId) {
        try {
            productService.removeTestFromProduct(productId, testId);
            return new ResponseEntity<>("Test successfully removed from product.", HttpStatus.OK); // Return 200 when the test is removed
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("Product or Test not found.", HttpStatus.NOT_FOUND); // Return 404 if the product or test is not found
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while removing the test from the product: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 with an error message
        }
    }
}
