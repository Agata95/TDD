package pl.sdacademy.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        assertAll(
                () -> assertFalse(filtered.isEmpty()),
                () -> assertEquals(3, filtered.size()),
                () -> assertTrue(filtered.contains(1)),
                () -> assertTrue(filtered.contains(2)),
                () -> assertTrue(filtered.contains(3))
        );

    }

}