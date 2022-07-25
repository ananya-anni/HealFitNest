package com.example.HealFitNest.Repository;

import com.example.HealFitNest.Model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<Users,String> {
    Users findByemail(String email);
}
