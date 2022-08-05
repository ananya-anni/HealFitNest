package com.example.HealFitNest.Service;

import com.example.HealFitNest.Model.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddress(String userId);
}