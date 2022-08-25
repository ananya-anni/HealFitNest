package com.example.HealFitNest.Service;


import com.example.HealFitNest.Model.Users;
import com.example.HealFitNest.Repository.UserRepo;
import com.example.HealFitNest.Service.Implementation.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    UserRepo userRepo=mock(UserRepo.class);

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    UserServiceImp userServiceImp;
    @Autowired
    Users user1=new Users();
    @Test
    public void registerUserTest(){

        user1.setUserId("UI9");
        user1.setFirstName("Sharanya");
        user1.setLastName("CH");
        user1.setEmail("shara@gmail.com");
        user1.setPassword("AbcD29#7");
        user1.setContact(Long.valueOf("9876543290"));

        when(userRepo.save(user1)).thenReturn(user1);
        Users users=userServiceImp.registerUser(user1);
        assertEquals("UI9",users.getUserId());

    }

    @Test
    public void findUser(){
        Users user1=new Users();
        user1.setUserId("UI1");
        user1.setFirstName("Isha");
        user1.setLastName("Asthana");
        user1.setEmail("ish@gmail.com");
        user1.setPassword("$2a$10$Yeg.ZI6.SKTW/DGT1lQSTOw1MXV7JcAXVFwTA/6CRfGCzcAHCUlrC");
        user1.setContact(Long.valueOf("9876543211"));

        when(userRepo.findById(any())).thenReturn(Optional.of(user1));
        Users users= userServiceImp.findUser(user1.getUserId());
        assertEquals("Isha",users.getFirstName());
    }
}
