package com.example.HealFitNest.Config;


import com.example.HealFitNest.Model.Users;
import com.example.HealFitNest.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Users users = userRepo.findByEmail(email);
        if(users!=null){
            String name=users.getEmail();
            String pwd=users.getPassword();
            return new User(name,pwd,new ArrayList<>());//Here the user is of spring security
        }
        else
        {
            throw new UsernameNotFoundException("No User");
        }

    }

}
