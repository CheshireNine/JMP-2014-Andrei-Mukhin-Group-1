package com.epam.employeemanagement.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.epam.employeemanagement.dao.UnitDAO;
import com.epam.employeemanagement.model.Unit;
import com.epam.employeemanagement.service.UnitService;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    UnitDAO unitDAO;

    @Value("${page.defaultorder}")
    private String direction;

    @Value("${page.size}")
    private int pageSize;

    private Sort sort;

    public UnitServiceImpl() {
    }

    @PostConstruct
    public void initEmployeeSort() {
        sort = new Sort(Sort.Direction.valueOf(direction), ServiceConstants.DIRECTION_NAME);
    }

    public List<Unit> list(int pageNum) {
        Pageable pageable = new PageRequest(pageNum, pageSize, sort);
        Page<Unit> unitPage = unitDAO.findAll(pageable);
        return (List<Unit>)unitPage.getContent();
    }

    public Unit view(Long unitId) {
        return unitDAO.findOne(unitId);
    }

    public Unit save(Unit unit) {
        return unitDAO.saveAndFlush(unit);
    }

    @Override
    public void delete(Long unitId) {
        unitDAO.delete(unitId);
        unitDAO.flush();
    }

    @Override
    public boolean exists(Long unitId) {
        return unitDAO.exists(unitId);
    }

    @Override
    public long count() {
        return unitDAO.count();
    }

    public int getPageSize() {
        return pageSize;
    }
}
