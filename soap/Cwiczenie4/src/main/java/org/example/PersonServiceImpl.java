package org.example;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;
import java.util.Random;

@WebService(serviceName = "PersonService", endpointInterface = "org.example.PersonService")
public class PersonServiceImpl implements PersonService {
    private final PersonRepository dataRepository = new PersonRepositoryImpl();

    @WebMethod
    public Person getPerson(int id) throws PersonNotFoundEx {
        System.out.println("...called getPerson id=" + id);
        return dataRepository.getPerson(id);
    }

    @WebMethod
    public List<Person> getAllPersons() {
        System.out.println("...called getAllPersons");
        try {
            Random rand = new Random();
            boolean randomBool = rand.nextDouble() < 0.5;
            if (randomBool) {
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return dataRepository.getAllPersons();
    }

    @WebMethod
    public Person addPerson(int id, String name, int age) throws PersonExistsEx {
        System.out.println("...called addPerson");
        return dataRepository.addPerson(id, name, age);
    }

    @WebMethod
    public boolean deletePerson(int id) throws PersonNotFoundEx {
        System.out.println("...called deletePerson");
        return dataRepository.deletePerson(id);
    }

    @Override
    @WebMethod
    public Person updatePerson(int id, String name, int age) throws PersonNotFoundEx {
        System.out.println("... called updatePerson id=" + id + " name=" + name + " age=" + age);
        return dataRepository.updatePerson(id, name, age);
    }
    @WebMethod
    public int countPersons() {
        System.out.println("...called deletePerson");
        return dataRepository.countPersons();
    }
}