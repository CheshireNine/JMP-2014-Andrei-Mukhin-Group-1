package com.epam.concurrency.comparator;

import java.util.Comparator;

import com.epam.concurrency.model.Person;

public final class PersonComparator implements Comparator<Person> {

	public PersonComparator() {
	}

	@Override
	public int compare(Person o1, Person o2) {
		return o1.getPersonId() < o2.getPersonId() ? -1 :
			o1.getPersonId() > o2.getPersonId() ? 1 : 0;
	}

}
