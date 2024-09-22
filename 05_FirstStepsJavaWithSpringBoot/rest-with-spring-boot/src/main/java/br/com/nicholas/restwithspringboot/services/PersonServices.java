package br.com.nicholas.restwithspringboot.services;

import br.com.nicholas.restwithspringboot.data.vo.v1.PersonVO;
import br.com.nicholas.restwithspringboot.exceptions.ResourceNotFoundException;
import br.com.nicholas.restwithspringboot.model.Person;
import br.com.nicholas.restwithspringboot.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private PersonRepository repository;

    public PersonVO findById(Long id) {
        logger.info("Finding person by id: " + id);
        Person person = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
        return ModelToVoMapper(person);
    }

    public List<PersonVO> findAll(){
        List<Person> personList = repository.findAll();
        List<PersonVO> personVOList = new ArrayList<>();

        for(Person person : personList){
            personVOList.add(ModelToVoMapper(person));
        }
        return personVOList;
    }

    public Person mockPerson(int i) {
        Person person = new Person();
        person.setFirstName("Person " + i);
        person.setLastName("Lastname " + i);
        person.setAddress("Example Address " + i);
        person.setGender("Gender " + i);
        return person;
    }

    public PersonVO create (PersonVO personVO) {
        Person person = VOToModelMapper(personVO);

        return ModelToVoMapper(repository.save(person));
    }

    public PersonVO update (PersonVO personVO) {
        Person person = VOToModelMapper(personVO);
        logger.info("Updating person: " + person.getId());
        Person entity = repository.findById(person.getId()).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return ModelToVoMapper(repository.save(person));
    }

    public void delete (Long id) {
        logger.info("Deleting person: " + id);
        Person entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

    public Person VOToModelMapper (PersonVO personVO) {
        return new Person(
                personVO.firstName(),
                personVO.lastName(),
                personVO.gender(),
                personVO.address()
        );
    }

    public PersonVO ModelToVoMapper (Person person) {
        return new PersonVO(
                person.getFirstName(),
                person.getLastName(),
                person.getGender(),
                person.getAddress()
        );
    }
}
