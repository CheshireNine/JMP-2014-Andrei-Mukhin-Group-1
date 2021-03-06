package com.epam.employeemanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.epam.employeemanagement.model.Unit;
import com.epam.employeemanagement.service.UnitService;

@Controller
@SessionAttributes( { ControllerConstants.ATTRIBUTE_UNIT_NAME }) 
@RequestMapping("/unit")
public class UnitController {

    @Autowired
    private UnitService unitService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(ControllerConstants.ATTRIBUTE_PAGE_NUMBER_NAME) String page, Model model, SessionStatus status) {
        status.setComplete();
        int pageNum = Integer.parseInt(page) - 1;
        model.addAttribute(ControllerConstants.ATTRIBUTE_UNIT_LIST_NAME, unitService.list(pageNum));
        /* paging */
        model.addAttribute(ControllerConstants.ATTRIBUTE_PAGES_COUNT_NAME,
                Math.ceil((double) unitService.count() / unitService.getPageSize()));
        model.addAttribute(ControllerConstants.ATTRIBUTE_PAGE_NUMBER_NAME, pageNum);

        return ControllerConstants.UNITS_PAGE_PATH;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveUnit(@Valid @ModelAttribute(ControllerConstants.ATTRIBUTE_UNIT_NAME) Unit unit,
            BindingResult bindingResult,
            SessionStatus status) {
        if (bindingResult.hasErrors()) {
            return ControllerConstants.UNIT_ADD_PAGE_PATH;
        }
        unitService.save(unit);
        status.setComplete();
        return ControllerConstants.REDIRECT_NAME + ControllerConstants.UNITS_PAGE_REST_MAPPING;
    }

    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String prepareAddUnitPage(Model model) {
        Unit unit = new Unit();
        model.addAttribute(ControllerConstants.ATTRIBUTE_UNIT_NAME, unit);
        return ControllerConstants.UNIT_ADD_PAGE_PATH;
    }

    @RequestMapping(value="{id}", method = RequestMethod.POST)
    public String edit(@PathVariable String id, Model model) {
        Long unitId = Long.parseLong(id);
        Unit unit = unitService.view(unitId);
        model.addAttribute(ControllerConstants.ATTRIBUTE_EMPLOYEE_NAME, unit);
        //return ServiceConstants.UNIT_EDIT_PAGE_PATH;  //not implemented yet
        return ControllerConstants.UNITS_PAGE_PATH;
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable String id, Model model) {
        Long unitId = Long.parseLong(id);
        if(unitService.exists(unitId)) {
            unitService.delete(unitId);
        }
        return ControllerConstants.REDIRECT_NAME + ControllerConstants.UNITS_PAGE_REST_MAPPING;
    }

}
