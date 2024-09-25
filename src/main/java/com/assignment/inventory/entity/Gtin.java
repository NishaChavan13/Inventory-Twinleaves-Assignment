package com.assignment.inventory.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Gtin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String gtin;

    @JoinColumn
    @OneToOne
    private Product product;
}
