
package com.example.employee.controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeH2Service;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
public class EmployeeController{
    @Autowired
    public EmployeeH2Service employeeservice;
    @GetMapping("/employees")
    public ArrayList<Employee> getEmployee(){
        return employeeservice.getEmployee();
    }
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable ("employeeId") int employeeId){
        return employeeservice.getEmployeeById(employeeId);
    }
    @PostMapping("/employees")
    public Employee addEmployeepost(@RequestBody Employee employee){
        return employeeservice.addEmployee(employee);
    }
    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@PathVariable ("employeeId") int employeeId , @RequestBody Employee employee){
        return employeeservice.updateEmployee(employeeId , employee);
    }
    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployees(@PathVariable ("employeeId") int employeeId){
        employeeservice.deleteEmployee(employeeId);
    }

}