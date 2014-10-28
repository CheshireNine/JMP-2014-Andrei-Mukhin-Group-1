package com.epam.concurrency.menu;

import com.epam.concurrency.menu.action.EmptyAction;
import com.epam.concurrency.menu.action.IMenuItemAction;

public abstract class AbstractMenuItem {

	public static final String separator = "=============";

	private IMenuItemAction action;
	private MenuItemType type;
	private String name;
	private boolean isExitSelected = false;

	public AbstractMenuItem(String name) {
		super();
		this.type = MenuItemType.DEFAULT;
		this.name = ( null == name ) ? "" : name;
		this.action = new EmptyAction();
	}
	
	public AbstractMenuItem(String name, IMenuItemAction action) {
		super();
		this.type = MenuItemType.DEFAULT;
		this.name = ( null == name ) ? "" : name;
		this.action = action;
	}

	public AbstractMenuItem(String name, IMenuItemAction action,
			MenuItemType type) {
		super();
		this.action = action;
		this.type = type;
		this.name = name;
	}

	public AbstractMenuItem(MenuItemType type) {
		super();
		this.type = type;
		this.name = (MenuItemType.BACK.equals(this.type)) ||
				(MenuItemType.EXIT.equals(this.type)) ? this.type.toString() : "";
		this.action = new EmptyAction();
	}

	public MenuItemType getType() {
		return type;
	}

	public void setType(MenuItemType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getSeparator() {
		return separator;
	}

	public IMenuItemAction getAction() {
		return action;
	}

	public void setAction(IMenuItemAction action) {
		this.action = action;
	}

	public abstract void draw(int num);
	
	public abstract void execute() throws IllegalArgumentException;

	public boolean isExitSelected() {
		return isExitSelected;
	}

	public void setExitSelected(boolean isExitSelected) {
		this.isExitSelected = isExitSelected;
	}
}
