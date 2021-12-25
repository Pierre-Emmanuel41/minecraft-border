package fr.pederobien.minecraft.border.event;

import java.time.LocalTime;
import java.util.StringJoiner;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.game.impl.DisplayHelper;

public class BorderSpeedChangePostEvent extends BorderEvent {
	private double oldSpeed;
	private LocalTime oldMoveTime;

	/**
	 * Creates an event thrown when the speed of a border has changed.
	 * 
	 * @param border      The border whose the speed has changed.
	 * @param oldSpeed    The old border speed.
	 * @param oldMoveTime The old move time associated to the old speed.
	 */
	public BorderSpeedChangePostEvent(IBorder border, double oldSpeed, LocalTime oldMoveTime) {
		super(border);
		this.oldSpeed = oldSpeed;
		this.oldMoveTime = oldMoveTime;
	}

	/**
	 * @return The old border speed.
	 */
	public double getOldSpeed() {
		return oldSpeed;
	}

	/**
	 * @return The old move time associated to the old speed.
	 */
	public LocalTime getOldMoveTime() {
		return oldMoveTime;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "{", "}");
		joiner.add("border=" + getBorder().getName());
		joiner.add(String.format("oldSpeed= %2.f block/s (oldMoveTime = %s)", getOldSpeed(), DisplayHelper.toString(getOldMoveTime(), true)));
		joiner.add(String.format("newSpeed= %2.f block/s (newMoveTime = %s)", getBorder().getSpeed(), DisplayHelper.toString(getBorder().getMoveTime(), true)));
		return String.format("%s_%s", getName(), joiner);
	}
}
