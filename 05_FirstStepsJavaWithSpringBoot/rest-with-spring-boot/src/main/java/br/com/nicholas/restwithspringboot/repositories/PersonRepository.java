package br.com.nicholas.restwithspringboot.repositories;

import br.com.nicholas.restwithspringboot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
