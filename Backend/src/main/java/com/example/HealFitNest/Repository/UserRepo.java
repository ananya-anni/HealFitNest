

package com.example.HealFitNest.Repository;

import com.example.HealFitNest.Model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<Users,String> {
//    Users findByname(String name);
     Users findByEmail(String email);
}