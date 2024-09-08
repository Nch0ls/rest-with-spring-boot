package br.com.nicholas.restwithspringboot.services;

import br.com.nicholas.restwithspringboot.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public Person findById(String id) {
        logger.info("Finding person by id: " + id);
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("Example Address");
        person.setGender("Male");
        return person;
    }

    public List<Person> findAll(){
        logger.info("Finding all people");
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 8; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person mockPerson(int i) {
        logger.info("Finding person by id: " + i);
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person " + i);
        person.setLastName("Lastname " + i);
        person.setAddress("Example Address " + i);
        person.setGender("Gender " + i);
        return person;
    }

    public Person create (Person person) {
        logger.info("Creating person: " + person.getId());
        return person;
    }

    public Person update (Person person) {
        logger.info("Updating person: " + person.getId());
        return person;
    }

    public void delete (String id) {
        logger.info("Deleting person: " + id);
    }
}
