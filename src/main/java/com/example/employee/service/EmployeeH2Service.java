
package com.example.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeRowMapper;
@Service 

public class EmployeeH2Service implements EmployeeRepository{
    @Autowired
    private JdbcTemplate db;
    @Override
    public ArrayList<Employee> getEmployee(){
        List<Employee> employeesall = db.query("select * from employeeList" , new EmployeeRowMapper());
        ArrayList<Employee> employee = new ArrayList<>(employeesall);
        return employee;
    }
    @Override
    public Employee getEmployeeById(int employeeId){
        try{
            Employee employee = db.queryForObject("select * from employeeList where employeeId = ?", new EmployeeRowMapper(),employeeId);
            return employee;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public Employee addEmployee(Employee employee){
        // db.update("insert into team(playerName , jerseyNumber,role) values(player.getPlayerName(),player.getJerseyNumber(),player.getRole())");
        // return db.queryForObject("select * from team where playerName = ? and jerseyNumber = ? and role = ?",new PlayerRowMapper(),player.getPlayerName(),player.getJerseyNumber(),player.getRole());
        String sql = "INSERT INTO employeeList (employeeName, email , department) VALUES (?, ? , ?)";  
        db.update(sql, employee.getEmployeeName(), employee.getEmail() , employee.getDepartment());
        Employee person = db.queryForObject("select * from employeeList where employeeName = ? and email = ? and department = ?",new EmployeeRowMapper(),employee.getEmployeeName(),employee.getEmail(),employee.getDepartment());
        return person;
        
    }
    @Override
    public Employee updateEmployee(int employeeId , Employee employee){
        if(employee.getEmployeeName()!=null){
            db.update("update employeeList set employeeName = ? where employeeId = ?",employee.getEmployeeName() , employeeId);
        }
        if(employee.getEmail()!=null){
            db.update("update employeeList set email = ? where employeeId = ?",employee.getEmail() , employeeId);
        }
        if(employee.getDepartment()!=null){
            db.update("update employeeList set department = ? where employeeId = ?",employee.getDepartment() , employeeId);
        }
        return getEmployeeById(employeeId);
    }
    @Override
    public void deleteEmployee(int employeeId){
        db.update("delete from employeeList where employeeId = ?",employeeId);
    }

}