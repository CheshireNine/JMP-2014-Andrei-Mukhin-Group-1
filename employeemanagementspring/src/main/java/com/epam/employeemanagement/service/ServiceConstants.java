package com.epam.employeemanagement.service;

public final class ServiceConstants {

    public final static String PARAMETER_PROJECT_ID_NAME = "projectId";
    public final static String PARAMETER_UNIT_ID_NAME = "projectId";

    public final static String ATTRIBUTE_EMPLOYEE_LIST_NAME = "employeeList";
    public final static String ATTRIBUTE_EMPLOYEE_NAME = "currentEmployee";
    public final static String ATTRIBUTE_PROJECT_NAME = "currentProject";
    public final static String ATTRIBUTE_UNIT_NAME = "currentUnit";

    public final static String ATTRIBUTE_PROJECT_LIST_NAME = "projectList";
    public final static String ATTRIBUTE_ADDED_PROJECTS_NAME = "addedProjectList";
    public final static String ATTRIBUTE_UNIT_LIST_NAME = "unitList";
    public final static String ATTRIBUTE_STATUSES_NAME = "statusList";
    public final static String ATTRIBUTE_PAGES_COUNT_NAME = "pagesCount";
    public final static String ATTRIBUTE_PAGE_NUMBER_NAME = "page";
    public final static String ATTRIBUTE_SEARCH_VALUE_NAME = "searchValue";
    
    public final static String REDIRECT_NAME = "redirect:";
    public final static String FORWARD_NAME = "forward:";

    public final static String EMPLOYEES_PAGE_REST_MAPPING = "/employee?page=1";
    public final static String EMPLOYEE_EDIT_PAGE_REST_MAPPING = "/employee/";

    public final static String PROJECTS_PAGE_REST_MAPPING = "/project?page=1";
    public final static String UNITS_PAGE_REST_MAPPING = "/unit?page=1";

    public final static String HOME_PAGE_PATH = "home";
    public final static String EMPLOYEES_PAGE_PATH = "employees";
    public final static String PROJECTS_PAGE_PATH = "projects";
    public final static String UNITS_PAGE_PATH = "units";

    public final static String EMPLOYEE_VIEW_PAGE_PATH = "viewEmployee";
    public final static String EMPLOYEE_ADD_PAGE_PATH = "addEmployee";
    public final static String EMPLOYEE_EDIT_PAGE_PATH = "editEmployee";
    public final static String EMPLOYEE_FIND_PAGE_PATH = "findEmployee";
    public final static String EMPLOYEE_FIND_RESULT_PAGE_PATH = "findedEmployee";
    
    public final static String PROJECT_SELECT_PAGE_PATH = "selectProject";
    public final static String PROJECT_ADD_PAGE_PATH = "addProject";

    public final static String UNIT_SELECT_PAGE_PATH = "selectUnit";
    public final static String UNIT_ADD_PAGE_PATH = "addUnit";

    public final static String PERSONAL_EDIT_PAGE_PATH = "editPersonal";

    private ServiceConstants() {
    }

}
