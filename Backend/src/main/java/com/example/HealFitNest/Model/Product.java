package com.example.HealFitNest.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "Product")



public class Product {

    @Id
    private int productId;
    private String productName;
    private String category;
    private String SubCategory;
    private int quantity;
    private String decription;
    private int cost;
}
