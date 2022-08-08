package com.example.HealFitNest.Controller;

import com.example.HealFitNest.Model.Address;
import com.example.HealFitNest.Repository.AddressRepo;

import com.example.HealFitNest.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RequestMapping("/api/v3")

@RestController
public class AddressController{
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private AddressService addressService;

    //Adding Address
    @PostMapping("/addAddress")
    public ResponseEntity<?> saveAddress(@RequestBody Address address){
        try{
            addressService.saveAddress(address);
            return new ResponseEntity<>(address, HttpStatus.CREATED);
            } catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    //Updating new Address via userId
    @PutMapping("/update/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable String id, @RequestBody Address updatedAddress){
        addressService.updateAddressValues(id,updatedAddress);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
    /*@PutMapping("/update/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable String id, @RequestBody Address updatedAddress){
        Address updateAddress = addressRepo.findById(id).orElse(null);
        updateAddress.setAddressLine1(updatedAddress.getAddressLine1());
        updateAddress.setAddressLine2((updatedAddress.getAddressLine2()));
        updateAddress.setCity(updatedAddress.getCity());
        updateAddress.setState(updatedAddress.getState());
        updateAddress.setCountry(updatedAddress.getCountry());
        updateAddress.setPostalCode(updatedAddress.getPostalCode());

        addressRepo.save(updateAddress);
        return ResponseEntity.ok(updateAddress);
    }*/

    @GetMapping("/address")
    public List<Address> getAddress(){
        return addressService.findAllAddress();
    }

    /*@GetMapping("/address")
    public List<Address> getAddress(){
        return addressRepo.findAll();
    }*/

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Address> deleteProduct(@PathVariable String id){
        addressService.deleteAddressById(id);
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }

    /*@DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id){
        addressRepo.deleteById(id);
        return "Deleted Successfully";
    }*/

    @GetMapping("/get/{userId}")
    public List<Address> getAddresses(@PathVariable String userId){
        return addressService.getAllAddress(userId);
    }
}