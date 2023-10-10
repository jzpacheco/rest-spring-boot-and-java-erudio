package br.com.jzpacheco.restspringbootandjavaerudio.unittests.mapper.mocks;

import br.com.jzpacheco.restspringbootandjavaerudio.model.Book;
import br.com.jzpacheco.restspringbootandjavaerudio.model.Person;
import br.com.jzpacheco.restspringbootandjavaerudio.vo.v1.BookVO;
import br.com.jzpacheco.restspringbootandjavaerudio.vo.v1.PersonVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {


    public Book mockEntity() {
        return mockEntity(0);
    }
    
    public BookVO mockVO() {
        return mockVO(0);
    }
    
    public List<Book> mockEntityList() {
        List<Book> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }
    
    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setAuthor("Author Test" + number);
        book.setPrice(number.doubleValue());
        book.setLaunch_date(new Date(2023,10,07));
        book.setId(number);
        book.setTitle("Title test" + number);
        return book;
    }

    public BookVO mockVO(Integer number) {
        BookVO book = new BookVO();
        book.setAuthor("Author Test" + number);
        book.setPrice(number.doubleValue());
        book.setLaunch_date(new Date(2023,10,07));
        book.setKey(number);
        book.setTitle("Title test" + number);
        return book;
    }

}
