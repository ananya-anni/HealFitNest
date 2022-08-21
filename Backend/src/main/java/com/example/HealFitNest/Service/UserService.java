package com.example.HealFitNest.Service;

import com.example.HealFitNest.Model.Users;

public interface UserService {
    public Users registerUser(Users user);
    public Users findUser(String userId);
}
