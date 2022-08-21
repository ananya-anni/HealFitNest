package com.example.HealFitNest.Controller;

import com.example.HealFitNest.Model.Address;
import com.example.HealFitNest.Repository.AddressRepo;
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
    private AddressRepo addressRepo;
    @Autowired
    private AddressService addressService;
    //Saving user address
    @PostMapping("/addAddress/{userId}")
    public ResponseEntity<?> saveAddress(@RequestBody Address address,@PathVariable String userId){
        try{
            addressService.saveAddress(address,userId);
            return new ResponseEntity<>(address, HttpStatus.CREATED);
            } catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    //Update address of user
    @PutMapping("/update/{userId}/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable String userId,@PathVariable String id,@RequestBody Address updatedAddress){
        addressService.updateAddressValues(userId,id,updatedAddress);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
    //Get all address
    @GetMapping("/address")
    public List<Address> getAddress(){
        return addressService.findAllAddress();
    }

    //Delete address of particular user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Address> deleteProduct(@PathVariable String id){
        addressService.deleteAddressById(id);
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }

    //Get all address of particular user
    @GetMapping("/get/{userId}")
    public List<Address> getAddresses(@PathVariable String userId){
        return addressService.getAllAddress(userId);
    }
}