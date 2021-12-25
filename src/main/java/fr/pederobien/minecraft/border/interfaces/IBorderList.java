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
	 * Append the given border configuration to this list. If a border configuration is already registered for the world, then the
	 * former configuration is replaced.
	 * 
	 * @param configuration The border configuration to add.
	 */
	void add(IBorder configuration);

	/**
	 * Remove from this list the border configuration associated to the given name.
	 * 
	 * @param configuration The configuration to remove.
	 * 
	 * @return The removed configuration if registered, null otherwise.
	 */
	IBorder remove(String name);

	/**
	 * Remove the given border configuration from this list.
	 * 
	 * @param configuration The configuration to remove.
	 * 
	 * @return True if the configuration was registered, false otherwise.
	 */
	boolean remove(IBorder configuration);

	/**
	 * Remove the border configuration registered for the given world.
	 * 
	 * @param world The world used as key to filter all registered border configurations.
	 * 
	 * @return The removed configuration if registered, null otherwise.
	 */
	IBorder remove(World world);

	/**
	 * Removes all registered borders.
	 */
	void clear();

	/**
	 * Get the border configuration for the given world if it exists.
	 * 
	 * @param world The world used as key to filter all registered border configurations.
	 * 
	 * @return An optional that contains the registered border configuration if it exists, an empty optional otherwise.
	 */
	Optional<IBorder> getBorder(World world);

	/**
	 * Get the configuration associated to the given name.
	 * 
	 * @param name The configuration name.
	 * 
	 * @return An optional that contains the configuration if it is registered, an empty optional otherwise.
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
