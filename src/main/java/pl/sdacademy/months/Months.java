package pl.sdacademy.months;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

public class Months {
    private static final Map<Month, Integer> MONTH_TO_NUMBER_OF_DAYS = new HashMap<>();

    // inicjalizacja statyczna klasy, jest odpalana w momencie gdy klasa jest odpalana do jvm
    static {
        MONTH_TO_NUMBER_OF_DAYS.put(Month.JANUARY, 31);
        MONTH_TO_NUMBER_OF_DAYS.put(Month.FEBRUARY, 28);
        MONTH_TO_NUMBER_OF_DAYS.put(Month.MARCH, 31);
        MONTH_TO_NUMBER_OF_DAYS.put(Month.APRIL, 30);
        MONTH_TO_NUMBER_OF_DAYS.put(Month.MAY, 31);
        MONTH_TO_NUMBER_OF_DAYS.put(Month.JUNE, 30);
        MONTH_TO_NUMBER_OF_DAYS.put(Month.JULY, 31);
        MONTH_TO_NUMBER_OF_DAYS.put(Month.AUGUST, 31);
        MONTH_TO_NUMBER_OF_DAYS.put(Month.OCTOBER, 30);
        MONTH_TO_NUMBER_OF_DAYS.put(Month.SEPTEMBER, 31);
        MONTH_TO_NUMBER_OF_DAYS.put(Month.NOVEMBER, 30);
        MONTH_TO_NUMBER_OF_DAYS.put(Month.DECEMBER, 31);
    }

    public static boolean has31days(final Month month) {
        return MONTH_TO_NUMBER_OF_DAYS.get(month) == 31;
    }
}
