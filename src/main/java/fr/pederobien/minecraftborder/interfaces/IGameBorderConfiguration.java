package fr.pederobien.minecraftborder.interfaces;

import java.util.List;
import java.util.Optional;

import org.bukkit.World;

import fr.pederobien.minecraftborder.exceptions.BorderConfigurationAlreadyRegisteredException;
import fr.pederobien.minecraftgameplateform.exceptions.configurations.BorderConfigurationNotRegisteredException;
import fr.pederobien.minecraftgameplateform.interfaces.element.IGameConfiguration;

public interface IGameBorderConfiguration extends IGameConfiguration {

	/**
	 * Append the given border configuration to this game configuration. If a border configuration is already registered for the
	 * world, then the former configuration is replaced.
	 * 
	 * @param configuration The border configuration to add.
	 * 
	 * @throws BorderConfigurationAlreadyRegisteredException If the given configuration is already registered.
	 */
	void add(IBorderConfiguration configuration);

	/**
	 * Remove the given border configuration from this game configuration.
	 * 
	 * @param configuration The configuration to remove.
	 * 
	 * @throws BorderConfigurationNotRegisteredException If the the given configuration is not registered.
	 */
	void remove(IBorderConfiguration configuration);

	/**
	 * Remove each configuration in the given list <code>configurations</code> from this game configuration.
	 * 
	 * @param configurations The list of border configuration to remove.
	 */
	void remove(List<IBorderConfiguration> configurations);

	/**
	 * Remove the border configuration registered for the given world.
	 * 
	 * @param world The world used as key to filter all registered border configurations.
	 * 
	 * @return An optional that contains the removed border configuration if it exists, an empty optional otherwise.
	 */
	Optional<IBorderConfiguration> remove(World world);

	/**
	 * @return The list of all registered border configuration for this game configuration. This list is unmodifiable.
	 */
	List<IBorderConfiguration> getBorders();

	/**
	 * Get the registered border configuration for the given world if it exists.
	 * 
	 * @param world The world used as key to filter all registered border configurations.
	 * 
	 * @return An optional that contains the registered border configuration if it exists, an empty optional otherwise.
	 */
	Optional<IBorderConfiguration> getBorder(World world);

	/**
	 * Get the configuration associated to the given name.
	 * 
	 * @param name The configuration name.
	 * 
	 * @return An optional that contains the configuration if it is registered, an empty optional otherwise.
	 */
	Optional<IBorderConfiguration> getBorder(String name);

	/**
	 * Remove each registered border configuration from this game configuration.
	 * 
	 * @return The list of removed border configuration.
	 */
	List<IBorderConfiguration> clearBorders();
}
