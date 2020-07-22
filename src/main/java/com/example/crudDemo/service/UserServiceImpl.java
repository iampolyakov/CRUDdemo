package com.example.crudDemo.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

import com.example.crudDemo.model.*;

@Service
public class UserServiceImpl implements UserService {

    private static final Map<Long, User> USER_REPOSITORY_MAP = new HashMap<>();

    private static final AtomicLong USER_ID_COUNTER = new AtomicLong();

    private String[] firstNamesList = new String[]{"Liam", "Emma", "Noah", "Olivia", "William", "Ava", "James", "Isabella", "Oliver", "Sophia"};
    private String[] lastNamesList = new String[]{"Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", "Rodriguez", "Wilson"};

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
        long userID = USER_ID_COUNTER.incrementAndGet();
        user.setId(userID);
        USER_REPOSITORY_MAP.put(userID, user);
    }

    @Override
    public List<User> readAll() {
        return new ArrayList<>(USER_REPOSITORY_MAP.values());
    }

    @Override
    public User read(Long id) {
        return USER_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(User user, Long id) {
        if (USER_REPOSITORY_MAP.containsKey(id)) {
            user.setId(id);
            USER_REPOSITORY_MAP.put(id, user);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return USER_REPOSITORY_MAP.remove(id) != null;
    }
}
