package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
public class PersonClient {
    private final RestTemplate restTemplate;
    private final String BASE_URL;

    public PersonClient(String baseUrl) {
        Logger logger = (Logger) LoggerFactory.getLogger("org.springframework.http.client");
        Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

        logger.setLevel(Level.OFF);  // Disable logging for RestTemplate
        rootLogger.setLevel(Level.OFF);  // Disable root logger

// Create RestTemplate with the modified HTTP client logging configuration
        this.restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());


        //this.restTemplate = new RestTemplate();
        this.BASE_URL = baseUrl;

//        // Create the request factory with a 5-second timeout
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//        requestFactory.setConnectTimeout((int) TimeUnit.SECONDS.toMillis(5));
//        requestFactory.setConnectionRequestTimeout((int) TimeUnit.SECONDS.toMillis(5));
//
//        // Create the RestTemplate with the request factory
//        this.restTemplate = new RestTemplate(requestFactory);
    }

    public int getPersonSize() {
        String url = BASE_URL + "/personsSize";
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            Map<String, Integer> map = response.getBody();
            return map.get("size");
        } catch (Exception e) {
            System.out.println("Can't get size of array");
        }

        return -1;
    }


    public List<PersonReceive> getAllPeople() {
        String url = BASE_URL + "/persons";
        try {
            ResponseEntity<PersonList> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<PersonList>() {}
            );

            return response.getBody().getEmbedded().getPersonList();
        } catch (Exception e) {
            System.out.println("The list is empty.");
            return new ArrayList<PersonReceive>();
        }

    }

    public PersonReceive getPersonById(int id) {
        try {
            String url = BASE_URL + "/persons/" + id;
            ResponseEntity<PersonReceive> response = restTemplate.getForEntity(url, PersonReceive.class);
            return response.getBody();
        } catch (Exception e) {
            System.out.println("Can't get person with this id = " + id);
        }

        return null;
    }

    public boolean deletePersonById(int id) {
        try {
            String url = BASE_URL + "/persons/" + id;
            ResponseEntity<Map<String, Boolean>> response = restTemplate.exchange(
                    url,
                    HttpMethod.DELETE,
                    null,
                    new ParameterizedTypeReference<Map<String, Boolean>>() {}
            );
            Map<String, Boolean> body = response.getBody();
            return body != null && body.get("deleted") != null && body.get("deleted");

        } catch (Exception e) {
            System.out.println("Can't delete person with this id = " + id);
        }

        return false;
    }

    public PersonReceive addPerson(Person person) {
        try {
            String url = BASE_URL + "/persons";
            ResponseEntity<PersonReceive> response = restTemplate.postForEntity(url, person, PersonReceive.class);
            return response.getBody();
        } catch (Exception e) {
            System.out.println("Can't create such person!");
        }

        return null;
    }

    public PersonReceive updatePerson(int id, Person updatedPerson) {
        try {
            String url = BASE_URL + "/persons/" + id;
            ResponseEntity<PersonReceive> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    new HttpEntity<>(updatedPerson),
                    PersonReceive.class
            );
            return response.getBody();
        } catch (Exception e) {
            System.out.println("Can't update person with this id = " + id);
        }

        return null;
    }
}