package com.example.HealFitNest.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HealFitNest.Model.OrderLine;
import com.example.HealFitNest.Service.OrderLineService;

@RestController
@RequestMapping("/api/v8")
public class OrderLineController {
    @Autowired
    private OrderLineService orderLineService;

    /*@PostMapping("/addItemsToOrderLine")
    public String addItemsToOrderLine(@RequestBody OrderLine orderLine){
        orderLineService.saveItemstoOrderLine(orderLine);
        return "items added successfully to the order line";
    }*/
}


