package com.example.HealFitNest.Controller;

import com.example.HealFitNest.Model.Users;
import com.example.HealFitNest.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v2")

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/addUser")
    public String saveUser(@RequestBody Users users){
        userRepo.save(users);
        return "User Added Successfully";
    }
    @GetMapping("/users")
    public List<Users> getUser(){
        return userRepo.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Users> getUsersById(@PathVariable String id){
        Users users = userRepo.findById(id).orElse(null);
//                .orElseThrow(() -> new ResourceNotFound("Mentors with the Id : " + id + "was not found!"));
        return ResponseEntity.ok(users);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable String id, @RequestBody Users updatedUser){
        Users updateUser = userRepo.findById(id).orElse(null);
        updateUser.setFirstName(updatedUser.getFirstName());
        updateUser.setLastName(updatedUser.getLastName());
        updateUser.setContact(updatedUser.getContact());
        updateUser.setEmail(updatedUser.getEmail());

        userRepo.save(updateUser);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id){
        userRepo.deleteById(id);
        return "User Deleted Successfully";
    }
}