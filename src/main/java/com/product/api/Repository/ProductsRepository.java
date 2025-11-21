package com.product.api.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.api.models.ProductModel;

public interface ProductsRepository extends JpaRepository<ProductModel, UUID> {
    
}
