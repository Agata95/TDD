package pl.sdacademy.database;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseStoreTest {

    private static DatabaseConnection databaseConnection;
    private DatabaseStore databaseStore;

    @BeforeAll
    static void setUpTestCase() {
        databaseConnection = new DatabaseConnection();
        databaseConnection.open();
    }

    @AfterAll
    static void tearDownTestCase() {
        databaseConnection.close();
    }

    @BeforeEach
    void setUp() {
        databaseStore = new DatabaseStore(databaseConnection);
        System.out.println("Starting next test");
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

    // w domu:
    // test dla clean - gdy w bazie jest coś
    // removeData - usunąć nieistniejący element
    // removeData - bez argumentów
    // addData - bez argumentów
}