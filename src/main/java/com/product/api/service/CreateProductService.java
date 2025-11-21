package com.product.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.product.api.Dtos.CreateProductDTO;
import com.product.api.Repository.ProductsRepository;
import com.product.api.models.ProductModel;

@Component
public class CreateProductService {

    @Autowired
    private ProductsRepository productsRepository;

    public void createProduct(CreateProductDTO newproduct) {
        ProductModel product = new ProductModel();
        product.setName(newproduct.getName());      
        product.setPrice(newproduct.getPrice());
        product.setQuantity(newproduct.getQuantity());
        product.setCategory(newproduct.getCategory());
        

        productsRepository.save(product);
    }
}
