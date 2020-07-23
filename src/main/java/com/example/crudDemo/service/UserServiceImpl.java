package com.example.crudDemo.service;

import com.example.crudDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import com.example.crudDemo.model.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private String[] firstNamesList = new String[]{"Liam", "Emma", "Noah", "Olivia", "William", "Ava", "James", "Isabella", "Oliver", "Sophia"};
    private String[] lastNamesList = new String[]{"Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", "Rodriguez", "Wilson"};

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User generateUser() {
        User user = new User();
        LocalDate start = LocalDate.of(1931, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2000, Month.DECEMBER, 31);
        user.setFirstName(firstNamesList[(int) (Math.random() * firstNamesList.length)]);
        user.setLastName(lastNamesList[(int) (Math.random() * lastNamesList.length)]);
        user.setBirthDate(LocalDate.ofEpochDay(ThreadLocalRandom.current().nextLong(start.toEpochDay(), end.toEpochDay())));

        return user;
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> readAll() {
        return userRepository.findAll();
    }

    @Override
    public User read(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public boolean update(User user, Long id) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
