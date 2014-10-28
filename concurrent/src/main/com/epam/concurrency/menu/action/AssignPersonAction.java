package com.epam.concurrency.menu.action;

import java.util.Collections;
import java.util.List;

import com.epam.concurrency.comparator.PersonComparator;
import com.epam.concurrency.form.SelectionForm;
import com.epam.concurrency.model.Account;
import com.epam.concurrency.model.Person;
import com.epam.concurrency.services.AccountService;
import com.epam.concurrency.services.PersonService;
import com.epam.concurrency.utils.ConsoleManager;


public class AssignPersonAction implements IMenuItemAction {
	private static AccountService service = new AccountService();
	private static PersonService personService = new PersonService();

	private SelectionForm form;

	public AssignPersonAction(SelectionForm form) {
		this.form = form;
	}

	@Override
	public void execute() {
		List<Person> persons = personService.getList();
		Collections.sort(persons, new PersonComparator());
		Account account = service.fetchById(form.getAccountId());
		ShowAccountAction.printAccount(account);
		ShowPersonsAction.printPersons(persons);
		
		int personNum = 0;
		while(personNum == 0) {
			try {
				personNum = Integer.parseInt(ConsoleManager
						.getInput("Input person num"));
			} catch(NumberFormatException ex) {
				ConsoleManager.writeLine("Should be a valid number");
			}
		}

		account.setOwner(persons.get(personNum - 1));
		service.editAccount(account);
	}


}
