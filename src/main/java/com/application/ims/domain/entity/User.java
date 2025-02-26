package com.application.ims.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
    private String fullName;

    @Email
    @Column(name = "email ", nullable = false, unique = true)
    private String email;

    @Size(min = 8, max = 64)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true; // default to true

}
