package fr.pederobien.minecraft.border.event;

import java.util.StringJoiner;

import fr.pederobien.minecraft.border.interfaces.IBorder;

public class BorderNameChangePostEvent extends BorderEvent {
	private String oldName;

	/**
	 * Creates an event thrown when a border is renamed.
	 * 
	 * @param border  The renamed border.
	 * @param oldName The old border name
	 */
	public BorderNameChangePostEvent(IBorder border, String oldName) {
		super(border);
		this.oldName = oldName;
	}

	/**
	 * @return The old border name.
	 */
	public String getOldName() {
		return oldName;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "{", "}");
		joiner.add("oldName=" + oldName);
		joiner.add("newName=" + getBorder().getName());
		return String.format("%s_%s", getName(), joiner);
	}

}
