package com.example.HealFitNest.Controller;

import com.example.HealFitNest.Model.Product;
import com.example.HealFitNest.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")

@RestController

public class ProductController {
    @Autowired
    private ProductRepo productRepo;

    @PostMapping("/addProduct")
    public String saveProduct(@RequestBody Product product){
        productRepo.save(product);
        return "Added Successfully";
    }

    @GetMapping("/products")
    public List<Product> getProduct(){
        return productRepo.findAll();
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        productRepo.deleteById(id);
        return "Deleted Successfully";
    }
}
