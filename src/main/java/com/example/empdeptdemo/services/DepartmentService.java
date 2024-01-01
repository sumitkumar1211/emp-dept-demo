package com.example.empdeptdemo.services;

import com.example.empdeptdemo.entities.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments();

    Department getDepartmentById(final Long id);

    Department createDepartment(final Department department);

    Department updateDepartment(final Department department);

    Long deleteDepartment(final Long id);
}
