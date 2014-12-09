package com.epam.employeemanagement.form;

import com.epam.employeemanagement.model.Employee;
import com.epam.employeemanagement.model.Project;
import com.epam.employeemanagement.model.Unit;


public class UserData {

    private Employee currentEmployee;
    private Project currentProject;
    private Unit currentUnit;

    public UserData() {
    }

    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    public void setCurrentEmployee(Employee currentEmployee) {
        this.currentEmployee = currentEmployee;
    }

    public Project getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(Project currentProject) {
        this.currentProject = currentProject;
    }

    public Unit getCurrentUnit() {
        return currentUnit;
    }

    public void setCurrentUnit(Unit currentUnit) {
        this.currentUnit = currentUnit;
    }
}
