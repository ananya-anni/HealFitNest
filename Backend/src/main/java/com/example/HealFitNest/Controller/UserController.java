package com.example.HealFitNest.Controller;

import com.example.HealFitNest.Model.UserLogin;
import com.example.HealFitNest.Model.UserProfile;
import com.example.HealFitNest.Model.Users;
import com.example.HealFitNest.Repository.CartRepo;
import com.example.HealFitNest.Repository.UserRepo;
import com.example.HealFitNest.Service.CartService;
import com.example.HealFitNest.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthenticationManager authenticationManager;

    //Adding the user to the database
    @PostMapping("/api/v2/registerUser")
    private String registerUser(@RequestBody Users user){
        userService.registerUser(user);
        return "User added successfully";
    }

    //Login of the user
    @PostMapping("/api/v2/loginUser")
    private ResponseEntity<?> loginAuth(@RequestBody Users logUser){
        String email=logUser.getEmail();
        String pass=logUser.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, pass));
        }
        catch(Exception ex){
            return  new ResponseEntity<>("Unauthenticated", HttpStatus.UNAUTHORIZED);
        }
        Users loggedUser = userRepo.findByEmail(email);
        UserLogin userLogin=new UserLogin();
        userLogin.setUserId(loggedUser.getUserId());
        String cartId=cartService.showCurrentStatus(loggedUser.getUserId());
        userLogin.setCartId(cartId);
        return new ResponseEntity<>(userLogin, HttpStatus.OK);
    }

    //User Profile
    @GetMapping("/api/v2/myProfile/{userId}")
    private ResponseEntity<UserProfile> myProfile(@PathVariable String userId){
        Users user = userService.findUser(userId);
        UserProfile userProfile = new UserProfile(user.getFirstName(), user.getLastName(), user.getContact(), user.getEmail());
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    //User Logout
    @GetMapping("/api/v2/logout")
    private ResponseEntity<String> userLogout(){
        return new ResponseEntity<>("User and Cart Id does not exist",HttpStatus.OK);
    }
}



