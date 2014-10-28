/**
 * 
 */
package com.epam.concurrency.menu;

import com.epam.concurrency.menu.action.IMenuItemAction;
import com.epam.concurrency.utils.ConsoleManager;

/**
 * @author I7eter
 *
 */
public class MenuItem extends AbstractMenuItem {

	public MenuItem(MenuItemType type) {
		super(type);
	}

	public MenuItem(String name, IMenuItemAction action) {
		super(name, action);
	}

	public MenuItem(String name) {
		super(name);
	}

	public MenuItem(String name, IMenuItemAction action, MenuItemType type) {
		super(name, action, type);
	}

	public void draw(int num){
		ConsoleManager.writeLine(this.getName().isEmpty() ?
				AbstractMenuItem.separator : " - " + this.getName());
	}

	public void execute() throws IllegalArgumentException {
		getAction().execute();
	}

}
