package com.example.rafflesystemapi.Service;

import com.example.rafflesystemapi.Repository.UserRepository;
import com.example.rafflesystemapi.ViewModel.User;
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

    public ResponseEntity<Object> newUser(User userVM) {
        statusMessage = new HashMap<>();
        Optional<User> res = userRepository.findUserByFullName(userVM.getFullName());
        statusMessage.put("name", userVM.getFullName());

        if(res.isPresent() && userVM.getId() == null) {
            statusMessage.put("Error: ", true);
            statusMessage.put("message", "User already exists");
            return new ResponseEntity<>(
                    statusMessage,
                    HttpStatus.CONFLICT
            );
        }
        statusMessage.put("message", "User created successfully");
        if(userVM.getId() > 0) {
            statusMessage.put("message", "User successfully updated");
        }
        userRepository.save(userVM);
        statusMessage.put("Data: ", userVM);

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
