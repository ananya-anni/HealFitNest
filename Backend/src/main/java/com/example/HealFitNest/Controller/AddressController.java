package com.example.HealFitNest.Controller;

import com.example.HealFitNest.Model.Address;

import com.example.HealFitNest.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RequestMapping("/api/v3")
@RestController
public class AddressController{
    @Autowired
    private AddressService addressService;

    //Adding Address
    @PostMapping("/addAddress/{userId}")
    public ResponseEntity<?> saveAddress(@PathVariable String userId, @RequestBody Address address){
        try{
            addressService.saveAddress(address, userId);
            return new ResponseEntity<>(address, HttpStatus.CREATED);
        } catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    //Updating new Address via userId
    @PutMapping("/update/{userId}/{addressId}")
    public ResponseEntity<Address> updateAddress(@PathVariable String userId, @PathVariable String addressId, @RequestBody Address updatedAddress){
        addressService.updateAddressValues(userId, addressId, updatedAddress);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    // Get all the addresses
    @GetMapping("/address")
    public List<Address> getAddress(){
        return addressService.findAllAddress();
    }

    // Delete a particular address
    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity<Address> deleteProduct(@PathVariable String addressId){
        addressService.deleteAddressById(addressId);
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }

    // get addresses of particular user
    @GetMapping("/getAddresses/{userId}")
    public List<Address> getAddresses(@PathVariable String userId){
        return addressService.getAllAddress(userId);
    }
}