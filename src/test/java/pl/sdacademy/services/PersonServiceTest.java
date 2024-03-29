package pl.sdacademy.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
// w JUnit4: @RunWith(MockitoJunitRunner.class)
class PersonServiceTest {

    @InjectMocks    // na starcie do tej klasy są wstrzykiwane wszystkie mocki do konstruktora wszystkie zależności
    // np. -> PersonRepository personRepository, teraz @BeforeEach nie jest nam potrzebny
    private PersonService personService;

    // mockuje obiekt z klasy, która posiada baze danych, których nie muszę znać do testowania
    @Mock
    private PersonRepository personRepository; // = Mockito.mock(PersonRepository.class)

//    zakomentujemy to aby działał Mockito
//    @BeforeEach
//    void setUp() {
//        personRepository = new PersonRepository();
//        personService = new PersonService(personRepository);
//    }

    /**
     * Testy jednostkowe dla dwóch metod -> przypadki pozytywne i niegatywne (jeden z wyjątkiem)
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
        final String email = "jakisEmailKtoregoNieMaNaLiscie@gmail.com";
        // tworzę listę jakiegoś człowieka, który nie zawiera takiego emaila jak powyżej
        when(personRepository.getPersonList()).thenReturn(
                Collections.singletonList(
                        new Person("Jan", "Jankowski", "asd@gmail.com", 18))
        );

        final Optional<Person> foundPerson = personService.findByEmail(email);

        assertThat(foundPerson)
                .isNotPresent();
        verify(personRepository).getPersonList();
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