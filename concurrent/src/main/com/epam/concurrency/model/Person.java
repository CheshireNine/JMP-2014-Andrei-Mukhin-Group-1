/**
 * 
 */
package com.epam.concurrency.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author I7eter
 *
 */
@XmlRootElement
public class Person {

	/**
	 * 
	 */

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
