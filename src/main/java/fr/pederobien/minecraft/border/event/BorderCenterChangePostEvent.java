package fr.pederobien.minecraft.border.event;

import java.util.StringJoiner;

import org.bukkit.block.Block;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.game.impl.DisplayHelper;

public class BorderCenterChangePostEvent extends BorderEvent {
	private Block oldCenter;

	/**
	 * Creates an event thrown when the center of a border has changed.
	 * 
	 * @param border    the border whose the center has changed.
	 * @param oldCenter The old border center.
	 */
	public BorderCenterChangePostEvent(IBorder border, Block oldCenter) {
		super(border);
		this.oldCenter = oldCenter;
	}

	/**
	 * @return The old border center.
	 */
	public Block getOldCenter() {
		return oldCenter;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "{", "}");
		joiner.add("border=" + getBorder().getName());
		joiner.add("oldCenter=" + DisplayHelper.toString(getOldCenter()));
		joiner.add("newCenter=" + DisplayHelper.toString(getBorder().getCenter()));
		return String.format("%s_%s", getName(), joiner);
	}
}
