package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MyData.info();

        PersonClient client = new PersonClient("http://10.182.101.87:8081");

//        System.out.println("Number of people: " + client.getPersonSize());
//
//        List<Person> people = client.getAllPeople();
//        for (Person person : people) {
//            System.out.println(person);
//        }
//
//        int id = 1;
//        System.out.println("Person with id " + id + ": " + client.getPersonById(id));
//
//        id = 2;
//        System.out.println("Deleting person with id " + id + ": " + client.deletePersonById(id));
//
//        Person newPerson = new Person(6, "Marta Nowak", 23, "marta.nowak@example.com");
//        System.out.println("Adding person: " + client.addPerson(newPerson));
//
//        id = 3;
//        Person updatedPerson = new Person(id, "Krzysztof Nowak", 43, "krzysztof.nowak@example.com");
//        System.out.println("Updating person with id " + id + ": " + client.updatePerson(id, updatedPerson));

        Scanner scanner = new Scanner(System.in);



        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1. Get number of people");
            System.out.println("2. Get list of people");
            System.out.println("3. Get person with id");
            System.out.println("4. Create person");
            System.out.println("5. Delete person with id");
            System.out.println("6. Update person with id");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Number of people: " + client.getPersonSize());
                    break;
                case 2:
                    List<PersonReceive> people = client.getAllPeople();
                    for (PersonReceive person : people) {
                        System.out.println(person);
                    }
                    break;
                case 3:
                    System.out.println("Enter person id:");
                    int id = scanner.nextInt();
                    System.out.println("Person with id " + id + ": " + client.getPersonById(id));
                    break;
                case 4:
                    System.out.println("Enter person id:");
                    int id2 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter person name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter person age:");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter person email:");
                    String email = scanner.nextLine();
                    Person newPerson = new Person(id2, name, age, email);

                    System.out.println("Adding person: " + client.addPerson(newPerson));
                    break;
                case 5:
                    System.out.println("Enter if of person to delete:");
                    int deleteId = scanner.nextInt();

                    System.out.println("Deleting person with id " + deleteId + ": " + client.deletePersonById(deleteId));
                    break;
                case 6:
                    System.out.println("Enter person id:");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter person name:");
                    String updatedName = scanner.nextLine();
                    System.out.println("Enter person age:");
                    int updatedAge = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter person email:");
                    String updatedEmail = scanner.nextLine();
                    Person updatedPerson = new Person(updateId, updatedName, updatedAge, updatedEmail);

                    System.out.println("Updated person "+ client.updatePerson(updateId, updatedPerson));
                    break;
                default:
                    System.exit(0);
            }
        }


    }

}