package br.com.jzpacheco.restspringbootandjavaerudio.services;

import br.com.jzpacheco.restspringbootandjavaerudio.controllers.BookController;
import br.com.jzpacheco.restspringbootandjavaerudio.exceptions.RequiredObjectIsNullException;
import br.com.jzpacheco.restspringbootandjavaerudio.exceptions.ResourceNotFoundException;
import br.com.jzpacheco.restspringbootandjavaerudio.mapper.DozerMapper;
import br.com.jzpacheco.restspringbootandjavaerudio.model.Book;
import br.com.jzpacheco.restspringbootandjavaerudio.repositories.BookRepository;
import br.com.jzpacheco.restspringbootandjavaerudio.vo.v1.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    private Logger logger = Logger.getLogger(BookRepository.class.getName());

    @Autowired
    private BookRepository repository;

    public BookVO findById(int id){
        logger.info("Finding one book!");

        BookVO vo = DozerMapper.parseObject(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found")),BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());

        return vo;
    }

    public List<BookVO> findAll(){
        logger.info("Finding All books");

        List<BookVO> books = new ArrayList<>();

        for (BookVO book:
                DozerMapper.parseListObject(repository.findAll(), BookVO.class)) {
            book.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
            books.add(book);
        }

        BookVO findAllLink = new BookVO();
        findAllLink.add(linkTo(methodOn(BookController.class).findAll()).withSelfRel());
        books.add(findAllLink);
        return books;
    }

    public BookVO create(BookVO book){

        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Inserting book");
        Book entity = DozerMapper.parseObject(book, Book.class);

        BookVO vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public BookVO update(BookVO book){
        if (book == null) throw new RequiredObjectIsNullException();

        Book entity = repository.findById(book.getKey()).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));

        entity.setAuthor(book.getAuthor());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        entity.setLaunch_date(book.getLaunch_date());

        BookVO vo = DozerMapper.parseObject(repository.save(entity),BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(int id){
        repository.delete(repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource Not Found")));
    }

}
