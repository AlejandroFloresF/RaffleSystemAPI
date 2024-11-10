package com.example.rafflesystemapi.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<User> getAllRaffles() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<Object> addRaffle(@RequestBody User user) {
        return this.userService.newUser(user);
    }

    @PutMapping
    public ResponseEntity<Object> updateRaffle(@RequestBody User user) {
        return this.userService.newUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("UserId") Long id) {
        return this.userService.deleteUser(id);
    }
}
