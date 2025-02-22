package com.application.ims.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Size(min = 2, max = 120)
    @Column(name = "product", nullable = false)
    private String name;

    @Size(min = 10)
    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name="supplier_id", nullable = false)
    private Supplier supplier;

}
