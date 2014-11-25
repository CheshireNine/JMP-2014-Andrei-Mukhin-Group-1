package com.epam.banksystem.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "person")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="person_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long personId;
	private String firstName;
	private String lastName;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
