package com.product.api.Controller.Products;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.Repository.ProductsRepository;
import com.product.api.exception.InvalidProductException;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Products")
public class DeleteProduct {
    @Autowired
    private ProductsRepository productsRepository;

    @DeleteMapping("/DeleteProductById/{id}")
    ResponseEntity<String> GetProductById(
        @PathVariable("id") UUID productid, HttpServletRequest request) {
    
        productsRepository
            .findById(productid)
            .orElseThrow(() -> new InvalidProductException("Product not found"));

        productsRepository.deleteById(productid);

       
        return ResponseEntity.status(HttpStatus.OK)
                        .body("Product deleted successfully");
    }
}
