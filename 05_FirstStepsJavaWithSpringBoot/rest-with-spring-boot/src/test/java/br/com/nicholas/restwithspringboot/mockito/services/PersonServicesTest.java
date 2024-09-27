package br.com.nicholas.restwithspringboot.mockito.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import br.com.nicholas.restwithspringboot.data.vo.v1.PersonVO;
import br.com.nicholas.restwithspringboot.exceptions.RequiredObjectIsNullException;
import br.com.nicholas.restwithspringboot.exceptions.ResourceNotFoundException;
import br.com.nicholas.restwithspringboot.mocks.MockPerson;
import br.com.nicholas.restwithspringboot.model.Person;
import br.com.nicholas.restwithspringboot.repositories.PersonRepository;
import br.com.nicholas.restwithspringboot.services.PersonServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.List;
import java.util.Optional;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices personServices;

    @Mock
    private PersonRepository repository;

    @BeforeEach
    void setUpMocks() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        var result = personServices.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        System.out.println(result.toString());
        assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
        assertEquals("Address Test1",result.getAddress());
        assertEquals("First Name Test1",result.getFirstName());
        assertEquals("Last Name Test1",result.getLastName());
        assertEquals("Female",result.getGender());
    }

    @Test
    void findAll() {
        List<Person> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);

        var people = personServices.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);

        assertNotNull(personOne);
        assertNotNull(personOne.getKey());
        assertNotNull(personOne.getLinks());

        assertTrue(personOne.toString().contains("links: [</person/1>;rel=\"self\"]"));
        assertEquals("Address Test1", personOne.getAddress());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals("Female", personOne.getGender());

        var personFour = people.get(4);

        assertNotNull(personFour);
        assertNotNull(personFour.getKey());
        assertNotNull(personFour.getLinks());

        assertTrue(personFour.toString().contains("links: [</person/4>;rel=\"self\"]"));
        assertEquals("Address Test4", personFour.getAddress());
        assertEquals("First Name Test4", personFour.getFirstName());
        assertEquals("Last Name Test4", personFour.getLastName());
        assertEquals("Male", personFour.getGender());

        var personSeven = people.get(7);

        assertNotNull(personSeven);
        assertNotNull(personSeven.getKey());
        assertNotNull(personSeven.getLinks());

        assertTrue(personSeven.toString().contains("links: [</person/7>;rel=\"self\"]"));
        assertEquals("Address Test7", personSeven.getAddress());
        assertEquals("First Name Test7", personSeven.getFirstName());
        assertEquals("Last Name Test7", personSeven.getLastName());
        assertEquals("Female", personSeven.getGender());
    }

    @Test
    void create() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        Person persisted =  input.mockEntity(1);
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        System.out.println("classe: " + entity.toString());
        when(repository.save(any(Person.class))).thenReturn(persisted);
       // when(repository.save(entity)).thenReturn(persisted);

        var result = personServices.create(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void createWithNullPerson() {

        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            personServices.create(null);
        });

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();


        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        Person persisted =  input.mockEntity(1);
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);


        System.out.println("classe: " + entity.toString());
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = personServices.update(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void updateWithNullPerson() {

        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            personServices.update(null);
        });

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();


        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        personServices.delete(1L);

        var result = personServices.findById(1L);
    }
}