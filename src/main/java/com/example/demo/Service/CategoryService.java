package com.example.demo.Service;

import com.example.demo.DTOs.RequestCategoryDTO;
import com.example.demo.DTOs.ResponseCategoryDTO;
import com.example.demo.Entity.Category;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Utility.Response;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public Response<ResponseCategoryDTO> addCategory(RequestCategoryDTO categoryRequest) {
        try {
            if (categoryRequest.getCategoryName() == null || categoryRequest.getCategoryName().isBlank() || categoryRequest.getDescription() == null || categoryRequest.getDescription().isBlank()) {
                return new Response<>(false, "Category name or description cannot be empty", null);
            }

            Category category = new Category();
            category.setCategoryName(categoryRequest.getCategoryName());
            category.setDescription(categoryRequest.getDescription());

            Category savedCategory = categoryRepository.save(category);
            return new Response<>(true, "Category added successfully", List.of(new ResponseCategoryDTO(savedCategory)));

        } catch (Exception e) {
            return new Response<>(false, "Something went wrong while adding category", null);
        }
    }


    public Response<ResponseCategoryDTO> getAllCategories() {
        try {
            List<Category> categories = categoryRepository.findAll();
            if (categories.isEmpty()) {
                return new Response<>(true, "No categories found", null);
            }
            return new Response<>(true, "Categories fetched successfully",
                    categories.stream().map(ResponseCategoryDTO::new).toList());

        } catch (Exception e) {
            return new Response<>(false, "Something went wrong while fetching categories", null);
        }
    }
}

