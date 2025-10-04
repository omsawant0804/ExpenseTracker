package com.example.demo.Service;

import com.example.demo.DTOs.RequestExpenseDTO;
import com.example.demo.DTOs.ResponseExpenseDTO;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.Expense;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.ExpenseRepository;
import com.example.demo.Utility.Response;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Data
@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;


    public Response<ResponseExpenseDTO> addExpense(RequestExpenseDTO expenseRequest) {
        try {
            if (expenseRequest.getAmount() == null || expenseRequest.getAmount() <= 0 ||
                    expenseRequest.getDate() == null || expenseRequest.getNote() == null || expenseRequest.getNote().isBlank() || expenseRequest.getCategoryId() == null || expenseRequest.getCategoryId().isBlank()) {
                return new Response<>(false, "Please check required fields (amount, date, note)", null);
            }

            Expense expense = new Expense();
            expense.setAmount(expenseRequest.getAmount());
            expense.setDate(expenseRequest.getDate());
            expense.setNote(expenseRequest.getNote());

            if (expenseRequest.getCategoryId() != null) {
                Category category = categoryRepository.findById(expenseRequest.getCategoryId()).orElse(null);
                if (category == null) {
                    return new Response<>(false, "Category not found", null);
                }
                expense.setCategory(category);
            }

            Expense savedExpense = expenseRepository.save(expense);
            return new Response<>(true, "Expense added successfully", List.of(new ResponseExpenseDTO(savedExpense)));
        } catch (Exception e) {
            return new Response<>(false, "Something went wrong while adding expense: " + e.getMessage(), null);
        }
    }


    public Response<ResponseExpenseDTO> getAllExpenses() {
        try {
            List<Expense> expenses = expenseRepository.findAll();
            if (expenses.isEmpty()) {
                return new Response<>(true, "No expenses found", null);
            }
            return new Response<>(true, "Expenses fetched successfully",
                    expenses.stream().map(ResponseExpenseDTO::new).toList());
        } catch (Exception e) {
            return new Response<>(false, "Something went wrong while fetching expenses", null);
        }
    }


    public Response<ResponseExpenseDTO> getExpenseById(String id) {
        try {
            Expense expense = expenseRepository.findById(id).orElse(null);
            if (expense == null) {
                return new Response<>(false, "Expense not found", null);
            }
            return new Response<>(true, "Expense fetched successfully", List.of(new ResponseExpenseDTO(expense)));
        } catch (Exception e) {
            return new Response<>(false, "Something went wrong while fetching expense", null);
        }
    }


    public Response<ResponseExpenseDTO> updateExpense(String id, RequestExpenseDTO expenseRequest) {
        try {
            Expense expense = expenseRepository.findById(id).orElse(null);

            if (expense == null) {
                return new Response<>(false, "Expense not found", null);
            }
            boolean update =false;

            if (expenseRequest.getAmount() != null && expenseRequest.getAmount() > 0) {
                expense.setAmount(expenseRequest.getAmount());
                update=true;
            }
            if (expenseRequest.getDate() != null) {
                expense.setDate(expenseRequest.getDate());
                update=true;
            }
            if (expenseRequest.getNote() != null) {
                expense.setNote(expenseRequest.getNote());
                update=true;
            }

            if (expenseRequest.getCategoryId() != null) {
                Category category = categoryRepository.findById(expenseRequest.getCategoryId()).orElse(null);
                if (category == null) {
                    return new Response<>(false, "Category not found", null);
                }
                expense.setCategory(category);
                update=true;
            }
            if(update){
                Expense updatedExpense = expenseRepository.save(expense);
                return new Response<>(true, "Expense updated successfully", List.of(new ResponseExpenseDTO(updatedExpense)));
            }
            return new Response<>(false, "Please Provide Values to Update", null);
        } catch (Exception e) {
            return new Response<>(false, "Something went wrong while updating expense", null);
        }
    }


    public Response<ResponseExpenseDTO> deleteExpense(String id) {
        try {
            Expense expense = expenseRepository.findById(id).orElse(null);
            if (expense == null) {
                return new Response<>(false, "Expense not found", null);
            }
            expenseRepository.delete(expense);
            return new Response<>(true, "Expense deleted successfully", null);
        } catch (Exception e) {
            return new Response<>(false, "Something went wrong while deleting expense", null);
        }
    }



    public Response<ResponseExpenseDTO> getFilteredExpenses(String startDate, String endDate, String categoryId) {
        try {
            List<Expense> expenses;

            if (startDate != null && endDate != null && categoryId != null) {
                expenses = expenseRepository.findByDateRangeAndCategory(
                        LocalDate.parse(startDate),
                        LocalDate.parse(endDate),
                        categoryId);
            } else if (startDate != null && endDate != null) {
                expenses = expenseRepository.findByDateRange(LocalDate.parse(startDate), LocalDate.parse(endDate));
            } else if (categoryId != null) {
                expenses = expenseRepository.findByCategoryId(categoryId);
            } else {
                return new Response<>(false,"Provide valid Fileds",null);
            }

            if (expenses.isEmpty()) {
                return new Response<>(true, "No expenses found for given filters", null);
            }

            return new Response<>(true, "Filtered expenses fetched successfully",
                    expenses.stream().map(ResponseExpenseDTO::new).toList());

        } catch (Exception e) {
            return new Response<>(false, "Something went wrong while fetching filtered expenses", null);
        }
    }


    public Response<Object> getExpenseSummary() {
        try {
            Double totalAmount = expenseRepository.getTotalExpense();
            Long totalCount = expenseRepository.getTotalCount();

            double avgAmount = (totalCount != 0) ? totalAmount / totalCount : 0.0;

            var summary = new java.util.HashMap<String, Object>();
            summary.put("totalAmount", totalAmount);
            summary.put("totalExpenses", totalCount);
            summary.put("averageAmount", avgAmount);

            return new Response<>(true, "Expense summary fetched successfully", List.of(summary));

        } catch (Exception e) {
            return new Response<>(false, "Something went wrong while fetching summary", null);
        }
    }
}

