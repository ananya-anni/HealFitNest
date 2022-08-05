package com.example.HealFitNest.Config;


//import com.example.HealFitNest.Model.Role;
import com.example.HealFitNest.Model.Users;
//import com.example.HealFitNest.Repository.RoleRepo;
import com.example.HealFitNest.Repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


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
            return new User(name,pwd,new ArrayList<>());

        }
        else
        {
            throw new UsernameNotFoundException("No User");
        }

    }

}
