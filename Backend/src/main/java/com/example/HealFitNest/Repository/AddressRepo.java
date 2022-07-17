package com.example.HealFitNest.Repository;

import com.example.HealFitNest.Model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepo extends MongoRepository<Address,String> {

}
