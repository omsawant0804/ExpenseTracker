# Expense Tracker

I built a Personal Expense Tracker with CRUD APIs to manage daily expenses. Along with that, I added APIs to filter expenses by date range or category and another endpoint to get a summary report showing total and average spending. The project also includes APIs to add and view expense categories. Each endpoint has proper validations and handles edge cases, returning a 400 Bad Request when needed.

### Tech Stack : Spring Boot/Java + SQL/MySQL

### APIs Overview
1. **addExpense** : To add a new expense with amount, date, note, and category.
2. **getAllExpenses** : To get all expenses from the system.
3. **getExpenseById** : To get details of a particular expense by its ID.
4. **updateExpense** : To update expense details (can update a single field, combination of fields, or all fields at once).
5. **deleteExpense** : To delete an expense from the system.
6. **filterExpenses** : To get expenses filtered by date range, category, or both.
7. **getExpenseSummary** : To get a summary report of total spending, total number of expenses, and average expense.
8. **addCategory** : To add a new category (like Food, Travel, Bills).
9. **getAllCategories** : To get all categories from the system.

### Database
- databaseName : expense_tracker
- tableName : tbl_category
- table Colum overview
<img width="631" height="92" alt="image" src="https://github.com/user-attachments/assets/d66193bd-832f-4467-b083-90004d67fc86" />

- tableName : tbl_expense
- table Colum overview
<img width="856" height="87" alt="image" src="https://github.com/user-attachments/assets/7be4113b-fd6e-40db-a3a3-8e140262874c" />

## Project SetUp
### Prerequisites
1. Java JDK (preferably 17 or 21) installed and set in PATH.
2. Maven installed.
3. MySQL Server installed and running.

### Steps to run the Project
1. Clone the Repo 
   ``` git clone https://github.com/omsawant0804/ExpenseTracker.git ```
2. Open in any IDE
3. Open ```application.properties``` file located in ```Expense Tracker\src\main\resources``` \
Check the Configration. Setup the Server Port or MySQL port/user id/password according to your system.
<img width="1342" height="496" alt="image" src="https://github.com/user-attachments/assets/a4788bb4-5c20-4641-b4b6-e58e23605533" />

4. Open MySQL WorkBench and Create the database ```Create database if not exists expense_tracker;``` No nedd to create tables it will be created by hibernet
5. Open IDE Terminal
6. Run Command ```mvn clean install``` it will clean and install dependencies
<img width="1825" height="388" alt="image" src="https://github.com/user-attachments/assets/f82da7e0-b8e9-4e52-a121-69003b44319f" />

7. After Build Sucess to run or Start the Server run command ```mvn spring-boot:run``` this will start the server
<img width="1816" height="431" alt="image" src="https://github.com/user-attachments/assets/2a8c911c-52ba-4f3a-984f-1de2879b2390" />

8. We are free to hit the APIs **(Note : First Add the Categories to add new expense)**

## Test the APIs 
- Open PostMan Import API Collection JSON File [API Collection (JSON File)](https://drive.google.com/drive/folders/1F4fsYAKjkqiLg752BZyy1exeb1mO6MT7?usp=drive_link)

### 1. POST : addExpense API ```http://localhost:8080/expense/add-expense```
**Note : If you have changed server port in application.properties change here too ```8080--> you port```. If you are using server address change ```localhost-->your ip address```**
Body :
```
{
  "amount": 500,
  "date": "2025-12-04",
  "note": "Food Expense",
  "categoryId": "b0d786c9-f616-4108-96ca-b66ead16a7e7"
}
```
Resposne :
```
{
    "success": true,
    "message": "Expense added successfully",
    "data": [
        {
            "id": "1fa8a62d-be92-4dcb-a04d-bcfc5b1ee27e",
            "amount": 500.0,
            "date": "2025-12-04",
            "note": "Food Expense",
            "categoryName": "Food"
        }
    ]
}

```

### 2. POST : addCategory API ```http://localhost:8080/category/add-category```
**Note : If you have changed server port in application.properties change here too ```8080--> you port```. If you are using server address change ```localhost-->your ip address```**
Body :
```
{
  "categoryName": "Food",
  "description": "Eat Eat"
}
```
Resposne :
```
{
    "success": true,
    "message": "Category added successfully",
    "data": [
        {
            "id": "b0d786c9-f616-4108-96ca-b66ead16a7e7",
            "categoryName": "Food",
            "categoryDescription": "Eat Eat"
        }
    ]
}

```

### 3. GET : getAllCatagories API ```http://localhost:8080/category/get-all-category```
**Note : If you have changed server port in application.properties change here too ```8080--> you port```. If you are using server address change ```localhost-->your ip address```**
Resposne :
```
{
    "success": true,
    "message": "Categories fetched successfully",
    "data": [
        {
            "id": "25a9c535-d8f5-4155-b1c1-aa83d68d48c0",
            "categoryName": "Travel",
            "categoryDescription": "Do Travel"
        },
        {
            "id": "288bb112-9df7-4efc-a0f8-14db0f5edddf",
            "categoryName": "Hospital",
            "categoryDescription": "Take Rest"
        },
        {
            "id": "5442fe48-b8ad-4b4c-9877-6906a53997e5",
            "categoryName": "Shopping",
            "categoryDescription": "Do Shoping in Mall, Shop etccc"
        },
        {
            "id": "b0d786c9-f616-4108-96ca-b66ead16a7e7",
            "categoryName": "Food",
            "categoryDescription": "Eat Eat"
        }
    ]
}

```

### 4. GET : getAllExpenses API ```http://localhost:8080/expense/get-all-expense```
**Note : If you have changed server port in application.properties change here too ```8080--> you port```. If you are using server address change ```localhost-->your ip address```**
Resposne :
```
{
    "success": true,
    "message": "Expenses fetched successfully",
    "data": [
        {
            "id": "1fa8a62d-be92-4dcb-a04d-bcfc5b1ee27e",
            "amount": 500.0,
            "date": "2025-12-04",
            "note": "Food Expense",
            "categoryName": "Food"
        },
        {
            "id": "4f2f1ff1-d9b2-435a-ab45-6b1d0919ed02",
            "amount": 200.0,
            "date": "2025-10-04",
            "note": "Shopping",
            "categoryName": "Shopping"
        },
        {
            "id": "8e55f845-7cf5-44c8-8f0c-c1f9c3220bbc",
            "amount": 2000.0,
            "date": "2025-11-04",
            "note": "Shopping T-Shirt",
            "categoryName": "Shopping"
        },
        {
            "id": "a8f863a8-ee61-4f07-99e0-4edc41b2bbe0",
            "amount": 5000.0,
            "date": "2025-11-04",
            "note": "Food Expense",
            "categoryName": "Food"
        }
    ]
}

```

### 5. GET : getExpenseById API ```http://localhost:8080/expense/get-expense/{id}```
**Note : If you have changed server port in application.properties change here too ```8080--> you port```. If you are using server address change ```localhost-->your ip address```**
Resposne :
```
{
    "success": true,
    "message": "Expense fetched successfully",
    "data": [
        {
            "id": "1fa8a62d-be92-4dcb-a04d-bcfc5b1ee27e",
            "amount": 500.0,
            "date": "2025-12-04",
            "note": "Food Expense",
            "categoryName": "Food"
        }
    ]
}

```

### 6. PATCH : updateExpense API ```http://localhost:8080/expense/update-expense/{id}```
**Note : If you have changed server port in application.properties change here too ```8080--> you port```. If you are using server address change ```localhost-->your ip address```**
We can also update a single field, a combination of fields, or all fields
Body :
```
{
  "amount": 1200.50,
  "date": "2025-10-04",
  "note": "Dinner with friends",
  "categoryId": "b0d786c9-f616-4108-96ca-b66ead16a7e7"
}
```
Resposne :
```
{
    "success": true,
    "message": "Expense updated successfully",
    "data": [
        {
            "id": "1fa8a62d-be92-4dcb-a04d-bcfc5b1ee27e",
            "amount": 1200.5,
            "date": "2025-10-04",
            "note": "Dinner with friends",
            "categoryName": "Food"
        }
    ]
}

```

### 7. DELETE : daeateExpense API ```http://localhost:8080/expense/delete-expense/{id}```
**Note : If you have changed server port in application.properties change here too ```8080--> you port```. If you are using server address change ```localhost-->your ip address```**
Resposne :
```
{
    "success": true,
    "message": "Expense deleted successfully",
    "data": null
}

```

### 8. GET : ExpenseSummary API ```http://localhost:8080/expense/summary```
**Note : If you have changed server port in application.properties change here too ```8080--> you port```. If you are using server address change ```localhost-->your ip address```**
Resposne :
```
{
    "success": true,
    "message": "Expense summary fetched successfully",
    "data": [
        {
            "totalAmount": 7200.0,
            "averageAmount": 2400.0,
            "totalExpenses": 3
        }
    ]
}

```

### 9. GET : ExpenseFilter API ```http://localhost:8080/expense/filter```
**Note : If you have changed server port in application.properties change here too ```8080--> you port```. If you are using server address change ```localhost-->your ip address```**

We can filter the expenses by date range, by category, or by using both together.
Params :
```
startDate : 2025-10-01
endDate : 2025-10-04
categoryId : 5442fe48-b8ad-4b4c-9877-6906a53997e5
```
Resposne :
```
{
    "success": true,
    "message": "Filtered expenses fetched successfully",
    "data": [
        {
            "id": "4f2f1ff1-d9b2-435a-ab45-6b1d0919ed02",
            "amount": 200.0,
            "date": "2025-10-04",
            "note": "Shopping",
            "categoryName": "Shopping"
        }
    ]
}

```

## Struture I follow : Layered Architecture
**Note: I am returning a 200 (OK) status code for all successful operations including create and delete because I am returning a custom response body in every case.**
### For Controller layer
similar for every function in controller
```
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
```

### For Service layer
similar for every function in service layer
```
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
```

### For Response
similar for evey response as we observe 
```
public class Response<T> {
// We are using this Response bcoz we have to standardize the structure we send to frontend
   private boolean success; // it holds true or false : true means success
   private String message; // here we are using this bcoz we want some time to send custom message
   private List<T> data; // if its post we can send our data here if not send null
}
```










