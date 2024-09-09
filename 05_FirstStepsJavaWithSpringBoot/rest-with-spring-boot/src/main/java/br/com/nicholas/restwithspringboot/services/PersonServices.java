package br.com.nicholas.restwithspringboot.services;

import br.com.nicholas.restwithspringboot.exceptions.ResourceNotFoundException;
import br.com.nicholas.restwithspringboot.model.Person;
import br.com.nicholas.restwithspringboot.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private PersonRepository repository;

    public Person findById(Long id) {
        logger.info("Finding person by id: " + id);
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("Example Address");
        person.setGender("Male");
        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
    }

    public List<Person> findAll(){
        return repository.findAll();
    }

    public Person mockPerson(int i) {
        Person person = new Person();
        person.setFirstName("Person " + i);
        person.setLastName("Lastname " + i);
        person.setAddress("Example Address " + i);
        person.setGender("Gender " + i);
        return person;
    }

    public Person create (Person person) {
        return repository.save(person);
    }

    public Person update (Person person) {
        logger.info("Updating person: " + person.getId());
        Person entity = repository.findById(person.getId()).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete (Long id) {
        logger.info("Deleting person: " + id);
        Person entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }
}
