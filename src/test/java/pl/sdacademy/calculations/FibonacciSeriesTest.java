package pl.sdacademy.calculations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FibonacciSeriesTest {

    private final FibonacciSeries fibonacciSeries = new FibonacciSeries();

    /**
     * Testy jednostkowe - trzy podpunkty
     */

//    @Test
//    void shouldCalculateValueIndex1() {
//        final int index = 1;
//
//        final long result = fibonacciSeries.compute(index);
//
//        assertThat(result).isEqualTo(1);
//    }
//
//    @Test
//    void shouldCalculateValueIndex5() {
//        final int index = 5;
//
//        final long result = fibonacciSeries.compute(index);
//
//        assertThat(result).isEqualTo(5);
//    }
//
//    @Test
//    void shouldCalculateValueIndex15() {
//        final int index = 15;
//
//        final long result = fibonacciSeries.compute(index);
//
//        assertThat(result).isEqualTo(610);
//    }

    /**
     * To samo dla @ArgumentsSource
     */

    @ParameterizedTest
    @ArgumentsSource(FibonacciArgumentsProvider.class)
    void shouldCalculateValueIndex(final int index, final long expectedVallue) {
        final long result = fibonacciSeries.compute(index);

        assertThat(result).isEqualTo(expectedVallue);
    }

}