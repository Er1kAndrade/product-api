package com.product.api.Controller.Products;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.product.api.Dtos.ProductResponseDTO;
import com.product.api.Repository.ProductsRepository;
import com.product.api.exception.InvalidProductException;
import com.product.api.models.ProductModel;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Products")
public class GetProduct {
    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping("/GetById/{id}")
    ResponseEntity<ProductResponseDTO> GetProductById(
        @PathVariable("id") UUID productid, HttpServletRequest request ) {
    
        ProductModel product = productsRepository
            .findById(productid)
            .orElseThrow(() -> new InvalidProductException("Product not found"));


        ProductResponseDTO  response = (new ProductResponseDTO(
            product.getName(),
            product.getPrice(),
            product.getQuantity(),
            product.getCategory()
        ));

        return ResponseEntity.status(HttpStatus.OK)
                        .body(response);
    }
}
