package fr.pederobien.minecraft.border.entries;

import java.text.DecimalFormat;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.managers.WorldManager;

public class BorderSizeEntry extends BorderEntry {
	private World world;
	private DecimalFormat format;
	private boolean displayHalfSize;

	/**
	 * Create an entry that display the current size of the {@link WorldBorder} associated to the given world.
	 * 
	 * @param score   The line number of this entry in the player objective.
	 * @param border  The border used to get informations to display.
	 * @param pattern A string used to format the world border size.
	 */
	public BorderSizeEntry(int score, IBorder border, String pattern) {
		super(score, border);
		this.world = border.getWorld().get();
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
	public BorderSizeEntry setDisplayHalfSize(boolean displayHalfSize) {
		this.displayHalfSize = displayHalfSize;
		return this;
	}

	@Override
	public final String getBefore() {
		return WorldManager.getWorldNameNormalised(world) + " : ";
	}
}
