package com.example.HealFitNest.Service.Implementation;

import com.example.HealFitNest.Model.OrderLine;
import com.example.HealFitNest.Repository.OrderLineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderLineServiceImp {
    private OrderLineRepo orderLineRepo;

    @Autowired
    public void saveItemstoOrderLine(OrderLine orderLine){
        orderLineRepo.save(orderLine);
    }

}
