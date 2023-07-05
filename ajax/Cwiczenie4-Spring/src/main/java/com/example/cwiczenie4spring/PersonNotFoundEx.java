package com.example.cwiczenie4spring;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PersonNotFoundEx extends RuntimeException{
    public PersonNotFoundEx() {
        super("The specified person does not exist");
    }
    public PersonNotFoundEx(int id) {
        super("The person of id= "+id+" does NOT exist");
    }
}
