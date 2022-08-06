package com.example.HealFitNest.Service.Implementation;


import com.example.HealFitNest.Model.Address;
import com.example.HealFitNest.Service.AddressService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class AddressServiceImp implements AddressService {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<Address> getAllAddress(String userId){
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, Address.class);
    }

}