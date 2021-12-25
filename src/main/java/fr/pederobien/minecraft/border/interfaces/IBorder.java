package fr.pederobien.minecraft.border.interfaces;

import java.time.LocalTime;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;

import fr.pederobien.minecraft.game.platform.interfaces.INominable;

public interface IBorder extends INominable {

	/**
	 * @return The world on which this configuration is applied.
	 */
	World getWorld();

	/**
	 * Set the world in which this configuration is applied.
	 * 
	 * @param world The world in which the world border is applied.
	 */
	void setWorld(World world);

	/**
	 * @return The world border center.
	 */
	Block getCenter();

	/**
	 * Set the x coordinate and the z coordinate of the world border center.
	 * 
	 * @param Block the world border center.
	 */
	void setCenter(Block center);

	/**
	 * @return The initial world border diameter. This diameter is set when starting a game.
	 */
	Integer getInitialDiameter();

	/**
	 * Set the initial world border diameter. This value is set in the world when starting a game.
	 * 
	 * @param initialDiameter The initial world border diameter.
	 */
	void setInitialDiameter(int initialDiameter);

	/**
	 * @return The final world border diameter. If this border is moved, it is target diameter.
	 */
	Integer getFinalDiameter();

	/**
	 * Set the final world border diameter. If the border is moved, it is the target diameter.
	 * 
	 * @param finalDiameter The final world border diameter.
	 */
	void setFinalDiameter(int finalDiameter);

	/**
	 * @return The speed (block/s) at which the border is moving from its initial diameter to its final diameter. The real world
	 *         border speed on minecraft world correspond to this speed divided by 2.
	 */
	Double getSpeed();

	/**
	 * Set the speed (block/s) at which the border moves from its initial diameter to its final diameter. The real world border speed
	 * on minecraft world correspond to the given speed divided by 2.
	 * 
	 * @param speed The world border speed.
	 */
	void setSpeed(double speed);

	/**
	 * @return The time at which the border associated to this configuration moves from its initial diameter to its final diameter. If
	 *         this time correspond to 0h 0m 0s then the border moves when a game starts.
	 * 
	 * @see LocalTime#of(int, int, int)
	 */
	LocalTime getStartTime();

	/**
	 * Set the time at which the border associated to this configuration moves from its initial diameter to its final diameter. If
	 * this time correspond to 0h 0m 0s then the border moves when a game starts.
	 * 
	 * @param startTime The time at which the border moves.
	 * 
	 * @see LocalTime#of(int, int, int)
	 */
	void setStartTime(LocalTime startTime);

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
	 * @return true if this border has been updated, false otherwise.
	 */
	boolean reload();
}
