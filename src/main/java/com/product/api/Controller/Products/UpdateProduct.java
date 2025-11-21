package com.product.api.Controller.Products;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.Dtos.CreateProductDTO;
import com.product.api.Dtos.ProductResponseDTO;
import com.product.api.Repository.ProductsRepository;
import com.product.api.exception.InvalidProductException;
import com.product.api.models.ProductModel;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Products")
public class UpdateProduct {
    @Autowired
    private ProductsRepository productsRepository;

    @PatchMapping("/UpdateProductById/{id}")
    ResponseEntity<ProductResponseDTO> GetProductById(
        @PathVariable("id") UUID productid, HttpServletRequest request, @RequestBody CreateProductDTO newproduct) {
    
        ProductModel product = productsRepository
            .findById(productid)
            .orElseThrow(() -> new InvalidProductException("Product not found"));

        product.setName(newproduct.getName());      
        product.setPrice(newproduct.getPrice());        
        product.setQuantity(newproduct.getQuantity());
        product.setCategory(newproduct.getCategory());
            
        productsRepository.save(product);

        ProductResponseDTO  response = (new ProductResponseDTO(
            newproduct.getName(),
            newproduct.getPrice(),
            newproduct.getQuantity(),
            newproduct.getCategory()
        ));

        return ResponseEntity.status(HttpStatus.OK)
                        .body(response);
    }
}
