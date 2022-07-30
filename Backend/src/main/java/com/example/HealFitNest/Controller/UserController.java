package com.example.HealFitNest.Controller;

import com.example.HealFitNest.Config.UserDetailService;
//import com.example.HealFitNest.Model.Role;
import com.example.HealFitNest.Model.Users;
//import com.example.HealFitNest.Repository.RoleRepo;
import com.example.HealFitNest.Repository.UserRepo;

//import com.example.HealFitNest.Service.UserService;
//import com.example.HealFitNest.event.RegistrationComplete;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v2")

@RestController
public class UserController {

    @Autowired
    private UserDetailService userService;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/addUser")
    private String registerUser(@RequestBody Users users){
    users.setEmail(users.getEmail());
    users.setPassword(passwordEncoder.encode(users.getPassword()));
    userRepo.save(users);
    return "User Added Successfully";

}
    @PostMapping("/loginUser")
    private String loginAuth(@RequestBody Users users){
        String email=users.getEmail();
        String pass=users.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, pass));
        }
        catch(Exception ex){
            return "error";
        }
        return "Authenticated";
    }

}



