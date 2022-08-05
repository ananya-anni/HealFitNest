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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        users.setFirstName(users.getFirstName());
        users.setUserId(users.getUserId());
        users.setLastName(users.getLastName());
        users.setContact(users.getContact());
        String emailreg="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
                Pattern pattern=Pattern.compile(emailreg);
        Matcher matcher= pattern.matcher(users.getEmail());
        if(matcher.matches()==true)
            users.setEmail(users.getEmail());
        else
            return "Enter a valid email Id";
        String passreg= "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$";
        Pattern pat=Pattern.compile(passreg);
        Matcher mat= pat.matcher(users.getPassword());
        if(mat.matches()==true)
            users.setPassword(passwordEncoder.encode(users.getPassword()));
        else
            return "Enter a valid Password";
        userRepo.save(users);
        return "User Added Successfully";

}
    @PostMapping("/loginUser")
    private ResponseEntity<String> loginAuth(@RequestBody Users users){
        String email=users.getEmail();
        String pass=users.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, pass));
        }
        catch(Exception ex){
            return  new ResponseEntity<>("Unauthenticated", HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok("Authenticated");
    }

}



