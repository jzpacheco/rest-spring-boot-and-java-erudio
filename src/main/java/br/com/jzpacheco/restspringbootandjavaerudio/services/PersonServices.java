package br.com.jzpacheco.restspringbootandjavaerudio.services;

import br.com.jzpacheco.restspringbootandjavaerudio.exceptions.ResourceNotFoundException;
import br.com.jzpacheco.restspringbootandjavaerudio.mapper.DozerMapper;
import br.com.jzpacheco.restspringbootandjavaerudio.mapper.custom.PersonMapper;
import br.com.jzpacheco.restspringbootandjavaerudio.model.Person;
import br.com.jzpacheco.restspringbootandjavaerudio.repositories.PersonRepository;
import br.com.jzpacheco.restspringbootandjavaerudio.vo.v1.PersonVO;
import br.com.jzpacheco.restspringbootandjavaerudio.vo.v2.PersonVOV2;
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

    public List<PersonVO> findAll(){
        logger.info("Finding all people!");
        return DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
    }
    public PersonVO findById(Long id){
        logger.info("Finding one person!");

        Person person =   repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found this id"));

        return DozerMapper.parseObject(person, PersonVO.class);
    }

    public PersonVO create(PersonVO personVO){
        logger.info("Inserting person");
        Person entity = DozerMapper.parseObject(personVO, Person.class);

        return DozerMapper.parseObject(repository.save(entity),PersonVO.class);
    }

    public PersonVOV2 createV2(PersonVOV2 personVO){
        logger.info("Inserting person with V2");
        Person entity = PersonMapper.convertVoToEntity(personVO);

        return PersonMapper.convertEntityToVO(repository.save(entity));
    }

    public PersonVO update(PersonVO person){
        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No record found this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

       return DozerMapper.parseObject(repository.save(entity),PersonVO.class);
    }

    public void delete(Long id){
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found this id"));
        repository.delete(entity);
    }

}
