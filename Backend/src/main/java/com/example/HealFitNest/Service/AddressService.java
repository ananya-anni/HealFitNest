package com.example.HealFitNest.Service;

import com.example.HealFitNest.Model.Address;

import java.util.List;

public interface AddressService {
    public List<Address> getAllAddress(String userId);
    public Address saveAddress(Address address, String userId);
    public List<Address> findAllAddress();
    public void deleteAddressById(String id);
    public void updateAddressValues(String userId, String addressId, Address updatedAddress);

}