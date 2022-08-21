package com.example.HealFitNest.Service.Implementation;

<<<<<<< HEAD
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
=======
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.HealFitNest.Handler.UserNotFoundException;
import com.example.HealFitNest.Model.Users;
import com.example.HealFitNest.Repository.UserRepo;
import com.example.HealFitNest.Service.UserService;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Users registerUser(Users user) {
        try{
            Users regUser = new Users();
            regUser.setFirstName(user.getFirstName());
            regUser.setLastName(user.getLastName());
            regUser.setUserId(user.getUserId());
            String emailreg="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            Pattern pattern=Pattern.compile(emailreg);
            Matcher matcher= pattern.matcher(user.getEmail());
            if(matcher.matches()==true)
                regUser.setEmail(user.getEmail());
            else
                throw new UserNotFoundException("Email not valid");
            if(user.getPassword().length()>=8)
                regUser.setPassword(passwordEncoder.encode(user.getPassword()));
            else
                throw new UserNotFoundException("Enter a password of lenght 8.");
            if(String.valueOf(user.getContact()).length()==10)
                regUser.setContact(user.getContact());
            else
                throw new UserNotFoundException("Enter a valid phone number.");
            userRepo.save(regUser);
            return regUser;
        }catch (ConstraintViolationException e){
            throw new UserNotFoundException("Constraints are getting violated");
        } catch (NullPointerException e){
            throw new UserNotFoundException("Email/Password is null");
        }
    }

    public Users findUser(String userId){
        return userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found."));
    }
}
>>>>>>> 5253fce9ef2fc6823ce2276883d6328e156a06c1
