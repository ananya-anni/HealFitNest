package com.example.HealFitNest.Controller;

import com.example.HealFitNest.Model.UserProfile;
import com.example.HealFitNest.Model.Users;
import com.example.HealFitNest.Repository.UserRepo;
import com.example.HealFitNest.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v2")
@RestController
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/registerUser")
    private String registerUser(@RequestBody Users user){
        userService.registerUser(user);
        return "User added successfully";
    }

    @PostMapping("/loginUser")
    private ResponseEntity<String> loginAuth(@RequestBody Users logUser){
        String email=logUser.getEmail();
        String pass=logUser.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, pass));
        }
        catch(Exception ex){
            return  new ResponseEntity<>("Unauthenticated", HttpStatus.UNAUTHORIZED);
        }
        Users loggedUser = userRepo.findByEmail(email);
        return new ResponseEntity<>(loggedUser.getUserId(), HttpStatus.OK);
    }

    @GetMapping("/myProfile/{userId}")
    private ResponseEntity<UserProfile> myProfile(@PathVariable String userId){
        Users user = userService.findUser(userId);
        UserProfile userProfile = new UserProfile(user.getFirstName(), user.getLastName(), user.getContact(), user.getEmail());
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }
}



