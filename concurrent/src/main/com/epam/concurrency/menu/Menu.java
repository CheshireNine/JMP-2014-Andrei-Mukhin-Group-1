/**
 * 
 */
package com.epam.concurrency.menu;

import java.util.ArrayList;

import com.epam.concurrency.menu.action.IMenuItemAction;
import com.epam.concurrency.utils.ConsoleManager;

/**
 * @author I7eter
 *
 */
public final class Menu extends AbstractMenuItem {
	private ArrayList<AbstractMenuItem> subMenus = new ArrayList<AbstractMenuItem>();

	public Menu(String name, IMenuItemAction action){
		super(name, action);
	}

	public Menu(String name) {
		super(name);
	}

	public Menu(MenuItemType type) {
		super(type);
	}

	public Menu(String name, IMenuItemAction action, MenuItemType type) {
		super(name, action, type);
	}

	public boolean addItem(AbstractMenuItem item){
		return subMenus.contains(item) ? false : subMenus.add(item);
	}

	public void execute() throws IllegalArgumentException {
		boolean isRepeatNeeded = true;
		while(isRepeatNeeded) {
			getAction().execute();
			for (int i = 0; i < subMenus.size(); ++i) {
				subMenus.get(i).draw(i + 1);
			}

			try {
				int chosenMenuItemNum = getUserChoice() - 1;
				ConsoleManager.clearConsole();
				subMenus.get(chosenMenuItemNum).execute();
				isRepeatNeeded = isExitSelected() && checkMenuType(chosenMenuItemNum);
			} catch (IllegalArgumentException e){
				ConsoleManager.writeLine("Your choice has to be number from the list");
			}
		}
	}

	public boolean checkMenuType(int chosenMenuItemNum) {
		AbstractMenuItem item = subMenus.get(chosenMenuItemNum);

		if(MenuItemType.BACK.equals(item.getType())) {
			return false;
		} else if(MenuItemType.EXIT.equals(item.getType())) {
			setExitSelected(true);
			return false;
		}

		return true;
	}

	public void draw(int num){
		ConsoleManager.writeLine(" > " +" "+ getName());        
	}

	private int getUserChoice() throws IllegalArgumentException {
		int action = Integer.parseInt(ConsoleManager.getInput("Your Choice"));
		if ((action < 1 ) || (action > subMenus.size())) {
			throw new IllegalArgumentException();
		}
		return action;
	}

}
