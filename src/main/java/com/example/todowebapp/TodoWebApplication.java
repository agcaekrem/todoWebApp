package com.example.todowebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class})

public class TodoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoWebApplication.class, args);
    }

}
