package fr.pederobien.minecraft.border.entries;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import fr.pederobien.minecraft.border.impl.EBorderCode;
import fr.pederobien.minecraft.dictionary.interfaces.IMinecraftCode;
import fr.pederobien.minecraft.managers.WorldManager;
import fr.pederobien.minecraft.platform.entries.simple.OrientationEntry;

public class BorderCenterEntry extends OrientationEntry {
	private World world;

	/**
	 * Creates a entry to display the direction to follow in order to reach the center of the border of the player's world.
	 * 
	 * @param score The line number of this entry.
	 */
	public BorderCenterEntry(int score) {
		super(score);
	}

	@Override
	protected IMinecraftCode getBeforeAsCode(Player player) {
		return EBorderCode.CENTER;
	}

	@Override
	public Block getBlock() {
		return world == null ? WorldManager.OVERWORLD.getWorldBorder().getCenter().getBlock() : world.getWorldBorder().getCenter().getBlock();
	}

	@Override
	protected String updateCurrentValue(Player player) {
		world = player.getWorld();
		return super.updateCurrentValue(player);
	}
}
