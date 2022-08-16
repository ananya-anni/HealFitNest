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

@AllArgsConstructor
@NoArgsConstructor
@Service
public class AddressServiceImp implements AddressService {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private AddressRepo addressRepo;

    public List<Address> getAllAddress(String userId){
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));

        return mongoTemplate.find(query, Address.class);
    }

    public void saveAddress(Address address, String userId) {
            address.setUserId(userId);
            addressRepo.save(address);
    }

    public List<Address> findAllAddress() {
        return addressRepo.findAll();
    }

    public void deleteAddressById(String id) {
        addressRepo.deleteById(id);
    }

    public void updateAddressValues(String userId, String addressId, Address updatedAddress) {
        Address updateAddress = addressRepo.findById(addressId).orElse(null);
        updateAddress.setAddressLine1(updatedAddress.getAddressLine1());
        updateAddress.setAddressLine2((updatedAddress.getAddressLine2()));
        updateAddress.setCity(updatedAddress.getCity());
        updateAddress.setState(updatedAddress.getState());
        updateAddress.setCountry(updatedAddress.getCountry());
        updateAddress.setPostalCode(updatedAddress.getPostalCode());
        addressRepo.save(updateAddress);
    }

}