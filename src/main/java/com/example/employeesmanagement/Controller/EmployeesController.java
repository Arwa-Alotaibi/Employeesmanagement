package com.example.employeesmanagement.Controller;


import com.example.employeesmanagement.Pogo.Employees;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.example.employeesmanagement.Api.ApiResponse;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeesController {

    ArrayList<Employees>  employees = new ArrayList<>();

    //Get all the Employees
    @GetMapping("/get")
    public ArrayList<Employees> Getall(){
        return employees;
    }

    //Add new Employee
    @PostMapping("/add")
    public ResponseEntity AddEmployees(@Valid @RequestBody Employees employee , Errors errors){
        if(errors.hasErrors()){
            String message =errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        employees.add(employee);
        return ResponseEntity.status(200).body(new ApiResponse("user added"));
    }

    //Update Employee
    @PutMapping("/edit/{index}")
    public ResponseEntity UpdateEmployees(@Valid @RequestBody Employees employee ,Errors errors , @PathVariable int index){
        if(errors.hasErrors()){
            String message =errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        employees.set(index,employee);
        return ResponseEntity.status(200).body(new ApiResponse("user updated"));
    }

    //Delete Employee
    @DeleteMapping("/delete/{index}")
    public ApiResponse Delete(@PathVariable int index){
        employees.remove(index);
        return new ApiResponse("deleted");
    }
   // Employees apply for an annual leave and and turn their onLeave status to true and reduce annualLeave by 1
    // (Check if employee is on leave return bad request, if employee applies for leave and has 0 days return bad request)

    @PutMapping("/employees/apply/{id}")
    public ResponseEntity Employeesapply(@Valid @RequestBody Employees employee , @PathVariable Long id){

        for(Employees e : employees){
                if(id.equals(e.getId())&& e.getAnnualLeave()>0){
                    int balance= 1;
                    int total = e.getAnnualLeave()-balance;
                    e.setAnnualLeave(total);
                    e.setOnLeave("true");
                    return ResponseEntity.status(200).body(new ApiResponse("You can take a vacation"));
                } else if(id.equals(e.getId())&& e.getAnnualLeave()==0){
                    return ResponseEntity.status(400).body(new ApiResponse("You can't take a vacation"));
                }
            }
        return ResponseEntity.status(400).body(new ApiResponse("try another id"));

    }



}
