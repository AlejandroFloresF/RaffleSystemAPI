package com.example.rafflesystemapi.Repository;

import com.example.rafflesystemapi.ViewModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByFullName(String FullName);
    Optional<User> findByUsername(String username);
}
