package pl.sdacademy.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.exceptions.PersonActionException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {

    private PersonService personService;
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        personRepository = new PersonRepository();
        personService = new PersonService(personRepository);
    }

    @Test
    void shouldThrowExceptionWithGivenEmailDoesNotExist() {
        final String email = "nic@gmail.com";

        assertThatExceptionOfType(PersonActionException.class)
                .isThrownBy(() -> personService.getByEmail(email))
                .withMessage("Failed to get person by email")
                .withNoCause();
    }
}