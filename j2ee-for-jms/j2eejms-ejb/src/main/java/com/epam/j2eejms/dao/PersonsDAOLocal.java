package com.epam.j2eejms.dao;

import java.util.List;

import javax.ejb.Local;

import com.epam.j2eejms.model.Person;

@Local
public interface PersonsDAOLocal {
    Person create(Person person);
    Person edit(Person person);
    void remove(Person person);
    Person find(Object id);
    List<Person> findAll();
}
