package com.example.dstay.Controller;

import com.example.dstay.Entity.Element;
import com.example.dstay.Props.GlobalProps;
import com.example.dstay.Repository.ElementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping(produces = "application/json")
@EnableConfigurationProperties(GlobalProps.class)
public class ElementController {

    private final GlobalProps globalProps;


    private final ElementRepository elementRepository;

    public ElementController(ElementRepository elementRepository, GlobalProps globalProps) {
        this.elementRepository = elementRepository;
        this.globalProps = globalProps;
    }

    @GetMapping(value = "/element/{category_id}/all")
    public List<Element> getAllByCategory(@PathVariable Long category_id){
        Pageable pageable = (Pageable) PageRequest.of(0, globalProps.getElementsSize());
        PageRequest page = PageRequest.of(0,12, Sort.by("createdAt").descending());
        return elementRepository.findByCategoryId(category_id);
    }

    @PostMapping(value = "/element", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Element saveElement(@RequestBody Element element){
        return elementRepository.save(element);
    }

    @GetMapping(value = "/element/{element_id}")
    public ResponseEntity<Element> getElement(@PathVariable Long element_id) throws Exception {
        Optional<Element> element = elementRepository.findById(element_id);
        if(element.isPresent()){
            return new ResponseEntity<>(element.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PatchMapping(value = "/element/{element_id}", consumes = "application/json")
    public ResponseEntity<Element> patchElement(@PathVariable Long element_id, @RequestBody Element sendElement){
        Optional<Element> repositoryElement = elementRepository.findById(element_id);
        if (repositoryElement.isPresent()){
            if(sendElement.getName() != null){
                repositoryElement.get().setName(sendElement.getName());
            }
            if (sendElement.getSource() != null){
                repositoryElement.get().setSource(sendElement.getSource());
            }
            if (sendElement.getImage() != null){
                repositoryElement.get().setImage(sendElement.getImage());
            }
            if (sendElement.getDescription() != null){
                repositoryElement.get().setDescription(sendElement.getDescription());
            }
            if (sendElement.getCategory() != null){
                repositoryElement.get().setCategory(sendElement.getCategory());
            }
            return new ResponseEntity<>(repositoryElement.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/element/{element_id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteElement(@PathVariable Long element_id){
        try{
            elementRepository.deleteById(element_id);
        }catch (EmptyResultDataAccessException ignored){

        }
    }
}
