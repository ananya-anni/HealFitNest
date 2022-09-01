package com.example.HealFitNest.Repository;

import com.example.HealFitNest.Model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends MongoRepository<Address,String> {
    List<Address> findByUserId(String userId);

}