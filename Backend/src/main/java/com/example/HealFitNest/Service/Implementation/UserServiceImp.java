package com.example.HealFitNest.Service.Implementation;

import com.example.HealFitNest.Model.Users;
import com.example.HealFitNest.Repository.UserRepo;
import com.example.HealFitNest.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintViolationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registeredUser(Users users){

        try{
            users.setFirstName(users.getFirstName());
            users.setUserId(users.getUserId());
            users.setLastName(users.getLastName());
            users.setContact(users.getContact());


            String emailreg="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            Pattern pattern=Pattern.compile(emailreg);
            Matcher matcher= pattern.matcher(users.getEmail());
            if(matcher.matches())
                users.setEmail(users.getEmail());
            else
                return "Enter a valid email Id";

            //Atleast one digit and one lowercase and one uppercase and one symbol should be present
            //length should be between 8 and 20 characters
            String passreg= "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()]).{8,20}$";
//            Compile the ReGex
            Pattern pat=Pattern.compile(passreg);
            // Pattern class contains matcher() method
            // to find matching between given password
            // and regular expression.
            Matcher mat= pat.matcher(users.getPassword());
            if(mat.matches())//Password matches the regex
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
}
