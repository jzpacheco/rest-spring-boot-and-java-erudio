package br.com.jzpacheco.restspringbootandjavaerudio.controllers;


import br.com.jzpacheco.restspringbootandjavaerudio.services.BookServices;
import br.com.jzpacheco.restspringbootandjavaerudio.util.MediaType;
import br.com.jzpacheco.restspringbootandjavaerudio.vo.v1.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {

    @Autowired
    BookServices services;

    @GetMapping(value ="/{id}")
    public BookVO findById(@PathVariable(value = "id") Integer id){
        return services.findById(id);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public List<BookVO> findAll() {
        return services.findAll();
    }
}
