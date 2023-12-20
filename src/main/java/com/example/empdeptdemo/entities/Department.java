package com.example.empdeptdemo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DEPARTMENT")
@Getter
@Setter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPARTMENT_SEQUENCE")
    @SequenceGenerator(name = "DEPARTMENT_SEQUENCE", sequenceName = "DEPARTMENT_SEQUENCE", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "READONLY")
    private boolean readOnly;

    @Column(name = "REQUIRED")
    private boolean required;

    @ManyToMany(mappedBy = "departments")
    @JsonIgnore
    private Set<Employee> employees = new HashSet<>();
}
