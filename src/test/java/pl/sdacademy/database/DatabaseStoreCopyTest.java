package pl.sdacademy.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatabaseStoreCopyTest {

    @InjectMocks
    private DatabaseStoreCopy databaseStoreCopy;

    @Mock
    private DatabaseConnection databaseConnection;

    /**
     * Testowana sytuacja:
     * pozytywna bez wyrzucania wątków
     * na start baza jest zamknięta, musi przejść przez pierwszy if programu
     * następnie aby nie wyrzucić wątku baza się otwiera i nie przechodzi do drugiego if
     * dodaje do bazy wyznaczonego Stringa
     */

    @Test
    void shouldDatabaseConnectionAndAddData() {
        // za pierwszym razem musi być zamknięta (pierwszy if), a następnie otwarta (drugi if)
        final String value = "someValue";
        final int[] counter = {0};
        when(databaseConnection.isOpened()).thenAnswer(invocationOnMock -> {
            // jeżeli wołam pierwszy raz isOpen to zwrócimy false, jeśli drugi raz, to zwróci true
            // dodajemy counter, który musi być finalny -> dlatego tworzymy tablicę jednoelementową
            if (counter[0] == 0) {
                counter[0]++;
                return false;
            }
            return true;
        });
        doNothing()
                .when(databaseConnection)
                .open();

        databaseStoreCopy.addData(value);

        assertThat(databaseStoreCopy.getData()).containsExactlyInAnyOrder(value);
    }
}