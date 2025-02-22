package com.application.ims.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Size(min = 2, max = 120)
    @Column(name = "name", nullable = false)
    private String name;

    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "contact_info", nullable = false)
    private String contactInfo;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true; // default true

    @OneToMany(mappedBy = "customer")
    private List<Sale> sales;

}
