package com.example.HealFitNest.Repository;

import com.example.HealFitNest.Model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends MongoRepository<Address,String> {

}