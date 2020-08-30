package fr.pederobien.minecraftborder.entries;

import java.text.DecimalFormat;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftmanagers.WorldManager;

public class WorldBorderSizeEntry extends BorderConfigurationEntry {
	private World world;
	private DecimalFormat format;
	private boolean displayHalfSize;

	/**
	 * Create an entry that display the current size of the {@link WorldBorder} associated to the given world.
	 * 
	 * @param score         The line number of this entry in the player objective.
	 * @param configuration The configuration used to get informations to display.
	 * @param pattern       A string used to format the world border size.
	 */
	public WorldBorderSizeEntry(int score, IBorderConfiguration configuration, String pattern) {
		super(score, configuration);
		this.world = configuration.getWorld();
		this.format = new DecimalFormat(pattern);
	}

	@Override
	protected String updateCurrentValue(Player player) {
		return displayHalfSize ? "\u00b1" + format.format(world.getWorldBorder().getSize() / 2.0) : format.format(world.getWorldBorder().getSize());
	}

	/**
	 * @return The world to which this entry is associated.
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * Used to to display the "diameter" or the "radius" of the world border. By default, this entry display the diameter. When set to
	 * true, this entry display the following message : <code>WorldName : \u00b1 + radius + after</code>. The code \u00b1 correspond
	 * to the symbol +/-.
	 * 
	 * @param displayHalfSize True to display the radius, false to display the diameter.
	 * @return This entry.
	 */
	public WorldBorderSizeEntry setDisplayHalfSize(boolean displayHalfSize) {
		this.displayHalfSize = displayHalfSize;
		return this;
	}

	@Override
	public final String getBefore() {
		return WorldManager.getWorldNameNormalised(world) + " : ";
	}
}
