package br.com.nicholas.restwithspringboot.controllers;

import br.com.nicholas.restwithspringboot.data.vo.v1.PersonVO;
import br.com.nicholas.restwithspringboot.services.PersonServices;
import br.com.nicholas.restwithspringboot.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;
    //private PersonServices service = new PersonServices();

    //Path Variable example
    // por padrão é GET
    @GetMapping(
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML, MediaType.APPLICATION_YML})
    public List<PersonVO> findAll (){
        return service.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVO findById (@PathVariable(value = "id")  Long id){
        return service.findById(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVO create (@RequestBody PersonVO person){
        return service.create(person);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVO update (@RequestBody PersonVO person){
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete (@RequestBody @PathVariable(value = "id")  Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
