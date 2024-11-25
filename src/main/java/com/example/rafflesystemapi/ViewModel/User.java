package com.example.rafflesystemapi.ViewModel;

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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getReceivesNotifications() {
        return receivesNotifications;
    }

    public void setReceivesNotifications(Boolean receivesNotifications) {
        this.receivesNotifications = receivesNotifications;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


}
