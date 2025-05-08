package com.application.ims.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name="tb_logins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
    private String username;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Size(min = 8, max = 64)
    @Column(name = "password", nullable = false)
    private String password;

}
