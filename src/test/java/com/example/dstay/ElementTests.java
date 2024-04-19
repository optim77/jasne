package com.example.dstay;

import com.example.dstay.main.Entity.Category;
import com.example.dstay.main.Entity.Element;
import com.example.dstay.main.Entity.User;
import com.example.dstay.main.Repository.ElementRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElementTests {

    private final ElementRepository elementRepository;

    @Test
    void saveElement(){
        Element element = new Element();
        element.setSource("link");
        element.setName("name");
        element.setAuthor(new User());
        element.setDescription("lorem ipsum");
        element.setCategory(new Category());
        element.setId(1L);
        Element savedElement = elementRepository.save(element);
    }
}
