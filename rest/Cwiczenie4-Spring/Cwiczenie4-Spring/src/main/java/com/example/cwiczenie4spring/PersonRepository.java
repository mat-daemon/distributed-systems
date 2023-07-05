package com.example.cwiczenie4spring;

import java.util.List;

public interface PersonRepository {
    int getRepositorySize();
    List<Person> getAllPersons();
    Person getPerson(int id) throws PersonNotFoundEx;
    Person updatePerson(Person person) throws PersonNotFoundEx;
    boolean deletePerson(int id) throws PersonNotFoundEx;
    Person addPerson(Person person) throws BadRequestEx;
}
