package com.product.api.Controller.Products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.Repository.ProductsRepository;
import com.product.api.models.ProductModel;


@RestController
@RequestMapping("/Products") 
public class ListProducts {
    @Autowired
    private ProductsRepository productsRepository;
   
    @GetMapping("/ListProducts")
    public ResponseEntity<List<ProductModel>> listAll() {
        List<ProductModel> allProducts = productsRepository.findAll();
        return ResponseEntity.ok(allProducts);
    }
}
