package com.example.rafflesystemapi.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    HashMap<String, Object> statusMessage;

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public ResponseEntity<Object> newUser(User user) {
        statusMessage = new HashMap<>();
        Optional<User> res = userRepository.findUserByFullName(user.getFullName());
        statusMessage.put("name", user.getFullName());

        if(res.isPresent() && user.getId() == null) {
            statusMessage.put("Error: ", true);
            statusMessage.put("message", "User already exists");
            return new ResponseEntity<>(
                    statusMessage,
                    HttpStatus.CONFLICT
            );
        }
        statusMessage.put("message", "User created successfully");
        if(user.getId() > 0) {
            statusMessage.put("message", "User successfully updated");
        }
        userRepository.save(user);
        statusMessage.put("Data: ", user);

        return new ResponseEntity<>(
                statusMessage,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteUser(Long id) {
        statusMessage = new HashMap<>();
        boolean exist = this.userRepository.existsById(id);
        if(!exist) {
            statusMessage.put("Error: ", true);
            statusMessage.put("message", "User does not exists");
            return new ResponseEntity<>(
                    statusMessage,
                    HttpStatus.CONFLICT
            );
        }
        userRepository.deleteById(id);
        statusMessage.put("message", "User successfully deleted");
        return new ResponseEntity<>(
                statusMessage,
                HttpStatus.ACCEPTED
        );
    }
}
