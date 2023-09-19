package br.com.jzpacheco.restspringbootandjavaerudio;

import br.com.jzpacheco.restspringbootandjavaerudio.exceptions.UnsupportedMathOperationException;
import br.com.jzpacheco.restspringbootandjavaerudio.model.Person;
import br.com.jzpacheco.restspringbootandjavaerudio.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonServices personServices;
    private static final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/{id}",
                    method= RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(
            @PathVariable(value = "id") String id
    ) throws Exception{

        return personServices.findById(id);
    }
    @RequestMapping(method= RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll() {
        return personServices.findAll();
    }

}
