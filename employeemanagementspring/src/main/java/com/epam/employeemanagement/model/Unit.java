package com.epam.employeemanagement.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "unit")
public class Unit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "unit_id", nullable = false, length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unitId;

    @Column(name = "name", nullable = true, length = 20)
    private String name;

    @OneToMany(targetEntity = Employee.class, mappedBy = "unit")
    List<Employee> employees;

    public Unit() {
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
