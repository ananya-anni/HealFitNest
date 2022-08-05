package com.example.HealFitNest.Service.Implementation;


import com.example.HealFitNest.Model.Address;
import com.example.HealFitNest.Repository.AddressRepo;
import com.example.HealFitNest.Service.AddressService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class AddressServiceImp implements AddressService {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private AddressRepo addressRepo;


//
    public List<Address> getAllAddress(String userId){
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, Address.class);
//        Address address=addressRepo.findById(userId);
//        return address.getAddressLine1();
    }

//
//    public String getaddressid(String userId) {
//        addressRepo.findById(userId);
//
//        return ;
//    }


//
//    public Address showAddbyId(String userId){
////        return cartRepo.findById(cartId)
////                .orElseThrow(() -> new CartNotFoundException("Cart does not exists."));
////        return addressRepo.findById(userId);
//        return addressRepo.findById(userId);
//
//
//    }



}