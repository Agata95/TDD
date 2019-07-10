package pl.sdacademy.database;

import org.junit.jupiter.api.*;
import pl.sdacademy.exceptions.DatabaseStoreException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseStoreTest {

    private static DatabaseConnection databaseConnection;
    private DatabaseStore databaseStore;

    @BeforeAll
    static void setUpTestCase() {
        databaseConnection = new DatabaseConnection();
    }

    @AfterAll
    static void tearDownTestCase() {
        databaseConnection.close();
    }

    @BeforeEach
    void setUp() {
        databaseStore = new DatabaseStore(databaseConnection);
        System.out.println("Starting next test");
        databaseConnection.open();
    }

    @AfterEach
    void tearDown() {
        databaseStore.clean();
    }

    @Test
    @DisplayName("Hey I am a test that has changed name")
    void shouldAddSingleData() {
        final String valueToInsert = "NoSiema";

        databaseStore.addData(valueToInsert);

        final List<String> actualValues = databaseStore.getData();
        assertTrue(actualValues.contains(valueToInsert));
    }

    @Test
    void shouldAddMultiplyData() {
        final String valueToInsertA = "valA";
        final String valueToInsertB = "valB";
        final String valueToInsertC = "valC";

        databaseStore.addData(valueToInsertA, valueToInsertB, valueToInsertC);

        final List<String> actualValues = databaseStore.getData();
        // tutaj należy pamiętać o kolejności - lista zachowuje kolejność
        // również sprawdzamy, czy w tej liście są 3 elementy, a nie więcej
        assertIterableEquals(Arrays.asList(valueToInsertA, valueToInsertB, valueToInsertC), actualValues);
    }

    @Test
    @Disabled
    void shouldRemoveMultiplyDataWhenDatabaseIsNotEmpty() {
        final String valueToInsertA = "valA";
        final String valueToInsertB = "valB";
        final String valueToInsertC = "valC";
        databaseStore.addData(valueToInsertA, valueToInsertB, valueToInsertC);

        databaseStore.removeData(valueToInsertA, valueToInsertB);

        final List<String> actualValues = databaseStore.getData();
        assertIterableEquals(Collections.singletonList(valueToInsertC), actualValues);
    }

    @Test
    void shouldCleanWhenDatabaseIsNotEmpty() {
        final String value = "someValue";
        databaseStore.addData(value);

        databaseStore.clean();

        final List<String> actualValues = databaseStore.getData();
        assertTrue(actualValues.isEmpty());
    }

    @Test
    void shouldRemoveValueWhenDatabaseIsEmpty() {
        databaseStore.removeData();
        final List<String> actualValues = databaseStore.getData();

        assertTrue(actualValues.isEmpty());
    }

    /**
     * Testowanie wyjątków - assertThatThrownBy
     */

    @Test
    void shouldThrowExceptionWhenAddingDataAndConnectionsIsClosed() {
        final String value = "someValue";
        databaseConnection.close();

        assertThatThrownBy(
                () -> databaseStore.addData(value))
                .hasMessage("Connection is not opened. Cannot add data");
    }

    /**
     * Testowanie wyjątków - assertThatExceptionOfType
     */

    @Test
    void shouldThrowExceptionWhenRemovingDataAndConnectionsIsClosed() {
        final String value = "someValue";
        databaseConnection.close();

        assertThatExceptionOfType(DatabaseStoreException.class)
                .isThrownBy(() -> databaseStore.removeData())
                .withMessage("Connection is not opened. Cannot remove data")
                .withNoCause();


    }
}