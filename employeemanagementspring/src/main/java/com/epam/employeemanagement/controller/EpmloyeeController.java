package com.epam.employeemanagement.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
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
import com.epam.employeemanagement.service.UnitService;

@Controller
@SessionAttributes({ ControllerConstants.ATTRIBUTE_EMPLOYEE_NAME, ControllerConstants.ATTRIBUTE_PERSONAL_INFO_NAME })
@RequestMapping("/employee")
public class EpmloyeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UnitService unitService;

    @Autowired
    @Qualifier("employeeValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(ControllerConstants.ATTRIBUTE_PAGE_NUMBER_NAME) String page,
            Model model, SessionStatus status) {
        status.setComplete();
        int pageNum = Integer.parseInt(page) - 1;
        model.addAttribute(ControllerConstants.ATTRIBUTE_EMPLOYEE_LIST_NAME, employeeService.list(pageNum));
        /* paging */
        model.addAttribute(ControllerConstants.ATTRIBUTE_PAGES_COUNT_NAME,
                Math.ceil((double) employeeService.count() / employeeService.getPageSize()));
        model.addAttribute(ControllerConstants.ATTRIBUTE_PAGE_NUMBER_NAME, pageNum);
        return ControllerConstants.EMPLOYEES_PAGE_PATH;
    }

    @RequestMapping(params="find=ByName")
    public String findByName(@RequestParam(ControllerConstants.ATTRIBUTE_SEARCH_VALUE_NAME) String name,
            @RequestParam(ControllerConstants.ATTRIBUTE_PAGE_NUMBER_NAME) String page, Model model) {
        int pageNum = Integer.parseInt(page) - 1;
        model.addAttribute(ControllerConstants.ATTRIBUTE_EMPLOYEE_LIST_NAME, employeeService.findByName(name, pageNum));
        model.addAttribute(ControllerConstants.ATTRIBUTE_SEARCH_VALUE_NAME, name);
        /* paging */
        model.addAttribute(ControllerConstants.ATTRIBUTE_PAGES_COUNT_NAME,
                Math.ceil((double)employeeService.countByName(name) / employeeService.getPageSize()));
        model.addAttribute(ControllerConstants.ATTRIBUTE_PAGE_NUMBER_NAME, pageNum);

        return ControllerConstants.EMPLOYEE_FIND_RESULT_PAGE_PATH;
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    public String findPage() {
        return ControllerConstants.EMPLOYEE_FIND_PAGE_PATH;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveEmployee(@RequestHeader(value = "referer", required = false) final String referer,
            @Validated @ModelAttribute(ControllerConstants.ATTRIBUTE_EMPLOYEE_NAME) Employee employee,
            BindingResult bindingResult,
            SessionStatus status) {
        if (bindingResult.hasErrors()) {
            if(referer.contains(ControllerConstants.EMPLOYEES_ADD_PAGE_REST_MAPPING)) {
                return ControllerConstants.EMPLOYEE_ADD_PAGE_PATH;
            } else {
                return ControllerConstants.EMPLOYEE_EDIT_PAGE_PATH;
            }
        }
        employeeService.save(employee);
        status.setComplete();
        return ControllerConstants.REDIRECT_NAME + ControllerConstants.EMPLOYEES_PAGE_REST_MAPPING;
    }

    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String prepareAddEmployeePage(Model model) {
        Employee employee = new Employee();
        model.addAttribute(ControllerConstants.ATTRIBUTE_EMPLOYEE_NAME, employee);
        return ControllerConstants.EMPLOYEE_ADD_PAGE_PATH;
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public String view(@PathVariable String id, Model model) {
        Long employeeId = Long.parseLong(id);
        if (!model.containsAttribute(ControllerConstants.ATTRIBUTE_EMPLOYEE_NAME)) {
            Employee employee = employeeService.view(employeeId);
            model.addAttribute(ControllerConstants.ATTRIBUTE_EMPLOYEE_NAME, employee);
        }
        return ControllerConstants.EMPLOYEE_VIEW_PAGE_PATH;
    }

    @RequestMapping(value="{id}", method = RequestMethod.POST)
    public String edit(@PathVariable String id, Model model) {
        Long employeeId = Long.parseLong(id);

        if (!model.containsAttribute(ControllerConstants.ATTRIBUTE_EMPLOYEE_NAME)) {
            Employee employee = employeeService.view(employeeId);
            model.addAttribute(ControllerConstants.ATTRIBUTE_EMPLOYEE_NAME, employee);
        }
        return ControllerConstants.EMPLOYEE_EDIT_PAGE_PATH;
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable String id, Model model) {
        Long employeeId = Long.parseLong(id);
        if(employeeService.exists(employeeId)) {
            employeeService.delete(employeeId);
        }
        return ControllerConstants.REDIRECT_NAME + ControllerConstants.EMPLOYEES_PAGE_REST_MAPPING;
    }

    @RequestMapping(value="personal", method = RequestMethod.GET)
    public String editPersonal(@Validated @ModelAttribute(ControllerConstants.ATTRIBUTE_EMPLOYEE_NAME) Employee employee,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ControllerConstants.EMPLOYEE_EDIT_PAGE_PATH;
        }
        return ControllerConstants.PERSONAL_EDIT_PAGE_PATH;
    }

    @RequestMapping(value="personal", method = RequestMethod.POST)
    public String acceptPersonalInfo(@Validated @ModelAttribute(ControllerConstants.ATTRIBUTE_EMPLOYEE_NAME) Employee employee,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ControllerConstants.PERSONAL_EDIT_PAGE_PATH;
        }
        employee.getPersonalInfo().setEmployee(employee);
        return ControllerConstants.FORWARD_NAME
                + ControllerConstants.EMPLOYEE_EDIT_PAGE_REST_MAPPING
                + employee.getEmployeeId();
    }

    @RequestMapping(value="project/select", method = RequestMethod.GET)
    public String listProjects(@RequestParam(ControllerConstants.ATTRIBUTE_PAGE_NUMBER_NAME) String page, Model model) {
        int pageNum = Integer.parseInt(page) - 1;
        Employee currentEmployee = (Employee) model.asMap().get(ControllerConstants.ATTRIBUTE_EMPLOYEE_NAME);
        model.addAttribute(ControllerConstants.ATTRIBUTE_ADDED_PROJECTS_NAME, currentEmployee.getProjects());
        model.addAttribute(ControllerConstants.ATTRIBUTE_PROJECT_LIST_NAME, projectService.list(pageNum));
        /* paging */
        model.addAttribute(ControllerConstants.ATTRIBUTE_PAGES_COUNT_NAME,
                Math.ceil((double) projectService.count() / projectService.getPageSize()));
        model.addAttribute(ControllerConstants.ATTRIBUTE_PAGE_NUMBER_NAME, pageNum);
        return ControllerConstants.PROJECT_SELECT_PAGE_PATH;
    }

    @RequestMapping(value="unit/select", method = RequestMethod.GET)
    public String listUnits(@RequestParam(ControllerConstants.ATTRIBUTE_PAGE_NUMBER_NAME) String page, Model model) {
        int pageNum = Integer.parseInt(page) - 1;
        model.addAttribute(ControllerConstants.ATTRIBUTE_UNIT_LIST_NAME, unitService.list(pageNum));
        /* paging */
        model.addAttribute(ControllerConstants.ATTRIBUTE_PAGES_COUNT_NAME,
                Math.ceil((double) unitService.count() / unitService.getPageSize()));
        model.addAttribute(ControllerConstants.ATTRIBUTE_PAGE_NUMBER_NAME, pageNum);
        return ControllerConstants.UNIT_SELECT_PAGE_PATH;
    }

    @RequestMapping(value="project/select", method = RequestMethod.POST)
    public String performSelect(@RequestParam(ControllerConstants.PARAMETER_PROJECT_ID_NAME) String id, Model model) {
        Long projectId = Long.parseLong(id);
        Employee currentEmployee = (Employee) model.asMap().get(ControllerConstants.ATTRIBUTE_EMPLOYEE_NAME);
        Project project = projectService.view(projectId);
        List<Project> projects = currentEmployee.getProjects();
        if(projects == null) {
            projects = new ArrayList<Project>();
        }
        projects.add(project);
        currentEmployee.setProjects(projects);

        return ControllerConstants.FORWARD_NAME
            + ControllerConstants.EMPLOYEE_EDIT_PAGE_REST_MAPPING
            + currentEmployee.getEmployeeId();
    }

    @RequestMapping(value="unit/select", method = RequestMethod.POST)
    public String performSelectUnit(@RequestParam(ControllerConstants.PARAMETER_UNIT_ID_NAME) String id,
            Model model) {
        Long unitId = Long.parseLong(id);
        Employee currentEmployee = (Employee) model.asMap().get(ControllerConstants.ATTRIBUTE_EMPLOYEE_NAME);
        Unit unit = unitService.view(unitId);
        currentEmployee.setUnit(unit);

        return ControllerConstants.FORWARD_NAME
            + ControllerConstants.EMPLOYEE_EDIT_PAGE_REST_MAPPING
            + currentEmployee.getEmployeeId();
    }

    @ModelAttribute(ControllerConstants.ATTRIBUTE_STATUSES_NAME)
    public HashMap<String, String> getStatusList() {
        HashMap<String, String> statuses = new HashMap<String, String>();
        for(EmployeeStatus status : EmployeeStatus.values()) {
            statuses.put(status.toString(), status.getStatus());
        }
        return statuses;
    }

}
