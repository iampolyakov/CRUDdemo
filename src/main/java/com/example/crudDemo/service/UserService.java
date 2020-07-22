package com.example.crudDemo.service;

import java.util.List;

import com.example.crudDemo.model.*;

public interface UserService {
    void create(User user);

    List<User> readAll();

    User generateUser();

    User read(Long id);

    boolean update(User user, Long id);

    boolean delete(Long id);
}


