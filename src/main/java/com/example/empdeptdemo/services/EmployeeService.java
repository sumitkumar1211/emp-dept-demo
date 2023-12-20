package com.example.empdeptdemo.services;

import com.example.empdeptdemo.entities.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(final Long id);

    Employee createEmployee(final Employee employee);

    Employee updateEmployee(final Employee employee);

    void deleteEmployee(final Long id);
}
