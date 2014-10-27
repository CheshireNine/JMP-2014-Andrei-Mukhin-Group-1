package com.epam.concurrency.menu.action;

import java.util.List;

import com.epam.concurrency.SelectionForm;
import com.epam.concurrency.model.Person;
import com.epam.concurrency.services.PersonService;
import com.epam.concurrency.utils.ConsoleManager;

public class ShowPersonsAction implements IMenuItemAction {
	private static PersonService service = new PersonService();

	private SelectionForm form;

	public ShowPersonsAction(SelectionForm form) {
		this.form = form;
	}

	@Override
	public void execute() {
		List<Person> persons = service.getList();
		printPersons(persons);
		
	}
	
	public static void printPersons(List<Person> persons) {
		if (persons != null) {
			ConsoleManager.writeLine("#\tPersonId\tName");
			int number = 1;
			for(Person person : persons) {
				ConsoleManager.writeLine(number
						+ "\t" + person.getPersonId()
						+ "\t" + person.getFirstName()
						+ "\t" + person.getLastName());
				number++;
			}
		} else {
			ConsoleManager.writeLine("<Empty person list>");
		}
	}

}
