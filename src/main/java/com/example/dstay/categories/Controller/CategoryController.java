package com.example.dstay.categories.Controller;

import com.example.dstay.categories.Entity.Category;
import com.example.dstay.categories.Repository.CategoryRepository;
import com.example.dstay.categories.Service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(produces = "application/json")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/category/all")
    public List<Category> getCategory(){
        return categoryRepository.findAll();
    }
}
