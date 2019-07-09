package pl.sdacademy.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PolishPersonUtilTest2 {

    private PolishPersonUtil polishPersonUtil;

    @BeforeEach
    void setUp() {
        polishPersonUtil = new PolishPersonUtil();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Ula, Andrzejewska",
            "Ola, Wolska",
            "Kasia, Wiśniewska"
    })
    void shouldBeTypicalPolishWomanName(final String name, final String surname) {
        final boolean actualValue = polishPersonUtil.isWomanWithTypicalPolishSurname(name, surname);

        assertThat(actualValue).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "A, Andrzejewska",
            "Kate, Wolska",
            "Kasia, Smi",
            "Kasia, Rodriguez"
    })
    void shouldBeTypicalPolishWomanNameWhenIsFalse(final String name, final String surname){
        final boolean actualValue = polishPersonUtil.isWomanWithTypicalPolishSurname(name, surname);

        assertThat(actualValue).isFalse();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/javagda25/data.csv")
    void shouldBeTypicalPolishWomanNameWhenIsFalse2(final String name, final String surname){
        final boolean actualValue = polishPersonUtil.isWomanWithTypicalPolishSurname(name, surname);

        assertThat(actualValue).isFalse();
    }

}