package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "category_name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String categoryName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}

