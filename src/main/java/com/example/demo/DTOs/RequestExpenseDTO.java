package com.example.demo.DTOs;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RequestExpenseDTO {
    private Double amount;
    private LocalDate date;
    private String note;
    private String categoryId;
}

