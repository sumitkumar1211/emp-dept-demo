package com.example.empdeptdemo.controllers;

import com.example.empdeptdemo.entities.Department;
import com.example.empdeptdemo.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable final Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    public Department createDepartment(@RequestBody final Department department) {
        return departmentService.createDepartment(department);
    }

    @PutMapping
    public Department updateDepartment(@RequestBody final Department department) {
        return departmentService.updateDepartment(department);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable final Long id) {
        departmentService.deleteDepartment(id);
    }
}
