package br.com.nicholas.restwithspringboot.services;

import br.com.nicholas.restwithspringboot.controllers.PersonController;
import br.com.nicholas.restwithspringboot.data.vo.v1.PersonVO;
import br.com.nicholas.restwithspringboot.exceptions.RequiredObjectIsNullException;
import br.com.nicholas.restwithspringboot.exceptions.ResourceNotFoundException;
import br.com.nicholas.restwithspringboot.model.Person;
import br.com.nicholas.restwithspringboot.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        PersonVO vo = ModelToVoMapper(person);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return vo;
    }

    public List<PersonVO> findAll(){
        List<Person> personList = repository.findAll();
        List<PersonVO> personVOList = new ArrayList<>();

        for(Person person : personList){
            personVOList.add(ModelToVoMapper(person));
        }

        for(PersonVO personVO : personVOList){
            personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
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
        if (personVO == null) throw new RequiredObjectIsNullException();

        Person person = VOToModelMapper(personVO);

        PersonVO vo = ModelToVoMapper(repository.save(person));
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update (PersonVO personVO) {
        if(personVO == null) throw new RequiredObjectIsNullException();

        Person person = VOToModelMapper(personVO);
        logger.info("Updating person: " + person.getId());
        Person entity = repository.findById(person.getId()).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonVO vo = ModelToVoMapper(repository.save(entity));
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public void delete (Long id) {
        logger.info("Deleting person: " + id);
        Person entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

    public Person VOToModelMapper (PersonVO personVO) {
        return new Person(
                personVO.getKey(),
                personVO.getFirstName(),
                personVO.getLastName(),
                personVO.getAddress(),
                personVO.getGender()
        );
    }

    public PersonVO ModelToVoMapper (Person person) {
        return new PersonVO(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getAddress(),
                person.getGender()

        );
    }
}
