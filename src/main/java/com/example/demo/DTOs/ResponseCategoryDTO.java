package com.example.demo.DTOs;

import com.example.demo.Entity.Category;
import lombok.Data;

@Data
public class ResponseCategoryDTO {
    private String id;
    private String categoryName;
    private String categoryDescription;

    public ResponseCategoryDTO(Category category) {
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
        this.categoryDescription = category.getDescription();
    }
}
