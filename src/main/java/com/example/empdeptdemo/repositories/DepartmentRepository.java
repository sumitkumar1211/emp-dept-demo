package com.example.empdeptdemo.repositories;

import com.example.empdeptdemo.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByName(final String name);

    Optional<Department> findByName(final String name);

    Long countAllByIdIn(final Set<Long> ids);

    Set<Department> findAllByRequired(final boolean flag);
}
