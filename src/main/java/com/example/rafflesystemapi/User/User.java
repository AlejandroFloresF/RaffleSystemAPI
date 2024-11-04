package com.example.rafflesystemapi.User;

import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    private String phone;

    @Column(name = "email", unique = true)
    private String email;

    private String address;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "receives_notifications")
    private Boolean receivesNotifications;
}
