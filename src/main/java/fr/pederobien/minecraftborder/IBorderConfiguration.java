package fr.pederobien.minecraftborder;

import java.time.LocalTime;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;

import fr.pederobien.minecraftgameplateform.exceptions.worldstructure.WorldNotFoundException;
import fr.pederobien.minecraftgameplateform.interfaces.element.INominable;
import fr.pederobien.minecraftgameplateform.interfaces.runtime.timeline.IObservableTimeLine;
import fr.pederobien.minecraftgameplateform.interfaces.runtime.timeline.ITimeLineObserver;

public interface IBorderConfiguration extends Comparable<IBorderConfiguration>, INominable, ITimeLineObserver {

	/**
	 * @return The world on which this configuration is applied.
	 */
	World getWorld();

	/**
	 * Set the world in which this configuration is applied.
	 * 
	 * @param worldName The world's name in which the world border is applied.
	 * 
	 * @throws WorldNotFoundException If the given world name does not correspond to a world.
	 */
	void setWorld(String worldName);

	/**
	 * @return The world border center.
	 */
	Block getBorderCenter();

	/**
	 * Set the x coordinate and the z coordinate of the world border center.
	 * 
	 * @param x The x-Coordinate of the center.
	 * @param z The z-Coordinate of the center.
	 */
	void setBorderCenter(int x, int z);

	/**
	 * @return The initial world border diameter. This diameter is set when starting a game.
	 */
	Integer getInitialBorderDiameter();

	/**
	 * Set the initial world border diameter. This value is set in the world when starting a game.
	 * 
	 * @param initialDiameter The initial world border diameter.
	 */
	void setInitialBorderDiameter(int initialDiameter);

	/**
	 * @return The final world border diameter. If this border is moved, it is target diameter.
	 */
	Integer getFinalBorderDiameter();

	/**
	 * Set the final world border diameter. If the border is moved, it is the target diameter.
	 * 
	 * @param finalDiameter The final world border diameter.
	 */
	void setFinalBorderDiameter(int finalDiameter);

	/**
	 * @return The speed (block/s) at which the border is moving from its initial diameter to its final diameter. The real world
	 *         border speed on minecraft world correspond to this speed divided by 2.
	 */
	Double getBorderSpeed();

	/**
	 * Set the speed (block/s) at which the border moves from its initial diameter to its final diameter. The real world border speed
	 * on minecraft world correspond to the given speed divided by 2.
	 * 
	 * @param borderSpeed The world border speed.
	 */
	void setBorderSpeed(double borderSpeed);

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
	 * Modify the center and the size of the border associated to its world.
	 * 
	 * @param timeline The time line used to move the border from its initial diameter to its final diameter.
	 * 
	 * @see #getBorderCenter()
	 * @see #getInitialBorderDiameter()
	 * @see WorldBorder#setCenter(org.bukkit.Location)
	 * @see WorldBorder#setSize(double)
	 */
	void apply(IObservableTimeLine timeline);

	/**
	 * Set the diameter of the border to its current diameter.
	 */
	void pause();

	/**
	 * Move the border from its current diameter to its final diameter if the border was moving.
	 */
	void relaunched();

	/**
	 * Reset the border associated to its world. This configuration is also removed from the list of observers of the
	 * {@link IObservableTimeLine} passed to the method {@link #apply(IObservableTimeLine)}.
	 */
	void reset();
}
