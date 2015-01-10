package com.epam.employeemanagement.service;

import java.util.List;

import com.epam.employeemanagement.model.Unit;

public interface UnitService {
    List<Unit> list(int pageNum);
    Unit view(Long unitId);
    Unit save(Unit unit);
    void delete(Long unitId);
    boolean exists(Long unitId);
    long count();
    int getPageSize();
}
