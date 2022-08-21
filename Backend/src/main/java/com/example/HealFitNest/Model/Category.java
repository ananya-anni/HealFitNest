package com.example.HealFitNest.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Category")

public class Category {

    @Id
    @NotBlank(message = "Category Id cannot be blank!")
    private String categoryId;

    private List<String> subCategoryId;

    @NotBlank(message = "Category Name cannot be blank!")
    private String categoryName;

    private List<String> subCategoryName;

    @NotBlank(message = "Category Image cannot be blank!")
    private String categoryImage;

}