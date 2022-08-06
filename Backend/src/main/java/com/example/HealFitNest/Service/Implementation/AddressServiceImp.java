package com.example.HealFitNest.Service.Implementation;


import com.example.HealFitNest.Config.ValidationConfig;
import com.example.HealFitNest.Model.Address;
import com.example.HealFitNest.Repository.AddressRepo;
import com.example.HealFitNest.Service.AddressService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    @Autowired
    private ValidationConfig validationConfig;

    enum Country{India};
    enum City{Bangalore, Chennai, Gurgaon, Mumbai, Jaipur};
    enum State{Karnataka, TamilNadu, Haryana, Maharashtra, Rajasthan};

    Country countries[] = Country.values();
    City cities[] = City.values();
    State states[] = State.values();



    public List<Address> getAllAddress(String userId){
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, Address.class);
//        Address address=addressRepo.findById(userId);
//        return address.getAddressLine1();
    }

    @Override
    public void saveAddress(Address address) {
//            Address addresses = new Address();
//            addresses.setAddressLine1(address.getAddressLine1());
//            addresses.setAddressLine2(address.getAddressLine2());
//            for(City city: cities){
//                if(address.getCity() == null) return 5;
//                if(address.getState() == null) return 6;
//                if(address.getCountry() == null) return 7;
//                if(city.equals(address.getCity())){
//                    addresses.setCity(address.getCity());
//                }
//                else{
//                    return 1;
//                }
//            }
//            for(State state: states){
//                if(state.equals(address.getState())){
//                    addresses.setState(address.getState());
//                }
//                else{
//                    return 2;
//                }
//            }
//            for(Country country: countries){
//                if(country.equals(address.getCountry())){
//                    addresses.setCountry(address.getCountry());
//                }
//                else{
//                    return 3;
//                }
//            }
            addressRepo.save(address);

    }

    @Override
    public List<Address> findAllAddress() {
        return addressRepo.findAll();
    }

    @Override
    public void deleteAddressById(String id) {
        addressRepo.deleteById(id);
    }

    @Override
    public void updateAddressValues(String id, Address updatedAddress) {
        Address updateAddress = addressRepo.findById(id).orElse(null);
        updateAddress.setAddressLine1(updatedAddress.getAddressLine1());
        updateAddress.setAddressLine2((updatedAddress.getAddressLine2()));
        updateAddress.setCity(updatedAddress.getCity());
        updateAddress.setState(updatedAddress.getState());
        updateAddress.setCountry(updatedAddress.getCountry());
        updateAddress.setPostalCode(updatedAddress.getPostalCode());
        addressRepo.save(updateAddress);
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