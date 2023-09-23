package br.com.jzpacheco.restspringbootandjavaerudio.mapper.custom;

import br.com.jzpacheco.restspringbootandjavaerudio.model.Person;
import br.com.jzpacheco.restspringbootandjavaerudio.vo.v2.PersonVOV2;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {
    public static PersonVOV2 convertEntityToVO (Person person){

        PersonVOV2 vo = new PersonVOV2();

        vo.setId(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress(person.getAddress());
        vo.setGender(person.getGender());
        vo.setBirthDay(new Date());

        return vo;
    }

    public static Person convertVoToEntity(PersonVOV2 personVOV2){
        Person entity = new Person();
        entity.setId(personVOV2.getId());
        entity.setGender(personVOV2.getGender());
        entity.setAddress(personVOV2.getAddress());
        entity.setFirstName(personVOV2.getFirstName());
        entity.setLastName(personVOV2.getLastName());

        return entity;
    }
}
