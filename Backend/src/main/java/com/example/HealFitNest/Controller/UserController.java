package com.example.HealFitNest.Controller;

import com.example.HealFitNest.Config.UserDetailService;
//import com.example.HealFitNest.Model.Role;
import com.example.HealFitNest.Config.ValidationConfig;
import com.example.HealFitNest.Handler.UserNotFoundException;
import com.example.HealFitNest.Model.UserProfile;
//import com.example.HealFitNest.Handler.NotNullException;
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

import javax.validation.ConstraintViolationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequestMapping("/api/v2")

@RestController
public class UserController {

    @Autowired
    private UserDetailService userService;

    @Autowired
    private UserProfile userProfile;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/addUser")
    private String registerUser(@RequestBody Users users){

        try{
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
        } catch (ConstraintViolationException e){
            return e.getMessage();
        } catch (NullPointerException e){
            return "Email/password is null!";
        }
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
        Users user = userRepo.findByEmail(email);
        return new ResponseEntity<>(user.getUserId(),HttpStatus.OK);
    }

    @GetMapping("/profileview/{userId")
    private ResponseEntity<UserProfile>myProfile(@PathVariable String userId){
        Users user =userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found."));
        UserProfile userProfile =new UserProfile(user.getFirstName(), user.getLastName(),user.getContact(),user.getEmail());
        return new ResponseEntity<>(userProfile,HttpStatus.OK);
    }


}



