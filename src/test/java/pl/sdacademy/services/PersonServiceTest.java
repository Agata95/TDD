package pl.sdacademy.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sdacademy.exceptions.PersonActionException;
import pl.sdacademy.user.Person;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    private PersonService personService;

    // mockuje obiekt z klasy, która posiada baze danych, których nie muszę znać do testowania
    @Mock
    private PersonRepository personRepository; // = Mockito.mock(PersonRepository.class)

    @BeforeEach
    void setUp() {
//        personRepository = new PersonRepository();
        personService = new PersonService(personRepository);
    }

    /**
     * Testy jednostkowe dla dwóch metod -> przypadki pozytywne i niegatywne
     * z wykorzystaniem Mockito
     */

    @Test
    void shouldGetPersonByExistingEmail() {
        final String email = "test1@gmail.com";
        // tworzymy własną listę jednoelementową aby, w razie gdyby baza danych przestała działać
        // mogli korzystać z 'atrapy' bazy danych
        when(personRepository.getPersonList()).thenReturn(Collections.singletonList(
                new Person("fn", "ln", email, 12)
        ));

        final Person foundPerson = personService.getByEmail(email);

        assertThat(foundPerson.getEmail())
                .isEqualTo(email);
        verify(personRepository).getPersonList();
    }

    // trzeba dostać się do środka pudełka Person poprzez metodę .get()
    // extracting jest mapowaniem, który pozwala dostać się do konkretnej metody dla obiektu typu email

    @Test
    void shouldFindByExistingEmail() {
        final String email = "test1@gmail.com";
        when(personRepository.getPersonList()).thenReturn(Collections.singletonList(
                new Person("fn", "ln", email, 12)
        ));

        final Optional<Person> foundPerson = personService.findByEmail(email);

        assertThat(foundPerson)
                .isPresent()
                .get()
                .extracting(Person::getEmail)
                .isEqualTo(email);
        verify(personRepository).getPersonList();
    }

    @Test
    void shouldNotFindByNonExistingEmail() {
        final String email = "jakisEmail@gmail.com";

        final Optional<Person> foundPerson = personService.findByEmail(email);

        assertThat(foundPerson)
                .isNotPresent();
    }

    /**
     * Testowanie wyjątków
     */

    @Test
    void shouldThrowExceptionWithGivenEmailDoesNotExist() {
        final String email = "nic@gmail.com";

        assertThatExceptionOfType(PersonActionException.class)
                .isThrownBy(() -> personService.getByEmail(email))
                .withMessage("Failed to get person by email")
                .withNoCause();
    }
}