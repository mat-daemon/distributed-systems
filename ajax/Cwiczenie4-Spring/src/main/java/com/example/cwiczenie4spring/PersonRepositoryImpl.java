package com.example.cwiczenie4spring;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonRepositoryImpl implements PersonRepository {

    private List<Person> personList;

    public PersonRepositoryImpl(List<Person> personList) {
        this.personList = personList;
    }


    @Override
    public int getRepositorySize() {
        return personList.size();
    }

    @Override
    public List<Person> getAllPersons() {
        return personList;
    }


    @Override
    public List<Person> filterByName(String name) {
        return personList.stream()
                .filter(employee -> employee.name.equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> filterByAge(int age) {
        return personList.stream()
                .filter(employee -> employee.age == age)
                .collect(Collectors.toList());
    }

    @Override
    public Person getPerson(int id) throws PersonNotFoundEx {
        for (Person person : personList) {
            if (person.getId() == id) {
                return person;
            }
        }
        throw new PersonNotFoundEx(id);
    }

    @Override
    public Person updatePerson(Person person) throws PersonNotFoundEx {
        for (int i = 0; i < personList.size(); i++) {
            Person personFromList = personList.get(i);
            if (personFromList.getId() == person.getId()) {
                personList.set(i, person);
                return person;
            }
        }
        throw new PersonNotFoundEx(person.getId());
    }

    @Override
    public boolean deletePerson(int id) throws PersonNotFoundEx {

        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            if (person.getId() == id) {
                personList.remove(i);
                return true;
            }
        }

        throw new PersonNotFoundEx(id);
    }

    @Override
    public Person addPerson(CreatePerson person) throws BadRequestEx {
        if (person == null || person.getName() == null || person.getEmail() == null) {
            throw new BadRequestEx();
        }

        int freeId = 1;

        for(Person p : personList){
            if(Objects.equals(p.getEmail(), person.email)) {
                throw new BadRequestEx();
            }
            if(p.getId() == freeId){
                freeId++;
            } else {
                break;
            }
        }
        Person p = new Person(freeId, person.name, person.age, person.email);
        personList.add(p);
        return p;
    }
}