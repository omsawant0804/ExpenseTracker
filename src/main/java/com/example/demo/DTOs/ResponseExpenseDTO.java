package com.example.demo.DTOs;

import com.example.demo.Entity.Expense;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseExpenseDTO {
    private String id;
    private Double amount;
    private LocalDate date;
    private String note;
    private String categoryName;

    public ResponseExpenseDTO (Expense expense) {
        this.id = expense.getId();
        this.amount = expense.getAmount();
        this.date = expense.getDate();
        this.note = expense.getNote();
        this.categoryName = expense.getCategory() != null ? expense.getCategory().getCategoryName() : null;
    }
}
