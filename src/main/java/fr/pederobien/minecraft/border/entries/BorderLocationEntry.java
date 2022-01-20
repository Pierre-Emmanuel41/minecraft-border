package fr.pederobien.minecraft.border.entries;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import fr.pederobien.minecraft.managers.WorldManager;
import fr.pederobien.minecraft.platform.entries.simple.LocationEntry;

public class BorderLocationEntry extends LocationEntry {
	private World world;

	/**
	 * Creates a entry to display the player's coordinates relative to the center of the border of the player's world.
	 * 
	 * @param score The line number of this entry.
	 */
	public BorderLocationEntry(int score) {
		super(score);
	}

	@Override
	public Block getCenter() {
		return world == null ? WorldManager.OVERWORLD.getWorldBorder().getCenter().getBlock() : world.getWorldBorder().getCenter().getBlock();
	}

	@Override
	protected String updateCurrentValue(Player player) {
		world = player.getWorld();
		return super.updateCurrentValue(player);
	}
}
