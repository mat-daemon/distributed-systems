package com.example.cwiczenie4spring;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Links;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PersonController {

    private PersonRepository dataRepo = new PersonRepositoryImpl(
            new ArrayList<>(){
                {
                    add(new Person(1, "Jan Kowalski", 35, "jan.kowalski@example.com"));
                    add(new Person(2, "Anna Nowak", 28, "anna.nowak@example.com"));
//                    add(new Person(3, "Krzysztof Wójcik", 42, "krzysztof.wojcik@example.com"));
//                    add(new Person(4, "Monika Lewandowska", 19, "monika.lewandowska@example.com"));
//                    add(new Person(5, "Piotr Szymański", 57, "piotr.szymanski@example.com"));
                }
            }
    );

    @GetMapping("/personsSize")
    public EntityModel<Map<String, Integer>> getPeopleSize() {
        try {
            System.out.println("...called size GET");
            Map<String, Integer> toReturn = Collections.singletonMap("size", dataRepo.getRepositorySize());
            return EntityModel.of(toReturn);

        } catch (BadRequestEx e) {
            System.out.println("...GET size Exception");
            throw e;
        }
    }


    @GetMapping("/persons")
    public CollectionModel<EntityModel<Person>> getAllPeople() {
        List<EntityModel<Person>> people = dataRepo.getAllPersons().stream()
                .map(this::getEntityForPerson)
                .collect(Collectors.toList());

        return CollectionModel.of(people);
    }

    @GetMapping("/persons/{id}")
    public EntityModel<Person> getPerson(@PathVariable int id) {

        try {
            System.out.println("...called GET");
            Person person = dataRepo.getPerson(id);
            return getEntityForPerson(person);
        } catch (PersonNotFoundEx e) {
            System.out.println("...GET Exception");
            throw e;
        }

    }

    @DeleteMapping("/persons/{id}")
    public EntityModel<Map<String, Boolean>> deletePerson(@PathVariable("id") int id) {

        try {
            System.out.println("...called DELETE");
            Map<String, Boolean> toReturn = Collections.singletonMap("deleted", dataRepo.deletePerson(id));
            return EntityModel.of(toReturn);
        } catch (PersonNotFoundEx e) {
            System.out.println("...DELETE Exception");
            throw e;
        }
    }

    @PostMapping("/persons")
    public EntityModel<Person> addPerson(@RequestBody Person newPerson) {
        try {
            System.out.println("...called POST");

            Person person = dataRepo.addPerson(newPerson);

            return getEntityForPerson(person);
        } catch (BadRequestEx e) {
            System.out.println("...POST Exception");
            throw e;
        }
    }

    @PutMapping("/persons/{id}")
    public EntityModel<Person> updatePerson(@PathVariable int id, @RequestBody Person updatedPerson) {
        try {
            System.out.println("...called PUT");
            updatedPerson.setId(id);
            Person p = dataRepo.updatePerson(updatedPerson);

            return getEntityForPerson(p);

        } catch (PersonNotFoundEx e) {
            System.out.println("...PUT Exception");
            throw e;
        }
    }

    private EntityModel<Person> getEntityForPerson(Person p) {
        return EntityModel.of(p,
                linkTo(methodOn(PersonController.class).getPerson(p.getId())).withSelfRel(),
                linkTo(methodOn(PersonController.class).getAllPeople()).withRel("listAll"),
                linkTo(methodOn(PersonController.class).addPerson(p)).withRel("add"),
                linkTo(methodOn(PersonController.class).updatePerson(p.getId(), p)).withRel("update"),
                linkTo(methodOn(PersonController.class).deletePerson(p.getId())).withRel("delete"),
                linkTo(methodOn(PersonController.class).getPeopleSize()).withRel("size")
        );
    }

    private Links getLinksForPerson(Person p) {
        return Links.of(
                linkTo(methodOn(PersonController.class).getPerson(p.getId())).withSelfRel(),
                linkTo(methodOn(PersonController.class).getAllPeople()).withRel("listAll"),
                linkTo(methodOn(PersonController.class).addPerson(p)).withRel("add"),
                linkTo(methodOn(PersonController.class).updatePerson(p.getId(), p)).withRel("update"),
                linkTo(methodOn(PersonController.class).deletePerson(p.getId())).withRel("delete"),
                linkTo(methodOn(PersonController.class).getPeopleSize()).withRel("size")
        );
    }
}
