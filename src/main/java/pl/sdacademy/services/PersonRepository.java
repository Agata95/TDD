package pl.sdacademy.services;

import pl.sdacademy.user.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PersonRepository {

    private List<Person> personList = new ArrayList<>();

    public PersonRepository() {
        personList = Arrays.asList(
                Person.builder()
                        .age(22)
                        .email("test1@gmail.com")
                        .firstName("Andrzej")
                        .lastName("Nowak")
                        .build(),
                Person.builder()
                        .age(41)
                        .email("test2@gmail.com")
                        .firstName("Michal")
                        .lastName("Bocian")
                        .build(),
                Person.builder()
                        .age(27)
                        .email("asd@wp.pl")
                        .firstName("Karol")
                        .lastName("Iksinski")
                        .build(),
                Person.builder()
                        .age(31)
                        .email("qwerty@o2.pl")
                        .firstName("Robert")
                        .lastName("NieKubica")
                        .build());
    }

    public PersonRepository(final List<Person> persons) {
        this.personList = persons;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public List<Person> getPersonsWithPolishEmail() {
        return personList.stream()
                .filter(person -> person.getEmail()
                        .contains(".pl"))
                .collect(Collectors.toList());
    }
}

