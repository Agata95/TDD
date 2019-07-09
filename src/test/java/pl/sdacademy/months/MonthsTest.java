package pl.sdacademy.months;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Month;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MonthsTest {

    /**
     * Przypadki pozytywne - @MethodSource
     */

    @ParameterizedTest
    @MethodSource("getMonthsWith31Days")
    void shouldHave31Days(final Month month) {
        final boolean has31days = Months.has31days(month);

        assertThat(has31days).isTrue();
    }

    static Stream<Arguments> getMonthsWith31Days() {
        return Stream.of(
                Arguments.of(Month.JANUARY),
                Arguments.of(Month.MARCH),
                Arguments.of(Month.MAY),
                Arguments.of(Month.JULY),
                Arguments.of(Month.AUGUST),
                Arguments.of(Month.SEPTEMBER),
                Arguments.of(Month.DECEMBER)
        );
    }

    /**
     * Przypadki negatywne - @EnumSource
     * names -> wybieramy konkretne miesiące z klasy enum MONTH
     */

    @ParameterizedTest
    @EnumSource(value = Month.class, names = {"FEBRUARY", "APRIL", "JUNE", "OCTOBER", "NOVEMBER"})
    void shouldHave31DaysWhenIsFalse(final Month month) {
        final boolean has31days = Months.has31days(month);

        assertThat(has31days).isFalse();
    }

    /**
     * Przypadki pozytywne (innym sposobem) - @EnumSource
     * wykorzystując 'mode'
     * klucze w adnotacji mogą być zapisane w dowolnej kolejności
     */

    @ParameterizedTest
    @EnumSource(names = {"FEBRUARY", "APRIL", "JUNE", "OCTOBER", "NOVEMBER"}, value = Month.class,
            mode = EnumSource.Mode.EXCLUDE)
    void shouldHave31DaysWhenIsTrue(final Month month) {
        final boolean has31days = Months.has31days(month);

        assertThat(has31days).isTrue();
    }

}