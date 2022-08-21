package com.example.HealFitNest.Service;

import com.example.HealFitNest.Model.Address;
import com.example.HealFitNest.Repository.AddressRepo;
import com.example.HealFitNest.Service.Implementation.AddressServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AddressServiceTest {

        @MockBean
        AddressRepo addressRepo;

        @BeforeEach
        public void beforeEach(){
            MockitoAnnotations.initMocks(this);
        }

        @Autowired
        AddressServiceImp addressServiceImp;

        @Test
        public void saveAddressTest(){
            Address address = new Address();
            address.setUserId("UI1");
            address.setAddressLine1("CyberHub");
            address.setCity("Gurugram");
            address.setState("Haryana");
            address.setCountry("India");
            address.setPostalCode("122004");
            addressServiceImp.saveAddress(address,"UI1");
            verify(addressRepo,times(1)).save(address);
        }
}

