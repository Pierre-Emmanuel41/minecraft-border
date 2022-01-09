package fr.pederobien.minecraft.border.event;

import java.util.StringJoiner;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.interfaces.IBorderList;

public class BorderListBorderRemovePostEvent extends BorderListEvent {
	private IBorder border;

	/**
	 * Creates an event thrown when a border is removed from a list.
	 * 
	 * @param list   The list to which a border has been added.
	 * @param border The removed border.
	 */
	public BorderListBorderRemovePostEvent(IBorderList list, IBorder border) {
		super(list);
		this.border = border;
	}

	/**
	 * @return The removed border.
	 */
	public IBorder getBorder() {
		return border;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "{", "}");
		joiner.add("list=" + getList().getName());
		joiner.add("border=" + getBorder().getName());
		return String.format("%s_%s", getName(), joiner);
	}
}
