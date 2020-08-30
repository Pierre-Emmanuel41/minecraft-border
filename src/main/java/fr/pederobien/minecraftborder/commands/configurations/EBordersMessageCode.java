package fr.pederobien.minecraftborder.commands.configurations;

import fr.pederobien.minecraftdictionary.impl.Permission;
import fr.pederobien.minecraftdictionary.interfaces.IMinecraftMessageCode;

public enum EBordersMessageCode implements IMinecraftMessageCode {
	// Code for command borders
	BORDERS__EXPLANATION,

	// Code for command add
	ADD_BORDERS__EXPLANATION, ADD_BORDERS__BORDER_DOES_NOT_EXIST, ADD_BORDERS__BORDER_ALREADY_REGISTERED, ADD_BORDERS__NO_BORDER_ADDED, ADD_BORDERS__ONE_BORDER_ADDED,
	ADD_BORDERS__SEVERAL_BORDERS_ADDED,

	// Code for command remove
	REMOVE_BORDERS__EXPLANATION, REMOVE_BORDERS__BORDER_NOT_REGISTERED, REMOVE_BORDERS__NO_BORDER_REMOVED, REMOVE_BORDERS__ONE_BORDER_REMOVED,
	REMOVE_BORDERS__SEVERAL_BORDERS_REMOVED,

	// Code for command list
	LIST_BORDERS__EXPLANATION, LIST_BORDERS__ALL_REGISTERED_BORDERS, LIST_BORDERS__REGISTERED_BORDER_FOR_WORLD,

	// Code for command details
	DETAILS__EXPLANATION, DETAILS_BORDERS__DETAILS_ON_ALL_REGISTERED_BORDERS, DETAILS_BORDERS__BORDER_NOT_REGISTERED, DETAILS_BORDERS__DETAILS_ON_SOME_BORDERS,

	// Code for command reload
	RELOAD_BORDERS__EXPLANATION, RELOAD_BORDERS__BORDER_NOT_REGISTERED, RELOAD_BORDERS__BORDER_DOES_NOT_EXIST, RELOAD_BORDERS__ANY_BORDER_RELOADED,
	RELOAD_BORDERS__ONE_BORDER_RELOADED, RELOAD_BORDERS__SEVERAL_BORDERS_RELOADED;

	private Permission permission;

	private EBordersMessageCode() {
		this(Permission.OPERATORS);
	}

	private EBordersMessageCode(Permission permission) {
		this.permission = permission;
	}

	@Override
	public String value() {
		return toString();
	}

	@Override
	public Permission getPermission() {
		return permission;
	}

	@Override
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
}
