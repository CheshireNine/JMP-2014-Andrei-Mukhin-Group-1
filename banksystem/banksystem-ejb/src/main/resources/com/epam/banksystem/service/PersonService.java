package com.epam.banksystem.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import com.epam.banksystem.dao.PersonsDAOLocal;
import com.epam.banksystem.form.UserDataLocal;
import com.epam.banksystem.model.Account;
import com.epam.banksystem.model.Person;
import com.epam.banksystem.requesthandler.HandlerConstants;

@Stateless
public class PersonService implements ActionService {
    @EJB
    PersonsDAOLocal personDAO;

    public PersonService() {
    }

    public String execute(HttpServletRequest req) {
        String actionName = req.getParameter(HandlerConstants.PARAMETER_ACTION_NAME);
        String page = null;
        if(HandlerConstants.PARAMETER_ACTION_VIEW_ALL.equals(actionName)) {
            page = prepareViewAllPersonsPage(req);
        } else if(HandlerConstants.PARAMETER_ACTION_SELECT.equals(actionName)) {
            page = prepareSelectPersonsPage(req);
        } else if(HandlerConstants.PARAMETER_ACTION_PERFORM_SELECT.equals(actionName)) {
            page = performPersonSelect(req);
        } else if(HandlerConstants.PARAMETER_ACTION_ADD.equals(actionName)) {
            page = prepareAddPersonPage(req);
        } else if(HandlerConstants.PARAMETER_ACTION_SAVE.equals(actionName)) {
            page = performSavePerson(req);
        }

        return page;
    }

    private String prepareAddPersonPage(HttpServletRequest req) {
        return ServiceConstants.PERSON_ADD_PAGE_PATH;
    }

    public String prepareViewAllPersonsPage(HttpServletRequest req) {
        List<Person> persons = personDAO.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_PERSON_LIST_NAME, persons);
        return ServiceConstants.PERSONS_PAGE_PATH;
    }

    public String prepareSelectPersonsPage(HttpServletRequest req) {
        List<Person> persons = personDAO.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_PERSON_LIST_NAME, persons);
        return ServiceConstants.PERSON_SELECT_PAGE_PATH;
    }
    
    private String performPersonSelect(HttpServletRequest req) {
        UserDataLocal dataBean = (UserDataLocal)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        Account account = dataBean.getCurrentAccount();
        String  id = req.getParameter(ServiceConstants.PARAMETER_PERSON_ID_NAME);
        if(id != null) {
            long personId = Long.parseLong(id);
            Person owner = personDAO.find(personId);
            account.setOwner(owner);
        }
        dataBean.setCurrentAccount(account);

        return ServiceConstants.ACCOUNT_EDIT_PAGE_PATH;
    }
    
    private String performSavePerson(HttpServletRequest req) {
        String firstName = req.getParameter(ServiceConstants.PARAMETER_FIRST_NAME);
        String lastName = req.getParameter(ServiceConstants.PARAMETER_LAST_NAME);
        UserDataLocal dataBean = (UserDataLocal)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        Person person = dataBean.getCurrentPerson();
        boolean isNewPerson = false;
        if(person == null) {
            person = new Person();
            isNewPerson = true;
        }
        person.setFirstName(firstName);
        person.setLastName(lastName);
        if (isNewPerson) {
            personDAO.create(person);
        } else {
            personDAO.edit(person);
        }

        
        dataBean.setCurrentPerson(null);
        List<Person> persons = personDAO.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_PERSON_LIST_NAME, persons);

        return ServiceConstants.PERSONS_PAGE_PATH;
    }

}
