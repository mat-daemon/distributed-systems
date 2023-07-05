package org.example;

import com.sun.xml.ws.client.BindingProviderProperties;
import jakarta.xml.ws.BindingProvider;

import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        MyData.info();

        System.out.println("Web Service Client just started...");

        URL addr = new URL("http://10.182.101.87:8081/personservice?wsdl");
        PersonService_Service pService = new PersonService_Service();
        PersonService pServiceProxy = pService.getPersonServiceImplPort();
        ((BindingProvider) pServiceProxy).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 1000);

        var scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 7) {
            System.out.println("Menu:");
            System.out.println("1. Get all persons");
            System.out.println("2. Get person with id");
            System.out.println("3. Get persons count");
            System.out.println("4. Create person");
            System.out.println("5. Delete person");
            System.out.println("6. Update person");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    try {
                        var persons = pServiceProxy.getAllPersons();
                        System.out.println("This is a list of people:");
                        for (Person p: persons) {
                            printPerson(p);
                        }
                    } catch (Exception e) {
                        System.out.println("Timeout!!:");
                    }

                    break;
                case 2:
                    int id = -1;
                    boolean validInput = false;
                    System.out.print("Enter the id of the person you want to retrieve: ");

                    while (!validInput) {
                        String input = scanner.next();
                        try {
                            id = Integer.parseInt(input);
                            validInput = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter an integer id.");
                        }
                    }

                    try {
                        var person = pServiceProxy.getPerson(id);
                        printPerson(person);
                    } catch (PersonNotFoundEx_Exception e) {
                        System.out.println("Person with this id " + id + " was not found!");
                    }

                    break;
                case 3:
                    int peopleCount = pServiceProxy.countPersons();
                    if (peopleCount == 0) {
                        System.out.println("There are no people: 0 in the database");
                    } else if ( peopleCount == 1) {
                        System.out.println("There is 1 human in the database");
                    } else {
                        System.out.println("There are " + peopleCount + " people in the database");
                    }
                    break;
                case 4:
                    System.out.println("We will add new person to database:");

                    System.out.println("Add person id");

                    int new_id = -1;
                    boolean validIDInput = false;
                    System.out.println("Enter the id of the person you want to add: ");

                    while (!validIDInput) {
                        String input = scanner.next();
                        try {
                            new_id = Integer.parseInt(input);
                            validIDInput = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter an integer id.");
                        }
                    }

                    System.out.println("Enter person first name: ");
                    var personName = scanner.next();


                    int age = -1;
                    boolean validAgeInput = false;
                    System.out.println("Enter the age of the person you want to add: ");

                    while (!validAgeInput) {
                        String input = scanner.next();
                        try {
                            age = Integer.parseInt(input);
                            validAgeInput = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter an integer age.");
                        }
                    }

                    try {
                        var person = pServiceProxy.addPerson(new_id, personName, age);
                        printPerson(person);
                    } catch (PersonExistsEx_Exception e) {
                        System.out.println("Error: This id is already taken!");
                    }


                    break;
                case 5:
                    int deleteId = -1;
                    boolean deleteValidInput = false;
                    System.out.println("Enter the id of the person you want to delete: ");

                    while (!deleteValidInput) {
                        String input = scanner.next();
                        try {
                            deleteId = Integer.parseInt(input);
                            deleteValidInput = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter an integer id.");
                        }
                    }

                    try {
                        var deleted = pServiceProxy.deletePerson(deleteId);
                        if (deleted) {
                            System.out.println("Person with this id " + deleteId + " was deleted!");
                        } else {
                            System.out.println("Person with this id " + deleteId + " was NOT deleted!");
                        }
                    } catch (PersonNotFoundEx_Exception e) {
                        System.out.println("Person with this id " + deleteId + " was not found!");
                    }


                    break;
                case 6:

                    System.out.println("We will update a person in database:");

                    System.out.println("Add person id");

                    int updateId = -1;
                    boolean validUpdateIDInput = false;
                    System.out.println("Enter the id of the person you want to update: ");

                    while (!validUpdateIDInput) {
                        String input = scanner.next();
                        try {
                            updateId = Integer.parseInt(input);
                            validUpdateIDInput = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter an integer id.");
                        }
                    }

                    System.out.println("Enter person first name: ");
                    var personUpdateName = scanner.next();


                    int updateAge = -1;
                    boolean validUpdateAgeInput = false;
                    System.out.println("Enter the age of the person you want to add: ");

                    while (!validUpdateAgeInput) {
                        String input = scanner.next();
                        try {
                            updateAge = Integer.parseInt(input);
                            validUpdateAgeInput = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter an integer age.");
                        }
                    }

                    try {
                        var person = pServiceProxy.updatePerson(updateId, personUpdateName, updateAge);
                        printPerson(person);
                    } catch (PersonNotFoundEx_Exception e) {
                        System.out.println("Person with this id " + updateId + " was not found!");
                    }


                    break;
                case 7:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void printPerson(Person person) {
        System.out.println("Person { id = " + person.id + ", name = " + person.firstName + ", age = " + person.age + " }");
    }

}