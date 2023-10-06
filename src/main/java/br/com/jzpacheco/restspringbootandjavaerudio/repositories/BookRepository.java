package br.com.jzpacheco.restspringbootandjavaerudio.repositories;

import br.com.jzpacheco.restspringbootandjavaerudio.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
