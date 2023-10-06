package br.com.jzpacheco.restspringbootandjavaerudio.services;

import br.com.jzpacheco.restspringbootandjavaerudio.controllers.BookController;
import br.com.jzpacheco.restspringbootandjavaerudio.exceptions.ResourceNotFoundException;
import br.com.jzpacheco.restspringbootandjavaerudio.mapper.DozerMapper;
import br.com.jzpacheco.restspringbootandjavaerudio.model.Book;
import br.com.jzpacheco.restspringbootandjavaerudio.repositories.BookRepository;
import br.com.jzpacheco.restspringbootandjavaerudio.vo.v1.BookVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class BookServices {

    private Logger logger = Logger.getLogger(BookRepository.class.getName());

    @Autowired
    private BookRepository repository;

    public BookVO findById(int id){
        logger.info("Finding one book!");

        BookVO vo = DozerMapper.parseObject(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found")),BookVO.class);
        //STOPED HERE, I SHOULD IMPLEMENT CONTROLLER METHODS.
        vo.add(linkTo(methodOn(BookController.class)))

        return vo;
    }
}
