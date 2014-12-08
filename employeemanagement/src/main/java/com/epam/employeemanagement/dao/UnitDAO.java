package com.epam.employeemanagement.dao;

import java.util.List;

import com.epam.employeemanagement.model.Unit;


public interface UnitDAO {
    Unit create(Unit unit);
    Unit edit(Unit unit);
    void remove(Unit unit);
    Unit find(Object id);
    List<Unit> findAll();
}
