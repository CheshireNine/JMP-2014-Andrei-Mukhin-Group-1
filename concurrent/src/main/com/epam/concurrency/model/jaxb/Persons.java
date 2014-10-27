package com.epam.concurrency.model.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.concurrency.model.Person;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "persons")
public class Persons {

	@XmlElement(name = "person", type = Person.class)
	private List<Person> persons = new ArrayList<Person>();
	
	public Persons() {
	}

	public Persons(List<Person> persons) {
		super();
		this.persons = persons;
	}


	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

}
