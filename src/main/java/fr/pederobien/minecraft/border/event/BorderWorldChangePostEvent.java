package fr.pederobien.minecraft.border.event;

import java.util.StringJoiner;

import org.bukkit.World;

import fr.pederobien.minecraft.border.interfaces.IBorder;

public class BorderWorldChangePostEvent extends BorderEvent {
	private World oldWorld;

	/**
	 * Creates an event thrown when the world of a border has changed.
	 * 
	 * @param border   The border whose the world has changed.
	 * @param oldWorld The old border world.
	 */
	public BorderWorldChangePostEvent(IBorder border, World oldWorld) {
		super(border);
		this.oldWorld = oldWorld;
	}

	/**
	 * @return The old border world.
	 */
	public World getOldWorld() {
		return oldWorld;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "{", "}");
		joiner.add("border=" + getBorder().getName());
		joiner.add("oldWorld=" + getOldWorld().getName());
		joiner.add("newWorld=" + getBorder().getWorld().getName());
		return String.format("%s_%s", getName(), joiner);
	}
}
