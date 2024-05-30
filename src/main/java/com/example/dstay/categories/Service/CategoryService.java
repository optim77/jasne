package com.example.dstay.categories.Service;

import com.example.dstay.categories.DTOs.CreateCategoryDTO;
import com.example.dstay.categories.Entity.Category;
import com.example.dstay.categories.Repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<HttpStatus> execCreateCategory(CreateCategoryDTO categoryDTO){
        try {
            Category category = new Category();
            category.setName(categoryDTO.getName());
            category.setNews_counter(0);
            category.setImage(categoryDTO.getImage());
            categoryRepository.save(category);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostConstruct
    public void createCategories(){
        List<String> categories = new LinkedList<>();
        categories.add("Nature");
        categories.add("Politics");
        categories.add("Tech");
        categories.add("Med");
        for (String cat : categories){
            Category category = new Category();
            category.setName(cat);
            categoryRepository.save(category);
        }


    }
}
