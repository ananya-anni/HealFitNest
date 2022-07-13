package com.example.HealFitNest.Controller;

import com.example.HealFitNest.Model.Users;
import com.example.HealFitNest.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v2")

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/addUser")
    public String saveCustomer(@RequestBody Users users){
        userRepo.save(users);
        return "Customer Added Successfully";
    }
    @GetMapping("/users")
    public List<Users> getCustomer(){
        return userRepo.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable String id){
        userRepo.deleteById(id);
        return "Deleted Successfully";
    }
}

