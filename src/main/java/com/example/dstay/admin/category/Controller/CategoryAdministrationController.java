package com.example.dstay.admin.category.Controller;


import com.example.dstay.admin.category.Service.CategoryAdministrationService;
import com.example.dstay.admin.category.DTO.CreateCategoryDTO;
import com.example.dstay.categories.Entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
@RestResource
public class CategoryAdministrationController {
    private final CategoryAdministrationService categoryAdministrationService;

    public CategoryAdministrationController(CategoryAdministrationService categoryAdministrationService) {
        this.categoryAdministrationService = categoryAdministrationService;
    }


    @PostMapping("/admin/category/create")
    public ResponseEntity<HttpStatus> createCategory(@RequestBody CreateCategoryDTO createCategoryDTO){
        return categoryAdministrationService.execCreateCategory(createCategoryDTO);
    }

    @PostMapping("/admin/category/update")
    public ResponseEntity<HttpStatus> updateCategory(@RequestBody CreateCategoryDTO createCategoryDTO){
        return categoryAdministrationService.execUpdateCategory(createCategoryDTO);
    }

    @PostMapping("/admin/category/delete")
    ResponseEntity<HttpStatus> deleteCategory(@RequestBody CreateCategoryDTO createCategoryDTO){
        return  categoryAdministrationService.execDeleteCategory(createCategoryDTO);
    }
}
