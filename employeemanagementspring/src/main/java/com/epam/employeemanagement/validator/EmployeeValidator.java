package com.epam.employeemanagement.validator;

import org.hibernate.validator.internal.constraintvalidators.EmailValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.epam.employeemanagement.model.Employee;

public class EmployeeValidator implements Validator {

    public EmployeeValidator() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;

        if((employee.getFirstName() == null)
                || (employee.getFirstName().trim().length() < 1)
                || (employee.getFirstName().length() > 50)) {
            errors.rejectValue("firstName", "first.name.size");
        }

        if((employee.getLastName() == null)
                || (employee.getLastName().trim().length() < 1)
                || (employee.getLastName().length() > 50)) {
            errors.rejectValue("lastName", "last.name.size");
        }

        if(employee.getAddress() != null) {
            if((employee.getAddress().getCity() != null)
                    && (employee.getAddress().getCity().length() > 20)) {
                errors.rejectValue("address.city", "address.city.size");
            } else if((employee.getAddress().getStreet() != null)
                    && (employee.getAddress().getStreet().length() > 20)) {
                errors.rejectValue("address.street", "address.street.size");
            }
        }

        if(employee.getPersonalInfo() != null) {
            EmailValidator emailValidator = new EmailValidator();
            if(!emailValidator.isValid(employee.getPersonalInfo().getEmail(), null)) {
                errors.rejectValue("personalInfo.email", "personal.email");
            } else if((employee.getPersonalInfo().getPhoneNumber() != null)
                    && (employee.getPersonalInfo().getPhoneNumber().length() > 20)) {
                errors.rejectValue("personalInfo.phoneNumber", "personal.phone.size");
            } else if((employee.getPersonalInfo().getEducation() != null)
                    && (employee.getPersonalInfo().getEducation().length() > 20000)) {
                errors.rejectValue("personalInfo.education", "personal.education.size");
            }
        }
    }

}
