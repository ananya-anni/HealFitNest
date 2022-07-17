package com.example.HealFitNest.Controller;

import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")

@RestController

public class ProductController {
    @Autowired
    private ItemRepo productRepo;

    @PostMapping("/addProduct")
    public String saveItem(@RequestBody Item item){
        productRepo.save(item);
        return "Added Successfully";
    }

    @GetMapping("/products")
    public List<Item> getItem(){
        return productRepo.findAll();
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id){
        productRepo.deleteById(id);
        return "Deleted Successfully";
    }
}
