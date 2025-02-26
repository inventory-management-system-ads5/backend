package com.application.ims.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Size(min = 2, max = 50)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(min = 10)
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
