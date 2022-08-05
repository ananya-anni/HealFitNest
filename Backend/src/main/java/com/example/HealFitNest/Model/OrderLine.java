package com.example.HealFitNest.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "OrderLine")

public class OrderLine {
    @Id
    private String orderLineId;
    private String orderId;
    private String itemId;
    private String itemQuantity;
}