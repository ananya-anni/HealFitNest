package com.example.HealFitNest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.HealFitNest.Service.OrderLineService;

@RestController
@RequestMapping("/api/v9")
public class OrderLineController {
    @Autowired
    private OrderLineService orderlineService;

    //Add orderline
    @PostMapping("/addOrderLine/{orderId}")
    public ResponseEntity<?> addOrder( @PathVariable String orderId){
        orderlineService.addOrderLineByOrderId(orderId);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}

