package com.example.dstay.DataMaker;

import com.example.dstay.Entity.Element;
import com.example.dstay.Repository.CategoryRepository;
import com.example.dstay.Repository.ElementRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ElementMaker {
    @Autowired
    ElementRepository elementRepository;

    @Autowired
    private final CategoryRepository categoryRepository;

    public ElementMaker(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void CreateElement(){
        Element element = new Element();
        element.setName("Music production");
        element.setDescription("lorem");
        element.setSource("Source");
        element.setImage("Image");
        //element.setCategory(categoryRepository.findById(1L));
        //element.setAuthor();
        //elementRepository.save(element);
    }
}
