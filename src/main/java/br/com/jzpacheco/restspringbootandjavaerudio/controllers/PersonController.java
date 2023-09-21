package br.com.jzpacheco.restspringbootandjavaerudio.controllers;

import br.com.jzpacheco.restspringbootandjavaerudio.model.Person;
import br.com.jzpacheco.restspringbootandjavaerudio.services.PersonServices;
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
    public Person findById(
            @PathVariable(value = "id") Long id
    ) throws Exception{

        return personServices.findById(id);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll() {
        return personServices.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person){
        return personServices.create(person);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@RequestBody Person person){
        return personServices.update(person);
    }

    @DeleteMapping(value ="/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
         personServices.delete(id);
        return ResponseEntity.noContent().build();
    }


}
