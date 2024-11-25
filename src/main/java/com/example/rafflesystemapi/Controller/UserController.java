package com.example.rafflesystemapi.Controller;

import com.example.rafflesystemapi.ViewModel.User;
import com.example.rafflesystemapi.Service.UserService;
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
    public ResponseEntity<Object> addRaffle(@RequestBody User userVM) {
        return this.userService.newUser(userVM);
    }

    @PutMapping
    public ResponseEntity<Object> updateRaffle(@RequestBody User userVM) {
        return this.userService.newUser(userVM);
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("UserId") Long id) {
        return this.userService.deleteUser(id);
    }
}
