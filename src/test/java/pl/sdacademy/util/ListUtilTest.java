package pl.sdacademy.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListUtilTest {

    @Test
    void shouldDoubleValues() {
        final List<Integer> valuesToDouble = Arrays.asList(3, 5, 4);

        final List<Integer> doubleValues = ListUtil.doubleValues(valuesToDouble);

        // collect robi kopię listy i referencje są różne
        assertIterableEquals(Arrays.asList(6, 10, 8), doubleValues);
        assertNotEquals(valuesToDouble, doubleValues);
    }

    @Test
    void shouldDoubleInputValues() {
        final List<Integer> valuesToDouble = Arrays.asList(3, 5, 4);

        final List<Integer> doubleValues = ListUtil.doubleInputValue(valuesToDouble);

        // tutaj metoda nigdzie nie tworzy kopii listy, tylko nadpisuje nowe wartości do starej listy
        assertIterableEquals(Arrays.asList(6, 10, 8), doubleValues);
        assertSame(valuesToDouble, doubleValues);
    }

}