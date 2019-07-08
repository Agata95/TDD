package pl.sdacademy.calculations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void shouldAddTwoNumbers() {
        // given - warunki początkowe
        final Calculator calculator = new Calculator();
        final double numA = 2;
        final double numB = 3;
        // dodajemy final, gdy jesteśmy pewni, że referencje się nie zmienią

        // when
        final double actualResult = calculator.add(numA, numB);

        // then
        assertEquals(5, actualResult); // oczekiwany wynik zawsze jest pierwszy
    }

    @Test
    void shouldSubtractTwoNumbers() {
        // given - warunki początkowe
        final Calculator calculator = new Calculator();
        final double numA = 2.5;
        final double numB = 3.8;

        // when
        final double actualResult = calculator.subtract(numA, numB);

        // then
        assertEquals(-1.3, actualResult, 0.1); // delta - margines błędu
    }

    @Test
    void shouldMultiplyTwoNumbers() {
        // given
        final Calculator calculator = new Calculator();
        final double numA = 2.1;
        final double numB = 3.0;

        // when
        final double actualResult = calculator.multiply(numA, numB);

        // then
        assertEquals(6.3, actualResult, 0.1);
    }


}