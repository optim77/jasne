package com.example.dstay.categories.Controller;

import com.example.dstay.categories.DTOs.CreateCategoryDTO;
import com.example.dstay.categories.Entity.Category;
import com.example.dstay.categories.Repository.CategoryRepository;
import com.example.dstay.categories.Service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/category/create")
    public ResponseEntity<HttpStatus> createCategory(@RequestBody CreateCategoryDTO createCategoryDTO){
        return categoryService.execCreateCategory(createCategoryDTO);
    }

    @GetMapping("/category/all")
    public List<Category> getCategory(){
        return categoryRepository.findAll();
    }
}
