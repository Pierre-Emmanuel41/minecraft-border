package fr.pederobien.minecraft.border.event;

import java.util.StringJoiner;

import fr.pederobien.minecraft.border.interfaces.IBorder;

public class BorderInitialDiameterChangePostEvent extends BorderEvent {
	private int oldInitialDiameter;

	/**
	 * Creates an event thrown when the initial diameter of a border has changed.
	 * 
	 * @param border             The border whose the initial diameter has changed.
	 * @param oldInitialDiameter The old border initial diameter.
	 */
	public BorderInitialDiameterChangePostEvent(IBorder border, int oldInitialDiameter) {
		super(border);
		this.oldInitialDiameter = oldInitialDiameter;
	}

	/**
	 * @return The old border initial diameter.
	 */
	public int getOldInitialDiameter() {
		return oldInitialDiameter;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "{", "}");
		joiner.add("border=" + getBorder().getName());
		joiner.add(String.format("oldInitialDiameter= %s block(s)", getOldInitialDiameter()));
		joiner.add(String.format("newInitialDiameter= %s block(s)", getBorder().getInitialDiameter()));
		return String.format("%s_%s", getName(), joiner);
	}

}
