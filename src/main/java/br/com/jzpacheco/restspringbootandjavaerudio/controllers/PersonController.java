package br.com.jzpacheco.restspringbootandjavaerudio.controllers;

import br.com.jzpacheco.restspringbootandjavaerudio.services.PersonServices;
import br.com.jzpacheco.restspringbootandjavaerudio.vo.v1.PersonVO;
import br.com.jzpacheco.restspringbootandjavaerudio.vo.v2.PersonVOV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonServices personServices;
    private static final AtomicLong counter = new AtomicLong();

    @GetMapping(value="/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO findById(
            @PathVariable(value = "id") Long id
    ) throws Exception{

        return personServices.findById(id);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVO> findAll() {
        return personServices.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO create(@RequestBody PersonVO person){
        return personServices.create(person);
    }

    @PostMapping(value = "/v2",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person){
        return personServices.createV2(person);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO update(@RequestBody PersonVO person){
        return personServices.update(person);
    }

    @DeleteMapping(value ="/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
         personServices.delete(id);
        return ResponseEntity.noContent().build();
    }


}
