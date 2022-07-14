package com.example.HealFitNest.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "Category")

public class Category {

    @Id
    private int categoryId;
    private int subCategoryId;
    private String categoryName;
    private String subCategoryName;
    private String imageUrl;

}
