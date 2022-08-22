package com.example.HealFitNest.Service.Implementation;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
            if(user.getPassword().length()>=8) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // Strength set as 12
                String encodedPassword = encoder.encode(user.getPassword());
                regUser.setPassword(encodedPassword);
            }
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


        /*try {
            Users users=new Users();
            users.setFirstName(users.getFirstName());
            users.setUserId(users.getUserId());
            users.setLastName(users.getLastName());
            users.setContact(users.getContact());
            String emailreg = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            Pattern pattern = Pattern.compile(emailreg);
            Matcher matcher = pattern.matcher(users.getEmail());
            if (matcher.matches() == true)
                users.setEmail(users.getEmail());
            else
                return "Enter a valid email Id";
            String passreg = "^(?=.\\d)(?=.[a-z])(?=.*[A-Z]).{4,8}$";
            Pattern pat = Pattern.compile(passreg);
            Matcher mat = pat.matcher(users.getPassword());
            if (mat.matches() == true)
                users.setPassword(passwordEncoder.encode(users.getPassword()));
            else
                return "Enter a valid Password";
            userRepo.save(users);
            return "User Added Successfully";
        } catch (ConstraintViolationException e) {
            return e.getMessage();
        } catch (NullPointerException e) {
            return "Email/password is null!";
        }*/

    }
    public Users findUser(String userId){
        return userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found."));
    }
}
