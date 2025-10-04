package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "tbl_expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "amount", nullable = false, columnDefinition = "DOUBLE")
    private Double amount;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "note", nullable = false, columnDefinition = "TEXT")
    private String note;


    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
}

