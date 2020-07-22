package com.example.crudDemo.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
