package com.example.dstay.Controller;

import com.example.dstay.Entity.Element;
import com.example.dstay.Repository.ElementRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import org.xml.sax.EntityResolver;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ElementController {

    private final ElementRepository elementRepository;

    public ElementController(ElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }

    @GetMapping(value = "/category/{category_id}/all")
    public List<Element> getAllByCategory(@PathVariable Long category_id){
        return elementRepository.findByCategoryId(category_id);
    }

    @PostMapping("/category/save")
    public Element saveElement(@RequestBody Element element){
        return elementRepository.save(element);
    }

    @GetMapping(value = "/element/{element_id}")
    public Element getElement(@PathVariable Long element_id) throws Exception {
        return elementRepository.findById(element_id).orElseThrow(Exception::new);
    }

    @PostMapping(value = "element/add")
    public Element addNewElement(@ModelAttribute("element") Element element){
        return elementRepository.save(element);

    }
}
