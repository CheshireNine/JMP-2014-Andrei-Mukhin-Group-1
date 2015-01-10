package com.epam.employeemanagement.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.epam.employeemanagement.model.Employee;
import com.epam.employeemanagement.model.EmployeeStatus;
import com.epam.employeemanagement.model.Project;
import com.epam.employeemanagement.model.Unit;
import com.epam.employeemanagement.service.EmployeeService;
import com.epam.employeemanagement.service.ProjectService;
import com.epam.employeemanagement.service.ServiceConstants;
import com.epam.employeemanagement.service.UnitService;

@Controller
@SessionAttributes( { ServiceConstants.ATTRIBUTE_EMPLOYEE_NAME })
@RequestMapping("/employee")
public class EpmloyeeController {
//    private static final Logger LOG = Logger.getLogger(EpmloyeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UnitService unitService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(ServiceConstants.ATTRIBUTE_PAGE_NUMBER_NAME) String page,
            Model model, SessionStatus status) {
        status.setComplete();
        int pageNum = Integer.parseInt(page) - 1;
        model.addAttribute(ServiceConstants.ATTRIBUTE_EMPLOYEE_LIST_NAME, employeeService.list(pageNum));
        /* paging */
        model.addAttribute(ServiceConstants.ATTRIBUTE_PAGES_COUNT_NAME,
                Math.ceil((double) employeeService.count() / employeeService.getPageSize()));
        model.addAttribute(ServiceConstants.ATTRIBUTE_PAGE_NUMBER_NAME, pageNum);
        return ServiceConstants.EMPLOYEES_PAGE_PATH;
    }

    @RequestMapping(params="find=ByName")
    public String findByName(@RequestParam(ServiceConstants.ATTRIBUTE_SEARCH_VALUE_NAME) String name,
            @RequestParam(ServiceConstants.ATTRIBUTE_PAGE_NUMBER_NAME) String page, Model model) {
        int pageNum = Integer.parseInt(page) - 1;
        model.addAttribute(ServiceConstants.ATTRIBUTE_EMPLOYEE_LIST_NAME, employeeService.findByName(name, pageNum));
        model.addAttribute(ServiceConstants.ATTRIBUTE_SEARCH_VALUE_NAME, name);
        /* paging */
        model.addAttribute(ServiceConstants.ATTRIBUTE_PAGES_COUNT_NAME,
                Math.ceil((double)employeeService.countByName(name) / employeeService.getPageSize()));
        model.addAttribute(ServiceConstants.ATTRIBUTE_PAGE_NUMBER_NAME, pageNum);

        return ServiceConstants.EMPLOYEE_FIND_RESULT_PAGE_PATH;
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    public String findPage() {
        return ServiceConstants.EMPLOYEE_FIND_PAGE_PATH;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute(ServiceConstants.ATTRIBUTE_EMPLOYEE_NAME) Employee employee, SessionStatus status) {;
        employeeService.save(employee);
        status.setComplete();
        return ServiceConstants.REDIRECT_NAME + ServiceConstants.EMPLOYEES_PAGE_REST_MAPPING;
    }

    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String prepareAddEmployeePage(Model model) {
        Employee employee = new Employee();
        model.addAttribute(ServiceConstants.ATTRIBUTE_EMPLOYEE_NAME, employee);
        return ServiceConstants.EMPLOYEE_ADD_PAGE_PATH;
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public String view(@PathVariable String id, Model model) {
        Long employeeId = Long.parseLong(id);
        if (!model.containsAttribute(ServiceConstants.ATTRIBUTE_EMPLOYEE_NAME)) {
            Employee employee = employeeService.view(employeeId);
            model.addAttribute(ServiceConstants.ATTRIBUTE_EMPLOYEE_NAME, employee);
        }
        return ServiceConstants.EMPLOYEE_VIEW_PAGE_PATH;
    }

    @RequestMapping(value="{id}", method = RequestMethod.POST)
    public String edit(@PathVariable String id, Model model) {
        Long employeeId = Long.parseLong(id);

        if (!model.containsAttribute(ServiceConstants.ATTRIBUTE_EMPLOYEE_NAME)) {
            Employee employee = employeeService.view(employeeId);
            model.addAttribute(ServiceConstants.ATTRIBUTE_EMPLOYEE_NAME, employee);
        }
        return ServiceConstants.EMPLOYEE_EDIT_PAGE_PATH;
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable String id, Model model) {
        Long employeeId = Long.parseLong(id);
        if(employeeService.exists(employeeId)) {
            employeeService.delete(employeeId);
        }
        return ServiceConstants.REDIRECT_NAME + ServiceConstants.EMPLOYEES_PAGE_REST_MAPPING;
    }

    @RequestMapping(value="personal", method = RequestMethod.GET)
    public String editPersonal(@ModelAttribute(ServiceConstants.ATTRIBUTE_EMPLOYEE_NAME) Employee employee) {
        return ServiceConstants.PERSONAL_EDIT_PAGE_PATH;
    }

    @RequestMapping(value="/personal", method = RequestMethod.POST)
    public String acceptPersonalInfo(@ModelAttribute(ServiceConstants.ATTRIBUTE_EMPLOYEE_NAME) Employee employee,
            Model model) {
        employee.getPersonalInfo().setEmployee(employee);
        return ServiceConstants.FORWARD_NAME
                + ServiceConstants.EMPLOYEE_EDIT_PAGE_REST_MAPPING
                + employee.getEmployeeId();
    }

    @RequestMapping(value="project/select", method = RequestMethod.GET)
    public String listProjects(@RequestParam(ServiceConstants.ATTRIBUTE_PAGE_NUMBER_NAME) String page, Model model) {
        int pageNum = Integer.parseInt(page) - 1;
        Employee currentEmployee = (Employee) model.asMap().get(ServiceConstants.ATTRIBUTE_EMPLOYEE_NAME);
        model.addAttribute(ServiceConstants.ATTRIBUTE_ADDED_PROJECTS_NAME, currentEmployee.getProjects());
        model.addAttribute(ServiceConstants.ATTRIBUTE_PROJECT_LIST_NAME, projectService.list(pageNum));
        /* paging */
        model.addAttribute(ServiceConstants.ATTRIBUTE_PAGES_COUNT_NAME,
                Math.ceil((double) projectService.count() / projectService.getPageSize()));
        model.addAttribute(ServiceConstants.ATTRIBUTE_PAGE_NUMBER_NAME, pageNum);
        return ServiceConstants.PROJECT_SELECT_PAGE_PATH;
    }

    @RequestMapping(value="unit/select", method = RequestMethod.GET)
    public String listUnits(@RequestParam(ServiceConstants.ATTRIBUTE_PAGE_NUMBER_NAME) String page, Model model) {
        int pageNum = Integer.parseInt(page) - 1;
        model.addAttribute(ServiceConstants.ATTRIBUTE_UNIT_LIST_NAME, unitService.list(pageNum));
        /* paging */
        model.addAttribute(ServiceConstants.ATTRIBUTE_PAGES_COUNT_NAME,
                Math.ceil((double) unitService.count() / unitService.getPageSize()));
        model.addAttribute(ServiceConstants.ATTRIBUTE_PAGE_NUMBER_NAME, pageNum);
        return ServiceConstants.UNIT_SELECT_PAGE_PATH;
    }

    @RequestMapping(value="project/select", method = RequestMethod.POST)
    public String performSelect(@RequestParam(ServiceConstants.PARAMETER_PROJECT_ID_NAME) String id, Model model) {
        Long projectId = Long.parseLong(id);
        Employee currentEmployee = (Employee) model.asMap().get(ServiceConstants.ATTRIBUTE_EMPLOYEE_NAME);
        Project project = projectService.view(projectId);
        List<Project> projects = currentEmployee.getProjects();
        if(projects == null) {
            projects = new ArrayList<Project>();
        }
        projects.add(project);
        currentEmployee.setProjects(projects);

        return ServiceConstants.FORWARD_NAME
            + ServiceConstants.EMPLOYEE_EDIT_PAGE_REST_MAPPING
            + currentEmployee.getEmployeeId();
    }

    @RequestMapping(value="unit/select", method = RequestMethod.POST)
    public String performSelectUnit(@RequestParam(ServiceConstants.PARAMETER_UNIT_ID_NAME) String id,
            Model model) {
        Long unitId = Long.parseLong(id);
        Employee currentEmployee = (Employee) model.asMap().get(ServiceConstants.ATTRIBUTE_EMPLOYEE_NAME);
        Unit unit = unitService.view(unitId);
        currentEmployee.setUnit(unit);

        return ServiceConstants.FORWARD_NAME
            + ServiceConstants.EMPLOYEE_EDIT_PAGE_REST_MAPPING
            + currentEmployee.getEmployeeId();
    }

    @ModelAttribute(ServiceConstants.ATTRIBUTE_STATUSES_NAME)
    public HashMap<String, String> getStatusList() {
        HashMap<String, String> statuses = new HashMap<String, String>();
        for(EmployeeStatus status : EmployeeStatus.values()) {
            statuses.put(status.toString(), status.getStatus());
        }
        return statuses;
    }

}
