package com.example.HealFitNest.Controller;

import com.example.HealFitNest.Model.Users;
import com.example.HealFitNest.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v2")

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/addUser")
    public String saveUser(@RequestBody Users users){
//        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        userRepo.save(users);
        return "User Added Successfully";
    }
    @GetMapping("/users")
    public List<Users> getUser(){
        return userRepo.findAll();
    }

    public Users FindUserByEmail(String email){
        return userRepo.findByemail(email);
    }

    @GetMapping("/users/{id}")
    public Users getUserById(@PathVariable String id){
      return userRepo.findById(id).orElse(null);
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable String id, @RequestBody Users updatedUser){
        Users updateUser = userRepo.findById(id).orElse(null);
        updateUser.setFirstName(updatedUser.getFirstName());
        updateUser.setLastName(updatedUser.getLastName());
        updateUser.setContact(updatedUser.getContact());
        updateUser.setEmail(updatedUser.getEmail());
        userRepo.save(updateUser);
        return "updated Successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id){
        userRepo.deleteById(id);
        return "User Deleted Successfully";
    }

}