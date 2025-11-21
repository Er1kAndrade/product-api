package com.product.api.Controller.Products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.Dtos.CreateProductDTO;
import com.product.api.Dtos.ProductResponseDTO;
import com.product.api.exception.InvalidProductException;
import com.product.api.service.CreateProductService;

@RestController
@RequestMapping("/Products") 
public class CreateProduct {
    
    @Autowired
    private CreateProductService createProductService;

    @PostMapping("/Create")
    ResponseEntity<ProductResponseDTO> create(@RequestBody CreateProductDTO newproduct) {
        
        if (newproduct.getName() == null || newproduct.getName().trim().isEmpty()) {
            throw new InvalidProductException("You need to provide a name for the product");
        } 
        
        if (newproduct.getPrice() < 0 ) {
            throw new InvalidProductException("The price cannot be negative");
        }


        if (newproduct.getQuantity() < 0 ) {
            throw new InvalidProductException("The quantity cannot be negative");
        }

        createProductService.createProduct(newproduct);

         ProductResponseDTO  response = (new ProductResponseDTO(
            newproduct.getName(),
            newproduct.getPrice(),
            newproduct.getQuantity(),
            newproduct.getCategory()
        ));

        return ResponseEntity.status(HttpStatus.CREATED)
                        .body(response);
    }
}
