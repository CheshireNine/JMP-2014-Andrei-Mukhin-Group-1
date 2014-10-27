package com.epam.concurrency.menu.action;

import com.epam.concurrency.SelectionForm;
import com.epam.concurrency.services.PersonService;
import com.epam.concurrency.utils.ConsoleManager;

public class AddClientAction implements IMenuItemAction {
	private static PersonService service = new PersonService();
	
	private SelectionForm form;
	
	public AddClientAction(SelectionForm form) {
		this.form = form;
	}

	@Override
	public void execute() {
		//TODO validation needed
		String firstName = ConsoleManager.getInput("First name: "),
				lastName = ConsoleManager.getInput("Last name: ");
		if(service.addPerson(firstName, lastName)) {
			ConsoleManager.writeLine("Saved.");
		} else {
			ConsoleManager.writeLine("Failed to save!");
		}
		
	}

}
