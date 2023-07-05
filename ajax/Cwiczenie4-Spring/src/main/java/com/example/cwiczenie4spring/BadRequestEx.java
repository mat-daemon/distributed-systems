package com.example.cwiczenie4spring;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BadRequestEx extends RuntimeException{
    public BadRequestEx() {
        super("Invalid person data");
    }

}
