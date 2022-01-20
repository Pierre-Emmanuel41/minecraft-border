package fr.pederobien.minecraft.border.commands.borders;

import fr.pederobien.minecraft.dictionary.impl.PlayerGroup;
import fr.pederobien.minecraft.dictionary.interfaces.IMinecraftCode;
import fr.pederobien.minecraft.dictionary.interfaces.IPlayerGroup;

public enum EBordersCode implements IMinecraftCode {
	// Common codes --------------------------------------------------------------

	// Code that returns "none"
	BORDERS_NONE,

	// Code for command borders --------------------------------------------------
	BORDERS__EXPLANATION,

	// Code for the "borders add" command ----------------------------------------
	BORDERS__ADD_BORDERS__EXPLANATION,

	// Code when the border file does not exists
	BORDERS__ADD_BORDERS__BORDER_DOES_NOT_EXIST,

	// Code when the border is already registered
	BORDERS__ADD_BORDERS__BORDER_ALREADY_REGISTERED,

	// Code when the border deserialization fails
	BORDERS__ADD_BORDERS__FAIL_TO_LOAD,

	// Code then there is not border to add
	BORDERS__ADD_BORDERS__NO_BORDER_ADDED,

	// Code when there is one border to add
	BORDERS__ADD_BORDERS__ONE_BORDER_ADDED,

	// Code when there is several borders to add
	BORDERS__ADD_BORDERS__SEVERAL_BORDERS_ADDED,

	// Code for the "borders remove" command -------------------------------------
	BORDERS__REMOVE_BORDERS__EXPLANATION,

	// Code when the border is not registered
	BORDERS__REMOVE_BORDERS__BORDER_NOT_REGISTERED,

	// Code when there is no border to remove
	BORDERS__REMOVE_BORDERS__NO_BORDER_REMOVED,

	// Code when there is one border to remove
	BORDERS__REMOVE_BORDERS__ONE_BORDER_REMOVED,

	// Code when there are several borders to remove
	BORDERS__REMOVE_BORDERS__SEVERAL_BORDERS_REMOVED,

	// Code for the "borders list" command ---------------------------------------
	BORDERS__LIST_BORDERS__EXPLANATION,

	// Code when displaying the border to each world
	BORDERS__LIST_BORDERS__ALL_REGISTERED_BORDERS,

	// Code for the "borders reload" command -------------------------------------
	BORDERS__RELOAD_BORDERS__EXPLANATION,

	// Code when the border is not registered
	BORDERS__RELOAD_BORDERS__BORDER_NOT_REGISTERED,

	// Code when there no border file
	BORDERS__RELOAD_BORDERS__NO_BORDER_FILE,

	// Code when no border has been reload
	BORDERS__RELOAD_BORDERS__NO_BORDER_RELOADED,

	// Code when one border has been reloaded
	BORDERS__RELOAD_BORDERS__ONE_BORDER_RELOADED,

	// Code when several borders have been reloaded
	BORDERS__RELOAD_BORDERS__SEVERAL_BORDERS_RELOADED,

	// Code for the "borders details" command ------------------------------------
	BORDERS__DETAILS__EXPLANATION,

	// Code when a border is not registered
	BORDERS__DETAILS_BORDERS__BORDER_NOT_FOUND,

	// Code when a border is not registered
	BORDERS__DETAILS_BORDERS__NO_ELEMENT,

	// Code when a border is one registered border
	BORDERS__DETAILS_BORDERS__DETAILS,

	;

	private IPlayerGroup group;

	private EBordersCode() {
		this(PlayerGroup.OPERATORS);
	}

	private EBordersCode(IPlayerGroup group) {
		this.group = group;
	}

	@Override
	public String value() {
		return name();
	}

	@Override
	public IPlayerGroup getGroup() {
		return group;
	}

	@Override
	public void setGroup(IPlayerGroup group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return String.format("value=%s,group=%s", value(), getGroup());
	}
}
