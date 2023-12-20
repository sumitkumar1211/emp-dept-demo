package com.example.empdeptdemo.services.impl;

import com.example.empdeptdemo.entities.Department;
import com.example.empdeptdemo.entities.Employee;
import com.example.empdeptdemo.exceptions.ResourceNotFoundException;
import com.example.empdeptdemo.repositories.DepartmentRepository;
import com.example.empdeptdemo.repositories.EmployeeRepository;
import com.example.empdeptdemo.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.empdeptdemo.constant.AppConstants.ID;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(final Long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), ID, id));
    }

    @Override
    public Employee createEmployee(final Employee employee) {
        return saveEmployee(employee);
    }

    @Override
    public Employee updateEmployee(final Employee employee) {
        final Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
        if (optionalEmployee.isEmpty()) {
            throw new ResourceNotFoundException(Employee.class.getSimpleName(), ID, employee.getId());
        }
        return saveEmployee(employee);
    }

    @Override
    public void deleteEmployee(final Long id) {
        final Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new ResourceNotFoundException(Employee.class.getSimpleName(), ID, id);
        }
        employeeRepository.deleteById(id);
    }

    private Employee saveEmployee(final Employee employee) {
        if (isBlank(employee.getName())) {
            throw new IllegalArgumentException("name is null or empty");
        }
        final Set<Long> ids = employee.getDepartments().stream()
            .map(Department::getId)
            .collect(toSet());
        if (departmentRepository.countAllByIdIn(ids) != ids.size()) {
            throw new ResourceNotFoundException(Department.class.getSimpleName(), ID, ids);
        }
        employee.getDepartments().addAll(departmentRepository.findAllByRequired(true));
        return employeeRepository.save(employee);
    }
}
