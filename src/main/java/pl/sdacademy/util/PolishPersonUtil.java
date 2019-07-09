package pl.sdacademy.util;

public class PolishPersonUtil {

    private static final int ADULT_AGE = 18;

    public boolean isPolishFemaleName(final String name) {
        return name.length() > 2 && name.endsWith("a");
    }

    public boolean isPolishFemaleAdult(final String name, final int age) {
        return isPolishFemaleName(name) && age >= ADULT_AGE;
    }

    public boolean isTypicalPolishSurname(final String surname) {
        return surname.length() > 6 && surname.endsWith("ski");
    }

    public boolean isWomanWithTypicalPolishSurname(final String name, final String surname) {
        return isPolishFemaleName(name) && surname.endsWith("ska") && surname.length() > 5;
    }
}
