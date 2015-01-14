package com.epam.employeemanagement.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.epam.employeemanagement.model.Employee;


public interface EmployeeDAO extends JpaRepository<Employee, Long> {
    @Query("select e from Employee e where lower(e.firstName) like lower(concat('%',:name,'%'))"
            + "or lower(e.lastName) like lower(concat('%',:name,'%'))"
            + "or lower(concat(e.firstName,' ',e.lastName)) like lower(concat('%',:name,'%'))")
    List<Employee> findByNameLike(@Param("name") String name, Pageable page);

    @Query("select count(e.employeeId) from Employee e where lower(e.firstName) like lower(concat('%',:name,'%'))"
            + "or lower(e.lastName) like lower(concat('%',:name,'%'))"
            + "or lower(concat(e.firstName,' ',e.lastName)) like lower(concat('%',:name,'%'))")
    long countByNameLike(@Param("name") String name);
}
