package com.example.cwiczenie4spring;

import java.util.List;

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
    public Person addPerson(Person person) throws BadRequestEx {
        if (person == null || person.getName() == null || person.getEmail() == null) {
            throw new BadRequestEx();
        }

        for(Person p : personList){
            if(p.getId() == person.getId()){
                throw new BadRequestEx();
            }
        }

        personList.add(person);
        return person;
    }
}