package com.example.cwiczenie4spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Cwiczenie4SpringApplication {

    public static void main(String[] args) {

        MyData.info();

        SpringApplication.run(Cwiczenie4SpringApplication.class, args);
    }

}
