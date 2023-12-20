package com.example.dstay.DataMaker;

import com.example.dstay.Entity.Category;
import com.example.dstay.Repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryMaker {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void createCategory() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Paint"));
        categories.add(new Category("Music"));
        categories.add(new Category("Graphic"));
        categoryRepository.saveAll(categories);
    }
}
