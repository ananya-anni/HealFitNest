package com.example.HealFitNest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HealFitNest.Repository.CartRepo;

@RestController
@RequestMapping("/api/v3")
public class CartController {

    @Autowired
    private CartRepo cartRepo;
    
}
