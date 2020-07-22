package com.example.crudDemo.controller;

import com.example.crudDemo.model.User;
import com.example.crudDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users")
    public ResponseEntity<?> create(@RequestBody User user) {
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<?> read() {
        List<User> users = userService.readAll();

        if (users != null && users.size() > 0) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Users not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") Long id) {
        User user = userService.read(id);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with ID " + id + " is not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/generate")
    public ResponseEntity<User> generate() {
        User user = userService.generateUser();
        create(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody User user) {
        if (userService.update(user, id)) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with ID " + id + " is not found", HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        if (userService.delete(id)) {
            return new ResponseEntity<>("User with ID " + id + " was deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with ID " + id + " is not found", HttpStatus.NOT_MODIFIED);
        }
    }
}
