package com.example.cwiczenie4spring;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Links;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
public class PersonController {

    private PersonRepository dataRepo = new PersonRepositoryImpl(
            new ArrayList<>(){
                {
                    add(new Person(1, "Jan", 35, "jan.kowalski@example.com"));
                    add(new Person(2, "Anna", 28, "anna.nowak@example.com"));
                    add(new Person(3, "Anna", 42, "anna.wojcik@example.com"));
                    add(new Person(4, "Monika", 35, "monika.lewandowska@example.com"));
                    add(new Person(5, "Jan", 28, "jan.szymanski@example.com"));
                }
            }
    );

    @GetMapping("/personsSize")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Map<String, Integer>> getPeopleSize(HttpServletRequest request) {
        try {
            String acceptHeader = request.getHeader("Accept");
            if (acceptHeader != null && acceptHeader.contains(MediaType.APPLICATION_XML_VALUE)) {
                System.out.println("Get size XML requested");
            } else {
                System.out.println("Get size JSON requested");
            }
            Map<String, Integer> toReturn = Collections.singletonMap("size", dataRepo.getRepositorySize());
            return EntityModel.of(toReturn);

        } catch (BadRequestEx e) {
            System.out.println("...GET size Exception");
            throw e;
        }
    }

    @GetMapping("/authors")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Map<String, String>> getAuthors(HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        if (acceptHeader != null && acceptHeader.contains(MediaType.APPLICATION_XML_VALUE)) {
            System.out.println("Get authors XML requested");
        } else {
            System.out.println("Get authors JSON requested");
        }

        Map<String, String> toReturn = Collections.singletonMap("authors", "Mateusz Śmigielski 260457 i Wojciech Begierski 260415");
        return EntityModel.of(toReturn);
    }

    @GetMapping("/persons")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<Person>> getAllPeople(HttpServletRequest request) {

        String acceptHeader = request.getHeader("Accept");
        if (acceptHeader != null && acceptHeader.contains(MediaType.APPLICATION_XML_VALUE)) {
            System.out.println("Get all people XML requested");
        } else {
            System.out.println("Get all people JSON requested");
        }

        List<EntityModel<Person>> people = dataRepo.getAllPersons().stream()
                .map(it -> getEntityForPerson(it, request))
                .collect(Collectors.toList());

        return CollectionModel.of(people);
    }

    @GetMapping("/persons/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Person> getPerson(@PathVariable int id, HttpServletRequest request) {

        try {

            String acceptHeader = request.getHeader("Accept");
            if (acceptHeader != null && acceptHeader.contains(MediaType.APPLICATION_XML_VALUE)) {
                System.out.println("Get by id XML requested");
            } else {
                System.out.println("Get by id JSON requested");
            }
            Person person = dataRepo.getPerson(id);
            return getEntityForPerson(person, request);
        } catch (PersonNotFoundEx e) {
            System.out.println("...GET Exception");
            throw e;
        }

    }

    @GetMapping("/persons/filter/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<Person>> filterPersonsByName(@PathVariable String name, HttpServletRequest request)  {
        List<EntityModel<Person>> filteredPersons = dataRepo.filterByName(name).stream()
                .map(it -> getEntityForPerson(it, request))
                .collect(Collectors.toList());

        String acceptHeader = request.getHeader("Accept");
        if (acceptHeader != null && acceptHeader.contains(MediaType.APPLICATION_XML_VALUE)) {
            System.out.println("Filter by name XML requested");
        } else {
            System.out.println("Filter by name  JSON requested");
        }

        return CollectionModel.of(filteredPersons);
    }

    @GetMapping("/persons/filter/age/{age}")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<Person>> filterPersonsByAge(@PathVariable int age, HttpServletRequest request) {
        List<EntityModel<Person>> filteredPersons = dataRepo.filterByAge(age).stream()
                .map(it -> getEntityForPerson(it, request))
                .collect(Collectors.toList());

        String acceptHeader = request.getHeader("Accept");
        if (acceptHeader != null && acceptHeader.contains(MediaType.APPLICATION_XML_VALUE)) {
            System.out.println("Filter by age XML requested");
        } else {
            System.out.println("Filter by age  JSON requested");
        }

        return CollectionModel.of(filteredPersons);
    }

    @DeleteMapping("/persons/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Map<String, Boolean>> deletePerson(@PathVariable("id") int id, HttpServletRequest request) {

        try {
            String acceptHeader = request.getHeader("Accept");
            if (acceptHeader != null && acceptHeader.contains(MediaType.APPLICATION_XML_VALUE)) {
                System.out.println("Filter by age XML requested");
            } else {
                System.out.println("Filter by age  JSON requested");
            }

            Map<String, Boolean> toReturn = Collections.singletonMap("deleted", dataRepo.deletePerson(id));
            return EntityModel.of(toReturn);
        } catch (PersonNotFoundEx e) {
            System.out.println("...DELETE Exception");
            throw e;
        }
    }

    @PostMapping("/persons")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Person> addPerson(@RequestBody CreatePerson newPerson, HttpServletRequest request) {
        try {

            String acceptHeader = request.getHeader("Accept");
            if (acceptHeader != null && acceptHeader.contains(MediaType.APPLICATION_XML_VALUE)) {
                System.out.println("Create XML requested");
            } else {
                System.out.println("Create  JSON requested");
            }

            Person person = dataRepo.addPerson(newPerson);

            return getEntityForPerson(person, request);
        } catch (BadRequestEx e) {
            System.out.println("...POST Exception");
            throw e;
        }
    }

    @PutMapping("/persons/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Person> updatePerson(@PathVariable int id, @RequestBody Person updatedPerson, HttpServletRequest request) {
        try {

            String acceptHeader = request.getHeader("Accept");
            if (acceptHeader != null && acceptHeader.contains(MediaType.APPLICATION_XML_VALUE)) {
                System.out.println("Update XML requested");
            } else {
                System.out.println("Update  JSON requested");
            }

            updatedPerson.setId(id);
            Person p = dataRepo.updatePerson(updatedPerson);

            return getEntityForPerson(p, request);

        } catch (PersonNotFoundEx e) {
            System.out.println("...PUT Exception");
            throw e;
        }
    }

    private EntityModel<Person> getEntityForPerson(Person p, HttpServletRequest request) {
        String acceptHeader = request.getHeader(HttpHeaders.ACCEPT);

        if (acceptHeader != null && acceptHeader.contains(MediaType.APPLICATION_XML_VALUE)) {
            // XML response
            return EntityModel.of(p, getLinksForPerson(p, request));
        } else {
            // JSON response (default)
            return EntityModel.of(p,
                    linkTo(methodOn(PersonController.class).getPerson(p.getId(), request)).withSelfRel(),
                    linkTo(methodOn(PersonController.class).getAllPeople(request)).withRel("listAll"),
                    linkTo(methodOn(PersonController.class).addPerson(p.toCreatePerson(), request)).withRel("add"),
                    linkTo(methodOn(PersonController.class).updatePerson(p.getId(), p, request)).withRel("update"),
                    linkTo(methodOn(PersonController.class).deletePerson(p.getId(), request)).withRel("delete"),
                    linkTo(methodOn(PersonController.class).getPeopleSize(request)).withRel("size")
            );
        }
    }

    private Links getLinksForPerson(Person p, HttpServletRequest request) {
        String acceptHeader = request.getHeader(HttpHeaders.ACCEPT);

        if (acceptHeader != null && acceptHeader.contains(MediaType.APPLICATION_XML_VALUE)) {
            // XML response links
            return Links.of(
                    linkTo(methodOn(PersonController.class).getPerson(p.getId(), request)).withSelfRel(),
                    linkTo(methodOn(PersonController.class).getAllPeople(request)).withRel("listAll"),
                    linkTo(methodOn(PersonController.class).addPerson(p.toCreatePerson(), request)).withRel("add"),
                    linkTo(methodOn(PersonController.class).updatePerson(p.getId(), p, request)).withRel("update"),
                    linkTo(methodOn(PersonController.class).deletePerson(p.getId(), request)).withRel("delete"),
                    linkTo(methodOn(PersonController.class).getPeopleSize(request)).withRel("size")
            );
        } else {
            // JSON response links (default)
            return Links.of(
                    linkTo(methodOn(PersonController.class).getPerson(p.getId(), request)).withSelfRel(),
                    linkTo(methodOn(PersonController.class).getAllPeople(request)).withRel("listAll"),
                    linkTo(methodOn(PersonController.class).addPerson(p.toCreatePerson(), request)).withRel("add"),
                    linkTo(methodOn(PersonController.class).updatePerson(p.getId(), p, request)).withRel("update"),
                    linkTo(methodOn(PersonController.class).deletePerson(p.getId(), request)).withRel("delete"),
                    linkTo(methodOn(PersonController.class).getPeopleSize(request)).withRel("size")
            );
        }
    }
}
