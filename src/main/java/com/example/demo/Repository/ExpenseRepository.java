package com.example.demo.Repository;

import com.example.demo.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, String> {

    @Query("SELECT e FROM Expense e WHERE e.date BETWEEN :startDate AND :endDate")
    List<Expense> findByDateRange(LocalDate startDate, LocalDate endDate);


    List<Expense> findByCategoryId(String categoryId);


    @Query("SELECT e FROM Expense e WHERE e.date BETWEEN :startDate AND :endDate AND e.category.id = :categoryId")
    List<Expense> findByDateRangeAndCategory(LocalDate startDate, LocalDate endDate, String categoryId);


    @Query("SELECT SUM(e.amount) FROM Expense e")
    Double getTotalExpense();

    @Query("SELECT COUNT(e) FROM Expense e")
    Long getTotalCount();
}
