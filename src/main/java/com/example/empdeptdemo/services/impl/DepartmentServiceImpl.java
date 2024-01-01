package com.example.empdeptdemo.services.impl;

import com.example.empdeptdemo.constant.AppOperation;
import com.example.empdeptdemo.entities.Department;
import com.example.empdeptdemo.exceptions.ResourceAlreadyExistsException;
import com.example.empdeptdemo.exceptions.ResourceNotFoundException;
import com.example.empdeptdemo.exceptions.ResourceOperationNotAllowedException;
import com.example.empdeptdemo.repositories.DepartmentRepository;
import com.example.empdeptdemo.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.empdeptdemo.constant.AppConstants.*;
import static com.example.empdeptdemo.constant.AppOperation.*;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(final Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Department.class.getSimpleName(), ID, id));
    }

    @Override
    public Department createDepartment(final Department department) {
        return saveDepartment(CREATE, department);
    }

    @Override
    public Department updateDepartment(final Department department) {
        final Optional<Department> optionalDepartment = departmentRepository.findById(department.getId());
        if (optionalDepartment.isEmpty()) {
            throw new ResourceNotFoundException(Department.class.getSimpleName(), ID, department.getId());
        } else {
            final Department oldDepartment = optionalDepartment.get();
            if (oldDepartment.isReadOnly() && department.isReadOnly()) {
                throw new ResourceOperationNotAllowedException(UPDATE, Department.class.getSimpleName(), READONLY, true);
            }
            return saveDepartment(UPDATE, department);
        }
    }

    @Override
    public Long deleteDepartment(final Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()) {
            throw new ResourceNotFoundException(Department.class.getSimpleName(), ID, id);
        }
        final Department department = optionalDepartment.get();
        if (department.isReadOnly()) {
            throw new ResourceOperationNotAllowedException(DELETE, Department.class.getSimpleName(), ID, id);
        }
        department.getEmployees()
                .forEach(emp -> emp.getDepartments().remove(department));
        departmentRepository.deleteById(id);
        return id;
    }

    private Department saveDepartment(final AppOperation operation, final Department department) {
        if (UPDATE.equals(operation)) {
            final Optional<Department> existingDepartment = departmentRepository.findByName(department.getName());
            if (existingDepartment.isPresent() && existingDepartment.get().getId() != department.getId()) {
                throw new ResourceAlreadyExistsException(Department.class.getSimpleName(), NAME, department.getName());
            }
        } else if (CREATE.equals(operation)) {
            if (departmentRepository.existsByName(department.getName())) {
                throw new ResourceAlreadyExistsException(Department.class.getSimpleName(), NAME, department.getName());
            }
        }
        return departmentRepository.save(department);
    }
}
