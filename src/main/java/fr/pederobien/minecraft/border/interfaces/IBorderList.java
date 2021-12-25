package fr.pederobien.minecraft.border.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.bukkit.World;

public interface IBorderList {

	/**
	 * @return The name of this borders list.
	 */
	String getName();

	/**
	 * Appends the given border to this list. If a border is already registered for the world, then the former configuration is
	 * replaced.
	 * 
	 * @param border The border to add.
	 */
	void add(IBorder border);

	/**
	 * Removes from this list the border associated to the given name.
	 * 
	 * @param name The border name to remove.
	 * 
	 * @return The removed border if registered, null otherwise.
	 */
	IBorder remove(String name);

	/**
	 * Removes the given border from this list.
	 * 
	 * @param border The border to remove.
	 * 
	 * @return True if the border was registered, false otherwise.
	 */
	boolean remove(IBorder border);

	/**
	 * Removes the border associated to the given world.
	 * 
	 * @param world The world used to remove the associated border.
	 * 
	 * @return The removed border if registered, null otherwise.
	 */
	IBorder remove(World world);

	/**
	 * Removes all registered borders.
	 */
	void clear();

	/**
	 * Get the border associated to the given world if it exists.
	 * 
	 * @param world The world used as key to filter all registered border configurations.
	 * 
	 * @return An optional that contains the registered border if it exists, an empty optional otherwise.
	 */
	Optional<IBorder> getBorder(World world);

	/**
	 * Get the border associated to the given name.
	 * 
	 * @param name The border name.
	 * 
	 * @return An optional that contains the border if it is registered, an empty optional otherwise.
	 */
	Optional<IBorder> getBorder(String name);

	/**
	 * @return a sequential {@code Stream} over the elements in this collection.
	 */
	Stream<IBorder> stream();

	/**
	 * @return A copy of the underlying list.
	 */
	List<IBorder> toList();
}
