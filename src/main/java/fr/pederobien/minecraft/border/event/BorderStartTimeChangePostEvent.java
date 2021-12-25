package fr.pederobien.minecraft.border.event;

import java.time.LocalTime;
import java.util.StringJoiner;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.game.impl.DisplayHelper;

public class BorderStartTimeChangePostEvent extends BorderEvent {
	private LocalTime oldStartTime;

	/**
	 * Creates an event thrown when the start time of a border has changed.
	 * 
	 * @param border       The border whose the start time has changed.
	 * @param oldStartTime The old border start time.
	 */
	public BorderStartTimeChangePostEvent(IBorder border, LocalTime oldStartTime) {
		super(border);
		this.oldStartTime = oldStartTime;
	}

	/**
	 * @return The old border start time.
	 */
	public LocalTime getOldStartTime() {
		return oldStartTime;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "{", "}");
		joiner.add("border=" + getBorder().getName());
		joiner.add("oldStartTime=" + DisplayHelper.toString(getOldStartTime(), true));
		joiner.add("newStartTime=" + DisplayHelper.toString(getBorder().getStartTime(), true));
		return String.format("%s_%s", getName(), joiner);
	}
}
