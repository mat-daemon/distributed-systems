package com.example.cwiczenie4spring;

import java.util.List;

public interface PersonRepository {
    int getRepositorySize();
    List<Person> getAllPersons();
    public List<Person> filterByName(String name);
    public List<Person> filterByAge(int age);
    Person getPerson(int id) throws PersonNotFoundEx;
    Person updatePerson(Person person) throws PersonNotFoundEx;
    boolean deletePerson(int id) throws PersonNotFoundEx;
    Person addPerson(CreatePerson person) throws BadRequestEx;
}
