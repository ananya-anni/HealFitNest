package com.example.HealFitNest.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

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
    private Boolean orderStatus=false;
    private BigDecimal totalPrice;

    public Order(String orderId, String userId, String cartId, String addressId, boolean orderStatus, int i) {
        this.orderId=orderId;
        this.userId=userId;
        this.cartId=cartId;
        this.addressId=addressId;
        this.orderStatus=orderStatus;
        this.totalPrice= BigDecimal.valueOf(i);
    }
}