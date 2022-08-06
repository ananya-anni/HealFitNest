package com.example.HealFitNest.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Order")
public class Order {

    @Id
    private String orderId;
    private String userId;
    private String cartId;
    private String addressId;
    private Date date=new Date(System.currentTimeMillis());
    private Boolean status=false;
    private BigDecimal totalPrice;
}
