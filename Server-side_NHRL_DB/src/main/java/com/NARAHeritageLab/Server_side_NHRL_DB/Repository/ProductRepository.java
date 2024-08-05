package com.NARAHeritageLab.Server_side_NHRL_DB.Repository;

import com.NARAHeritageLab.Server_side_NHRL_DB.Entity.Product;
import com.NARAHeritageLab.Server_side_NHRL_DB.Projection.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Find a product by its name
    Product findByProductName(String productName);

    // Custom query to retrieve all products as projections
    @Query("SELECT p FROM Product p")
    List<ProductProjection> findAllProductsProjection();

    // Custom query to retrieve a specific product by its ID
    @Query("SELECT p FROM Product p WHERE p.id = :id")
    ProductProjection findProductById(@Param("id") Integer id);
}
