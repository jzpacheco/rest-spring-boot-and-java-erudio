package br.com.jzpacheco.restspringbootandjavaerudio.services;

import br.com.jzpacheco.restspringbootandjavaerudio.exceptions.ResourceNotFoundException;
import br.com.jzpacheco.restspringbootandjavaerudio.model.Person;
import br.com.jzpacheco.restspringbootandjavaerudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll(){
        logger.info("Finding all people!");
        return repository.findAll();
    }
    public Person findById(Long id){

        logger.info("Finding one person!");

        return  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found this id"));
    }

    public Person create(Person person){
        logger.info("Inserting person");
        return repository.save(person);
    }

    public Person update(Person person){
       Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No record found this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

       return repository.save(entity);
    }

    public void delete(Long id){
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found this id"));
        repository.delete(entity);
    }

}
