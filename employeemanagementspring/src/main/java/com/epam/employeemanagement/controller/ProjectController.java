package com.epam.employeemanagement.controller;

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

import com.epam.employeemanagement.model.Project;
import com.epam.employeemanagement.service.ProjectService;
import com.epam.employeemanagement.service.ServiceConstants;

@Controller
@SessionAttributes( { ServiceConstants.ATTRIBUTE_PROJECT_NAME }) 
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(ServiceConstants.ATTRIBUTE_PAGE_NUMBER_NAME) String page, Model model, SessionStatus status) {
        status.setComplete();
        int pageNum = Integer.parseInt(page) - 1;
        model.addAttribute(ServiceConstants.ATTRIBUTE_PROJECT_LIST_NAME, projectService.list(pageNum));
        /* paging */
        model.addAttribute(ServiceConstants.ATTRIBUTE_PAGES_COUNT_NAME,
                Math.ceil((double) projectService.count() / projectService.getPageSize()));
        model.addAttribute(ServiceConstants.ATTRIBUTE_PAGE_NUMBER_NAME, pageNum);

        return ServiceConstants.PROJECTS_PAGE_PATH;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveProject(@ModelAttribute(ServiceConstants.ATTRIBUTE_PROJECT_NAME) Project project, SessionStatus status) {
        projectService.save(project);
        status.setComplete();
        return ServiceConstants.REDIRECT_NAME + ServiceConstants.PROJECTS_PAGE_REST_MAPPING;
    }

    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String prepareAddProjectPage(Model model) {
        Project project = new Project();
        model.addAttribute(ServiceConstants.ATTRIBUTE_PROJECT_NAME, project);
        return ServiceConstants.PROJECT_ADD_PAGE_PATH;
    }

    @RequestMapping(value="{id}", method = RequestMethod.POST)
    public String edit(@PathVariable String id, Model model) {
        Long projectId = Long.parseLong(id);
        Project project = projectService.view(projectId);
        model.addAttribute(ServiceConstants.ATTRIBUTE_EMPLOYEE_NAME, project);
        //return ServiceConstants.PROJECT_EDIT_PAGE_PATH;  //not implemented yet
        return ServiceConstants.PROJECTS_PAGE_REST_MAPPING;
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable String id, Model model) {
        Long projectId = Long.parseLong(id);
        if(projectService.exists(projectId)) {
            projectService.delete(projectId);
        }
        return ServiceConstants.REDIRECT_NAME + ServiceConstants.PROJECTS_PAGE_REST_MAPPING;
    }

}
