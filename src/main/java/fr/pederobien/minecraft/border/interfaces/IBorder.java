package fr.pederobien.minecraft.border.interfaces;

import java.time.LocalTime;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;

import fr.pederobien.minecraft.platform.interfaces.IConfigurable;
import fr.pederobien.minecraft.platform.interfaces.INominable;
import fr.pederobien.minecraft.platform.interfaces.IPlatformPersistence;

public interface IBorder extends INominable {

	/**
	 * @return The world on which this configuration is applied.
	 */
	IConfigurable<World> getWorld();

	/**
	 * @return The world border center.
	 */
	IConfigurable<Block> getCenter();

	/**
	 * @return The initial world border diameter. This diameter is set when starting a game.
	 */
	IConfigurable<Integer> getInitialDiameter();

	/**
	 * @return The final world border diameter. If this border is moved, it is target diameter.
	 */
	IConfigurable<Integer> getFinalDiameter();

	/**
	 * @return The speed (block/s) at which the border is moving from its initial diameter to its final diameter. The real world
	 *         border speed on minecraft world correspond to this speed divided by 2.
	 */
	IConfigurable<Double> getSpeed();

	/**
	 * @return The time at which the border associated to this configuration moves from its initial diameter to its final diameter. If
	 *         this time correspond to 0h 0m 0s then the border moves when a game starts.
	 * 
	 * @see LocalTime#of(int, int, int)
	 */
	IConfigurable<LocalTime> getStartTime();

	/**
	 * @return The time it take to move the border from its initial diameter to its final diameter.
	 */
	LocalTime getMoveTime();

	/**
	 * Set the time to move the border. Using this method update the border speed.
	 *
	 * @param moveTime The time it take to move the border from its initial diameter to its final diameter.
	 */
	void setMoveTime(LocalTime moveTime);

	/**
	 * @return True if the border is moving, false otherwise.
	 */
	boolean isMoving();

	/**
	 * @return The world border associated to the world of this configuration.
	 */
	WorldBorder getWorldBorder();

	/**
	 * Reload the content of the file associated to this border in to order to update this border.
	 * 
	 * @param persistence The persistence used to reload this border.
	 * 
	 * @return true if this border has been updated, false otherwise.
	 */
	boolean reload(IPlatformPersistence<IBorder> persistence);
}
