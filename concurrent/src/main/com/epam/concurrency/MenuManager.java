package com.epam.concurrency;

import com.epam.concurrency.menu.AbstractMenuItem;
import com.epam.concurrency.menu.Menu;
import com.epam.concurrency.menu.MenuItem;
import com.epam.concurrency.menu.MenuItemType;
import com.epam.concurrency.menu.action.AddAccountAction;
import com.epam.concurrency.menu.action.AddClientAction;
import com.epam.concurrency.menu.action.AssignPersonAction;
import com.epam.concurrency.menu.action.SelectAccountAction;
import com.epam.concurrency.menu.action.SelectBankAction;
import com.epam.concurrency.menu.action.ShowAccountsAction;
import com.epam.concurrency.menu.action.ShowPersonsAction;

public class MenuManager {

	private MenuManager() {
	}
	
	public static Menu createMenu() {
		SelectionForm form = new SelectionForm();
		Menu root = new Menu("root");


		Menu banks = new Menu("Banks", new SelectBankAction(form));
        root.addItem(banks);
		/** creating menu 'clients'*/
        Menu clients = new Menu("Clients", new ShowPersonsAction(form));
        banks.addItem(clients);

        AbstractMenuItem addClientMenu = new MenuItem("Add Client", new AddClientAction(form));
        clients.addItem(addClientMenu);
        clients.addItem(new MenuItem(MenuItemType.BACK));
        clients.addItem(new MenuItem(MenuItemType.EXIT));
        
        Menu accounts = new Menu("Accounts", new ShowAccountsAction(form));
        banks.addItem(accounts);
        Menu selectAccountMenu = new Menu("Select Account", new SelectAccountAction(form));
        Menu addAccountMenu = new Menu("Add Account", new AddAccountAction(form));
        Menu assignPersonMenu = new Menu("Assign Person", new AssignPersonAction(form));
        assignPersonMenu.addItem(new MenuItem(MenuItemType.BACK));
        assignPersonMenu.addItem(new MenuItem(MenuItemType.EXIT));
        addAccountMenu.addItem(assignPersonMenu);
        addAccountMenu.addItem(new MenuItem(MenuItemType.BACK));
        addAccountMenu.addItem(new MenuItem(MenuItemType.EXIT));

        Menu exchangeMenu = new Menu("Exchange currency");
        selectAccountMenu.addItem(assignPersonMenu);
        selectAccountMenu.addItem(exchangeMenu);
        selectAccountMenu.addItem(new MenuItem(MenuItemType.BACK));
        selectAccountMenu.addItem(new MenuItem(MenuItemType.EXIT));
        accounts.addItem(addAccountMenu);
        accounts.addItem(selectAccountMenu);
        accounts.addItem(new MenuItem(MenuItemType.BACK));
        accounts.addItem(new MenuItem(MenuItemType.EXIT));
        
        Menu currencies = new Menu("Currencies");
        banks.addItem(currencies);
        AbstractMenuItem addCurrencyMenu = new MenuItem("Add Currency");
        currencies.addItem(addCurrencyMenu);
        currencies.addItem(new MenuItem(MenuItemType.BACK));
        currencies.addItem(new MenuItem(MenuItemType.EXIT));

        banks.addItem(new MenuItem(MenuItemType.EXIT));
        root.addItem(new MenuItem(MenuItemType.EXIT));

        return root;
	}

}
