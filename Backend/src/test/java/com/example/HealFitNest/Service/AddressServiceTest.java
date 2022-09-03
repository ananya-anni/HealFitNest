package com.example.HealFitNest.Service;

import com.example.HealFitNest.Model.Address;
import com.example.HealFitNest.Repository.AddressRepo;
import com.example.HealFitNest.Service.Implementation.AddressServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

public class AddressServiceTest {

    AddressRepo addressRepo = mock(AddressRepo.class);

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    AddressServiceImp addressServiceImp;

    @Test
    public void saveAddressTest(){
        Address address = new Address();
        address.setAddressId("AI1");
        address.setUserId("UI1");
        address.setAddressLine1("CyberHub");
        address.setAddressLine2("hhfs");
        address.setCity("Gurugram");
        address.setState("Haryana");
        address.setCountry("India");
        address.setPostalCode("122004");
        when(addressRepo.save(address)).thenReturn(address);
        addressServiceImp.saveAddress(address,address.getUserId());
        verify(addressRepo,times(1)).save(address);
//        assertEquals(address, addressServiceImp.saveAddress(address,"UI1"));
    }

    @Test
    public void getAddressByUserId(){
        List<Address> allAddress = new ArrayList<Address>();
        Address address = new Address();
        address.setAddressId("AI1");
        address.setUserId("UI5");
        address.setAddressLine1("CyberHub");
        address.setAddressLine2("asgs");
        address.setCity("Noida");
        address.setState("Haryana");
        address.setCountry("India");

        //Address address 1= new Address();
        //address1.setAddressId("AI1");
        //address1.setUserId("UI5");
        //address1.setAddressLine1("CyberHub");
        //address.setAddressLine2("asgs");
        //address.setCity("Noida");
        //address.setState("Haryana");
        //address.setCountry("India");

        allAddress.add(address);

        when(addressRepo.findByUserId("UI22")).thenReturn(allAddress);
        List<Address> getAddresses = addressServiceImp.getAllAddress("UI22");
        assertEquals("AI1",allAddress.get(0).getAddressId());
    }

    @Test
    public void getAllAddresses(){
        List<Address> allAddress = new ArrayList<Address>();
        Address address = new Address();
        address.setAddressId("AI1");
        address.setUserId("UI1");
        address.setAddressLine1("CyberHub");
        address.setAddressLine2("asgs");
        address.setCity("Gurugram");
        address.setState("Haryana");
        address.setCountry("India");
        address.setPostalCode("122004");

        Address address1 = new Address();
        address1.setAddressId("AI2");
        address1.setUserId("UI2");
        address1.setAddressLine1("Gallaria");
        address1.setAddressLine2("hsdf");
        address1.setCity("Gurugram");
        address1.setState("Haryana");
        address1.setCountry("India");
        address1.setPostalCode("122004");

        List<Address> getAllAddresses = new ArrayList<>();
        getAllAddresses.add(address);
        getAllAddresses.add(address1);

        when(addressRepo.findAll()).thenReturn(getAllAddresses);
        assertEquals(2,addressServiceImp.findAllAddress().size());

    }

    @Test
    public void deleteAddressById(){
        Address address = new Address();
        address.setAddressId("AI1");
        address.setUserId("UI1");
        address.setAddressLine1("CyberHub");
        address.setAddressLine2("asgs");
        address.setCity("Gurugram");
        address.setState("Haryana");
        address.setCountry("India");
        address.setPostalCode("122004");

        willDoNothing().given(addressRepo).deleteById(address.getAddressId());
        addressServiceImp.deleteAddressById(address.getAddressId());
        verify(addressRepo, times(1)).deleteById(address.getAddressId());
    }

}
