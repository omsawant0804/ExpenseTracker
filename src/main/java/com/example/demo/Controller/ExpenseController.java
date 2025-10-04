package com.example.demo.Controller;


import com.example.demo.DTOs.RequestExpenseDTO;
import com.example.demo.Service.ExpenseService;
import com.example.demo.Utility.Response;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;


    @PostMapping("/add-expense")
    public ResponseEntity<Response> addExpense(@RequestBody RequestExpenseDTO expenseRequest) {
        try {
            Response res = expenseService.addExpense(expenseRequest);
            if (res.isSuccess()) {
                return ResponseEntity.ok().body(res);
            }
            return ResponseEntity.badRequest().body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(false, e.toString(), null));
        }
    }


    @GetMapping("/get-all-expense")
    public ResponseEntity<Response> getAllExpenses() {
        try {
            Response res = expenseService.getAllExpenses();
            if (res.isSuccess()) {
                return ResponseEntity.ok().body(res);
            }
            return ResponseEntity.badRequest().body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(false, e.toString(), null));
        }
    }


    @GetMapping("/get-expense/{id}")
    public ResponseEntity<Response> getExpenseById(@PathVariable String id) {
        try {
            Response res = expenseService.getExpenseById(id);
            if (res.isSuccess()) {
                return ResponseEntity.ok().body(res);
            }
            return ResponseEntity.badRequest().body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(false, e.toString(), null));
        }
    }


    @PatchMapping("/update-expense/{id}")
    public ResponseEntity<Response> updateExpense(@PathVariable String id, @RequestBody RequestExpenseDTO expenseRequest) {
        try {
            Response res = expenseService.updateExpense(id, expenseRequest);
            if (res.isSuccess()) {
                return ResponseEntity.ok().body(res);
            }
            return ResponseEntity.badRequest().body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(false, e.toString(), null));
        }
    }


    @DeleteMapping("/delete-expense/{id}")
    public ResponseEntity<Response> deleteExpense(@PathVariable String id) {
        try {
            Response res = expenseService.deleteExpense(id);
            if (res.isSuccess()) {
                return ResponseEntity.ok().body(res);
            }
            return ResponseEntity.badRequest().body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(false, e.toString(), null));
        }
    }


    @GetMapping("/filter")
    public ResponseEntity<Response> getFilteredExpenses(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String categoryId) {

        try {
            Response res = expenseService.getFilteredExpenses(startDate, endDate, categoryId);
            if (res.isSuccess()) {
                return ResponseEntity.ok(res);
            }
            return ResponseEntity.badRequest().body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(false, e.toString(), null));
        }
    }


    @GetMapping("/summary")
    public ResponseEntity<Response> getExpenseSummary() {
        try {
            Response res = expenseService.getExpenseSummary();
            if (res.isSuccess()) {
                return ResponseEntity.ok(res);
            }
            return ResponseEntity.badRequest().body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(false, e.toString(), null));
        }
    }
}

