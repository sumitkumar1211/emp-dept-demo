package com.example.empdeptdemo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_SEQUENCE")
    @SequenceGenerator(name = "EMPLOYEE_SEQUENCE", sequenceName = "EMPLOYEE_SEQUENCE", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    @NotBlank
    private String name;

    //    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH}), check why failing
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name = "MAP_EMPLOYEE_DEPARTMENT", joinColumns = @JoinColumn(name = "ID_EMPLOYEE"), inverseJoinColumns = @JoinColumn(name = "ID_DEPARTMENT"))
    private Set<Department> departments = new HashSet<>();
}
