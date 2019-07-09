package pl.sdacademy.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IntegerUtilsTest {

    private IntegerUtils integerUtils;

    @BeforeEach
    void setUp() {
        integerUtils = new IntegerUtils();
    }

    @Test
    void shouldFilterNumberWithWhenFilterApply() {
        final int toFilter = 12345;
        final List<Integer> filters = Arrays.asList(2, 4);

        final List<Integer> filtered = integerUtils.filter(toFilter, filters);

        // sposób pierwszy :
//        assertFalse(filtered.isEmpty());
//        assertEquals(3,filtered.size());
//        assertTrue(filtered.contains(1));
//        assertTrue(filtered.contains(2));
//        assertTrue(filtered.contains(3));
        // gdy występuje błąd, to nie wiadomo z którego assert'u

        // sposób drugi - lepszy :
        assertAll(
                () -> assertFalse(filtered.isEmpty()),
                () -> assertEquals(3, filtered.size()),
                () -> assertTrue(filtered.contains(1)),
                () -> assertTrue(filtered.contains(3)),
                () -> assertTrue(filtered.contains(5))
        );
    }

    /**
     * Wykorzystaj assertJ aby sprawdzić czy otrzymana lista z metody
     * filterDigitsGreaterThan jest niepusta, ma odpowiednią długość i posiada
     * oczekiwane elementy
     */

    @Test
    void shouldFilterDigitGreaterThanWhenFilterApply() {
        final int toFiltr = 12435;
        final int filtr = 3;

        final List<Integer> filterValues = integerUtils.filterDigitsGreaterThan(toFiltr, filtr);

        assertThat(filterValues)
                .isNotEmpty()
                .hasSize(2)
                .contains(4, 5);
    }

    /**
     * Wykorzystaj assertJ aby sprawdzić czy otrzymana lista z metody
     * getLastEvenDigit zwraca poprawną wartość, upewnij się że wartość istnieje
     * zanim ją odczytasz
     */

    @Test
    void shouldGetLastEvenDigitExistsInInputNumber() {
        final int input = 12435;

        final Optional<Integer> actualLastEvenDigit = integerUtils.getLastEvenDigit(input);

        assertThat(actualLastEvenDigit)
                .isPresent()
                .contains(4);
    }

    @Test
    void shouldNotFindAnyEvenDigitInUnvenNumber(){
        final int input = 1357;

        final Optional<Integer> actualLastEvenDigit = integerUtils.getLastEvenDigit(input);

        assertThat(actualLastEvenDigit)
                .isNotPresent();
    }
}