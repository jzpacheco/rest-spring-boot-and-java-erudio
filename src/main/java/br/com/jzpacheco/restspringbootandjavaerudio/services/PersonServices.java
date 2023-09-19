package br.com.jzpacheco.restspringbootandjavaerudio.services;

import br.com.jzpacheco.restspringbootandjavaerudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id){

        logger.info("Finding one person!");
        Person person  = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Jeff");
        person.setLastName("Pacheco");
        person.setAddress("Içara");
        person.setGender("Masculino");
        return person;
    }
    public List<Person> findAll(){

        logger.info("Finding all people!");
        List<Person> response = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            response.add(person);
        }

        return response;
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("First Name " + i);
        person.setLastName("Last Name" + i);
        person.setAddress("Some Address");
        person.setGender("Masculino");

        return person;
    }
}
