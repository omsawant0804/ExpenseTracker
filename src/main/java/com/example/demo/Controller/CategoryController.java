package com.example.demo.Controller;

import com.example.demo.DTOs.RequestCategoryDTO;
import com.example.demo.Service.CategoryService;
import com.example.demo.Utility.Response;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping("/add-category")
    public ResponseEntity<Response> addCategory(@RequestBody RequestCategoryDTO categoryRequest) {
        try {
            Response res = categoryService.addCategory(categoryRequest);
            if (res.isSuccess()) {
                return ResponseEntity.ok().body(res);
            }
            return ResponseEntity.badRequest().body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(false, e.toString(), null));
        }
    }

    @GetMapping("/get-all-category")
    public ResponseEntity<Response> getAllCategories() {
        try {
            Response res = categoryService.getAllCategories();
            if (res.isSuccess()) {
                return ResponseEntity.ok().body(res);
            }
            return ResponseEntity.badRequest().body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(false, e.toString(), null));
        }
    }
}

