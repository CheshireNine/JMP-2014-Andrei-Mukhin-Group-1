package com.epam.employeemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.epam.employeemanagement.dao.EmployeeDAO;
import com.epam.employeemanagement.dao.ProjectDAO;
import com.epam.employeemanagement.model.Employee;
import com.epam.employeemanagement.model.Project;
import com.epam.employeemanagement.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private ProjectDAO projectDAO;

    @Value("${page.defaultorder}")
    private String direction;

    @Value("${page.size}")
    private int pageSize;

    private Sort sort;

    public EmployeeServiceImpl() {
    }

    @PostConstruct
    public void initEmployeeSort() {
        sort = new Sort(Sort.Direction.valueOf(direction), ServiceConstants.DIRECTION_LAST_NAME);
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<Employee> list(int pageNum) {
        Pageable pageable = new PageRequest(pageNum, pageSize, sort);
        Page<Employee> employeePage = employeeDAO.findAll(pageable);
        return (List<Employee>)employeePage.getContent();
    }

    public Employee view(Long employeeId) {
        return employeeDAO.findOne(employeeId);
    }

    public Employee save(Employee employee) {
        return employeeDAO.saveAndFlush(employee);
    }

    @Override
    public void delete(Long employeeId) {
        employeeDAO.delete(employeeId);
        employeeDAO.flush();
    }

    @Override
    public boolean exists(Long employeeId) {
        return employeeDAO.exists(employeeId);
    }

    @Override
    public long count() {
        return employeeDAO.count();
    }

    @Override
    public List<Employee> findByName(String name, int pageNum) {
        Pageable pageable = new PageRequest(pageNum, pageSize, sort);
        return employeeDAO.findByNameLike(name, pageable);
    }

    @Override
    public long countByName(String name) {
        return employeeDAO.countByNameLike(name);
    }

    @Override
    public Employee addProject(Employee employee, long projectId) {
        Project project = projectDAO.findOne(projectId);
        List<Project> projects = employee.getProjects();
        if(projects == null) {
            projects = new ArrayList<Project>();
        }
        projects.add(project);
        employee.setProjects(projects);
        return employeeDAO.save(employee);
    }
}
