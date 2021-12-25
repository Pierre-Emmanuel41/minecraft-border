package fr.pederobien.minecraft.border.commands;

import fr.pederobien.minecraft.dictionary.impl.PlayerGroup;
import fr.pederobien.minecraft.dictionary.interfaces.IMinecraftCode;
import fr.pederobien.minecraft.dictionary.interfaces.IPlayerGroup;

public enum EBorderCode implements IMinecraftCode {
	// Common codes --------------------------------------------------------------

	// Code for the name parameter
	BORDER_NAME,

	// Code for the world parameter
	BORDER_WORLD,

	// Code for the center parameter
	BORDER_CENTER,

	// Code for the initial diameter parameter
	BORDER_INITIAL_DIAMETER,

	// Code for the final diameter parameter
	BORDER_FINAL_DIAMETER,

	// Code for the speed parameter
	BORDER_SPEED,

	// Code for the start time parameter
	BORDER_START_TIME,

	// Code for the move time parameter
	BORDER_MOVE_TIME,

	// Code for the end time parameter
	BORDER_END_TIME,

	// Code when the expected value should be strictly positive integer
	BORDER__INTEGER__BAD_FORMAT,

	// Code when the expected value should be strictly positive integer
	BORDER__STRICTLY_POSITIVE_INTEGER__BAD_FORMAT,

	// Code for the "border" command ---------------------------------------------
	BORDER__EXPLANATION,

	// Code for the "border new" command -----------------------------------------
	BORDER__NEW_BORDER__EXPLANATION,

	// Code when the name is missing wile creating a new border
	BORDER__NEW_BORDER__NAME_IS_MISSING,

	// Code when the name is already used
	BORDER__NEW_BORDER__NAME_ALREADY_USED,

	// Code when the border is created
	BORDER__NEW_BORDER__BORDER_CREATED,

	// Code for the "border details" command -----------------------------------------
	BORDER__DETAILS_BORDER__EXPLANATION,

	// Code when displaying the border details
	BORDER__DETAILS_BORDER__ON_DETAILS,

	// Code for the "border rename" command ------------------------------------------
	BORDER__RENAME_BORDER__EXPLANATION,

	// Code when the new border name is missing
	BORDER__RENAME_BORDER__NAME_IS_MISSING,

	// Code when the new border name is already used
	BORDER__RENAME_BORDER__NAME_IS_ALREADY_USED,

	// Code when border is renamed
	BORDER__RENAME_BORDER__BORDER_RENAMED,

	// Code for the "border delete" command ------------------------------------------
	BORDER__DELETE_BORDER__EXPLANATION,

	// Code when the border file name to delete is missing
	BORDER__DELETE_BORDER__NAME_IS_MISSING,

	// Code when the border file has been deleted
	BORDER__DELETE_BORDER__BORDER_DELETED,

	// Code for the "border load" command --------------------------------------------
	BORDER__LOAD_BORDER__EXPLANATION,

	// Code when the border name to load is missing
	BORDER__LOAD_BORDER__NAME_IS_MISSING,

	// Code when the border is loaded
	BORDER__LOAD_BORDER__BORDER_LOADED,

	// Code for the "border list" command --------------------------------------------
	BORDER__LIST_BORDER__EXPLANATION,

	// Code when there no border files
	BORDER__LIST_BORDER__NO_ELEMENT,

	// Code when there is one border file
	BORDER__LIST_BORDER__ONE_ELEMENT,

	// Code when there are several border files
	BORDER__LIST_BORDER__SEVERAL_ELEMENTS,

	// Code for the "border save" command --------------------------------------------
	BORDER__SAVE_BORDER__EXPLANATION,

	// Code when the border serialization fails
	BORDER__SAVE_BORDER__FAIL_TO_SAVE,

	// Code when the border is serialized
	BORDER__SAVE_BORDER__BORDER_SAVED,

	// Code for the "border world" command -------------------------------------------
	BORDER__WORLD_BORDER__EXPLANATION,

	// Code when the border world name is missing
	BORDER__WORLD_BORDER__WORLD_NAME_IS_MISSING,

	// Code when the world does not exists
	BORDER__WORLD_BORDER__WORLD_NOT_FOUND,

	// Code when the world of a border is updated
	BORDER__WORLD_BORDER__WORLD_UPDATED,

	// Code for the "border initialDiameter" command ---------------------------------
	BORDER__INITIAL_BORDER_DIAMETER__EXPLANATION,

	// Code for the initial diameter completion
	BORDER__INITIAL_BORDER_DIAMETER__COMPLETION,

	// Code when the initial diameter is missing
	BORDER__INITIAL_BORDER_DIAMETER__DIAMETER_IS_MISSING,

	// Code when the initial diameter is negative
	BORDER__INITIAL_BORDER_DIAMETER__NEGATIVE_DIAMETER,

	// Code when the initial diameter is updated
	BORDER__INITIAL_BORDER_DIAMETER__DIAMETER_UPDATED,

	// Code for the "border center" command ------------------------------------------
	BORDER__CENTER_BORDER__EXPLANATION,

	// Code when the X coordinate is missing
	BORDER__CENTER_BORDER__X_IS_MISSING,

	// Code when the Y coordinate is missing
	BORDER__CENTER_BORDER__Y_IS_MISSING,

	// Code when the Y coordinate is negative
	BORDER__CENTER_BORDER__Y_IS_NEGATIVE,

	// Code when the Y coordinate is greater than 256
	BORDER__CENTER_BORDER__Y_IS_GREATER_THAN_256,

	// Code when the Z coordinate is missing
	BORDER__CENTER_BORDER__Z_IS_MISSING,

	// Code when the center is updated
	BORDER__CENTER_BORDER__CENTER_UPDATED,

	// Code for the "border finalDiameter" command ---------------------------------
	BORDER__FINAL_BORDER_DIAMETER__EXPLANATION,

	// Code for the final diameter completion
	BORDER__FINAL_BORDER_DIAMETER__COMPLETION,

	// Code when the final diameter is missing
	BORDER__FINAL_BORDER_DIAMETER__DIAMETER_IS_MISSING,

	// Code when the final diameter is negative
	BORDER__FINAL_BORDER_DIAMETER__NEGATIVE_DIAMETER,

	// Code when the final diameter is updated
	BORDER__FINAL_BORDER_DIAMETER__DIAMETER_UPDATED,

	// Code for the "border startTime" command -------------------------------------
	BORDER__START_TIME_BORDER__EXPLANATION,

	// Code when the start time is missing
	BORDER__START_TIME_BORDER__TIME_IS_MISSING,

	// Code when the border moves from the beginning of the game
	BORDER__START_TIME_BORDER__BORDER_MOVES_AT_THE_BEGINNING,

	// Code when the start time is updated
	BORDER__START_TIME_BORDER__START_TIME_UPDATED,

	// Code for the "border speed" command -----------------------------------------
	BORDER__SPEED_BORDER__EXPLANATION,

	// Code for the border speed completion
	BORDER__SPEED_BORDER__COMPLETION,

	// Code when the border speed is missing
	BORDER__SPEED_BORDER__SPEED_IS_MISSING,

	// Code when the border speed is negative
	BORDER__SPEED_BORDER__SPEED_IS_NEGATIVE,

	// Code when the border speed is updated
	BORDER__SPEED_BORDER__SPEED_UPATED,

	// Code for the "border moveTime" command --------------------------------------
	BORDER__MOVE_TIME_BORDER__EXPLANATION,

	// Code when the move time is missing
	BORDER__MOVE_TIME_BORDER__TIME_IS_MISSING,

	// Code when the move time is 00:00:00
	BORDER__MOVE_TIME_BORDER__INSTANTLY_MOVE,

	// Code when the move time is updated
	BORDER__MOVE_TIME_BORDER__MOVE_TIME_UPDATED,

	// Code for in game messages
	MOVING_BORDER(PlayerGroup.ALL), MOVING_BORDER_COUNT_DOWN(PlayerGroup.ALL);

	private IPlayerGroup group;

	private EBorderCode() {
		this(PlayerGroup.OPERATORS);
	}

	private EBorderCode(IPlayerGroup group) {
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
