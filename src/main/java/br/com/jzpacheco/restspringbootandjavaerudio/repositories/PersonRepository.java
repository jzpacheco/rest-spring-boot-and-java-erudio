package br.com.jzpacheco.restspringbootandjavaerudio.repositories;

import br.com.jzpacheco.restspringbootandjavaerudio.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
