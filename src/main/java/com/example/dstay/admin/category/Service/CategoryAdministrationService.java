package com.example.dstay.admin.category.Service;


import com.example.dstay.admin.AdminUtils;
import com.example.dstay.admin.category.DTO.CreateCategoryDTO;
import com.example.dstay.categories.Entity.Category;
import com.example.dstay.categories.Repository.CategoryRepository;
import com.example.dstay.main.Repository.UserRepository;
import com.example.dstay.news.Repository.NewsRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryAdministrationService {

    private final CategoryRepository categoryRepository;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    public CategoryAdministrationService(CategoryRepository categoryRepository, NewsRepository newsRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }


    public ResponseEntity<HttpStatus> execCreateCategory(CreateCategoryDTO categoryDTO){
        if(AdminUtils.adminRoleCheck(categoryDTO.getToken(), userRepository)){
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
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    public ResponseEntity<HttpStatus> execUpdateCategory(CreateCategoryDTO createCategoryDTO){
        if(AdminUtils.adminRoleCheck(createCategoryDTO.getToken(), userRepository)){
            try{
                Optional<Category> category = categoryRepository.findById(createCategoryDTO.getId());
                if (category.isPresent()){
                    if(createCategoryDTO.getName() != null){
                        category.get().setName(createCategoryDTO.getName());
                    }
                    if (createCategoryDTO.getDescription() != null){
                        category.get().setDescription(createCategoryDTO.getDescription());
                    }
                    if (createCategoryDTO.getImage() != null){
                        category.get().setImage(createCategoryDTO.getImage());
                    }
                    categoryRepository.save(category.get());
                }
                return ResponseEntity.ok().build();
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    public ResponseEntity<HttpStatus> execDeleteCategory(CreateCategoryDTO createCategoryDTO){
        if(AdminUtils.adminRoleCheck(createCategoryDTO.getToken(), userRepository)){
            newsRepository.deleteAllByCategoryId(createCategoryDTO.getId());
            categoryRepository.deleteById(createCategoryDTO.getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
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
            category.setNews_counter(0);
            categoryRepository.save(category);
        }
    }
}
