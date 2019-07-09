package pl.sdacademy.calculations;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FibonacciSeriesTest {

    private final FibonacciSeries fibonacciSeries = new FibonacciSeries();

    @Test
    void shouldCalculateValueIndex1() {
        final int index = 1;

        final long result = fibonacciSeries.compute(index);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldCalculateValueIndex5() {
        final int index = 5;

        final long result = fibonacciSeries.compute(index);

        assertThat(result).isEqualTo(5);
    }

    @Test
    void shouldCalculateValueIndex15() {
        final int index = 15;

        final long result = fibonacciSeries.compute(index);

        assertThat(result).isEqualTo(610);
    }

}