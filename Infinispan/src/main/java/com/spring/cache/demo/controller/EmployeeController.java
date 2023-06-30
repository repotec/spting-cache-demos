package com.spring.cache.demo.controller;

import com.spring.cache.demo.model.Employee;
import com.spring.cache.demo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Employee> createNewEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.insertOrUpdateEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.insertOrUpdateEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("employee [" + employeeId + "] is deleted.", HttpStatus.OK);
    }
}
