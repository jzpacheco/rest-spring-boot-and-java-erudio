package br.com.jzpacheco.restspringbootandjavaerudio.unittests.mockito.services;

import br.com.jzpacheco.restspringbootandjavaerudio.exceptions.RequiredObjectIsNullException;
import br.com.jzpacheco.restspringbootandjavaerudio.model.Book;
import br.com.jzpacheco.restspringbootandjavaerudio.model.Person;
import br.com.jzpacheco.restspringbootandjavaerudio.repositories.BookRepository;
import br.com.jzpacheco.restspringbootandjavaerudio.services.BookServices;
import br.com.jzpacheco.restspringbootandjavaerudio.unittests.mapper.mocks.MockBook;
import br.com.jzpacheco.restspringbootandjavaerudio.vo.v1.BookVO;
import br.com.jzpacheco.restspringbootandjavaerudio.vo.v1.PersonVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

    MockBook input;
    @InjectMocks
    private BookServices service;

    @Mock
    BookRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Book book = input.mockEntity(1);
        book.setId(1);

        when(repository.findById(1)).thenReturn(Optional.of(book));
        var result = service.findById(1);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author Test1",result.getAuthor());
        assertEquals(1,result.getPrice());
        assertEquals(new Date(2023,10,07),result.getLaunch_date());
        assertEquals("Title test1",result.getTitle());

    }

    @Test
    void findAll() {
        List<Book> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);
        var books = service.findAll();

        assertNotNull(books);
        assertEquals(15,books.size());

        var bookOne = books.get(1);

        assertNotNull(bookOne);
        assertNotNull(bookOne.getKey());
        assertNotNull(bookOne.getLinks());

        assertTrue(bookOne.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author Test1",bookOne.getAuthor());
        assertEquals(1,bookOne.getPrice());
        assertEquals(new Date(2023,10,07),bookOne.getLaunch_date());
        assertEquals("Title test1",bookOne.getTitle());

        var bookFour = books.get(4);
        System.out.println(bookFour);
        assertNotNull(bookFour);
        assertNotNull(bookFour.getKey());
        assertNotNull(bookFour.getLinks());

        assertTrue(bookFour.toString().contains("links: [</api/book/v1/4>;rel=\"self\"]"));
        assertEquals("Author Test4",bookFour.getAuthor());
        assertEquals(4,bookFour.getPrice());
        assertEquals(new Date(2023,10,07),bookFour.getLaunch_date());
        assertEquals("Title test4",bookFour.getTitle());

        var bookSeven = books.get(7);

        assertNotNull(bookSeven);
        assertNotNull(bookSeven.getKey());
        assertNotNull(bookSeven.getLinks());

        assertTrue(bookSeven.toString().contains("links: [</api/book/v1/7>;rel=\"self\"]"));
        assertEquals("Author Test7",bookSeven.getAuthor());
        assertEquals(7,bookSeven.getPrice());
        assertEquals(new Date(2023,10,07),bookSeven.getLaunch_date());
        assertEquals("Title test7",bookSeven.getTitle());


    }

    @Test
    void create() {
        Book entity = input.mockEntity(1);
        Book persisted =  entity;
        persisted.setId(1);

        BookVO vo = input.mockVO(1);

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author Test1",result.getAuthor());
        assertEquals(1,result.getPrice());
        assertEquals(new Date(2023,10,07),result.getLaunch_date());
        assertEquals("Title test1",result.getTitle());

    }

    @Test
    void createWithNullPerson(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class, ()-> service.create(null));

        String exceptionMessage = exception.getMessage();
        String  predefinedMessage = "It's not allowes to persist null object!";

        assertTrue(exceptionMessage.contains(predefinedMessage));
    }
    @Test
    void update() {
        Book entity = input.mockEntity(1);
        Book persisted = entity;
        persisted.setId(1);

        BookVO vo = input.mockVO(1);

        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author Test1",result.getAuthor());
        assertEquals(1,result.getPrice());
        assertEquals(new Date(2023,10,07),result.getLaunch_date());
        assertEquals("Title test1",result.getTitle());
    }


    @Test
    void delete() {
    }
}