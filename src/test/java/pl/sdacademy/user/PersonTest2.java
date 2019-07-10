package pl.sdacademy.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.exceptions.PersonUpdateFailedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest2 {
    private Person person;

    @BeforeEach
    void setUpTestCase() {
        person = new Person();
    }

    /**
     * Testowanie wyjątków
     */

    @Test
    void shouldThrowExceptionSetPersonDetailsWithAgeIsNoNPositive() {
        final Integer age = -4;

        assertThatExceptionOfType(PersonUpdateFailedException.class)
                .isThrownBy(() -> person.setPersonDetails("", age))
                .withMessage("Age has to be positive")
                .withNoCause();
    }

    @Test
    void shouldThrowExceptionSetPersonDetailsWithZero() {
        final Integer age = 0;

        final PersonUpdateFailedException exp = assertThrows(PersonUpdateFailedException.class,
                () -> person.setPersonDetails("", age));

        assertThat(exp)
                .hasMessage("Age has to be positive")
                .hasNoCause();
    }

    @Test
    void shouldThrowExceptionSetPersonDetailsWithNull() {
        final Integer age = null;

        final PersonUpdateFailedException exp = assertThrows(PersonUpdateFailedException.class,
                () -> person.setPersonDetails("", age));

        assertThat(exp)
                .hasMessage("Age has to be positive")
                .hasNoCause();
    }
}