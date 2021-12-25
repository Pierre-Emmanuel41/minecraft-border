package fr.pederobien.minecraft.border.event;

import java.util.StringJoiner;

import fr.pederobien.minecraft.border.interfaces.IBorder;

public class BorderFinalDiameterChangePostEvent extends BorderEvent {
	private int oldFinalDiameter;

	/**
	 * Creates an event thrown when the final diameter of a border has changed.
	 * 
	 * @param border           The border whose the final diameter has changed.
	 * @param oldFinalDiameter The old border final diameter.
	 */
	public BorderFinalDiameterChangePostEvent(IBorder border, int oldFinalDiameter) {
		super(border);
		this.oldFinalDiameter = oldFinalDiameter;
	}

	/**
	 * @return The old border final diameter.
	 */
	public int getOldFinalDiameter() {
		return oldFinalDiameter;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "{", "}");
		joiner.add("border=" + getBorder().getName());
		joiner.add(String.format("oldFinalDiameter= %s block(s)", getOldFinalDiameter()));
		joiner.add(String.format("newFinalDiameter= %s block(s)", getBorder().getFinalDiameter()));
		return String.format("%s_%s", getName(), joiner);
	}
}
