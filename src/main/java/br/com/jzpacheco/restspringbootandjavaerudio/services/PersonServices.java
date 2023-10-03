package br.com.jzpacheco.restspringbootandjavaerudio.services;

import br.com.jzpacheco.restspringbootandjavaerudio.controllers.PersonController;
import br.com.jzpacheco.restspringbootandjavaerudio.exceptions.RequiredObjectIsNullException;
import br.com.jzpacheco.restspringbootandjavaerudio.exceptions.ResourceNotFoundException;
import br.com.jzpacheco.restspringbootandjavaerudio.mapper.DozerMapper;
import br.com.jzpacheco.restspringbootandjavaerudio.model.Person;
import br.com.jzpacheco.restspringbootandjavaerudio.repositories.PersonRepository;
import br.com.jzpacheco.restspringbootandjavaerudio.vo.v1.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonVO> findAll(){
        logger.info("Finding all people!");

        List<PersonVO> people = DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
        people.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));

        return people;
    }
    public PersonVO findById(Long id){
        logger.info("Finding one person!");

        Person person =   repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found this id"));

        PersonVO vo = DozerMapper.parseObject(person, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO personVO){

        if (personVO == null) throw new RequiredObjectIsNullException();

        logger.info("Inserting person");
        Person entity = DozerMapper.parseObject(personVO, Person.class);

        PersonVO vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO person){

        if (person == null) throw new RequiredObjectIsNullException();

        Person entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No record found this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonVO vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id){
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found this id"));
        repository.delete(entity);
    }

}
