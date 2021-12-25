package fr.pederobien.minecraft.border.commands.configurations;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.bukkit.World;

import fr.pederobien.minecraft.border.exceptions.BorderConfigurationNotRegisteredException;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.interfaces.IBorderList;
import fr.pederobien.minecraft.border.persistence.BorderPersistence;
import fr.pederobien.minecraftdictionary.interfaces.IMinecraftMessageCode;
import fr.pederobien.minecraftgameplateform.commands.configurations.AbstractGameConfigurationEdition;
import fr.pederobien.minecraftgameplateform.exceptions.configurations.ConfigurationNotFoundException;
import fr.pederobien.minecraftgameplateform.interfaces.element.ILabel;
import fr.pederobien.minecraftmanagers.WorldManager;
import fr.pederobien.persistence.interfaces.IPersistence;

public class AbstractGameBorderConfigurationEdition<T extends IBorderList> extends AbstractGameConfigurationEdition<T> {
	public static final IPersistence<IBorder> PERSISTENCE = BorderPersistence.getInstance();

	protected AbstractGameBorderConfigurationEdition(ILabel label, IMinecraftMessageCode explanation) {
		super(label, explanation);
	}

	/**
	 * Remove configurations already mentioned from the list returned by {@link IPersistence#list()}. The persistence is an instance
	 * of {@link BorderPersistence}.
	 * 
	 * @param alreadyMentionnedConfigurations A list that contains already mentioned configurations.
	 * @return A stream that contains free and not mentioned configurations.
	 */
	protected Stream<String> getFreeBorderConfigurations(List<String> alreadyMentionnedConfigurations) {
		return PERSISTENCE.list().stream().filter(conf -> !alreadyMentionnedConfigurations.contains(conf) && !get().getBorder(conf).isPresent());
	}

	/**
	 * Load the configuration for each name in the array <code>args</code>.
	 * 
	 * @param args An array that contains each configuration to load.
	 * 
	 * @return A list of loaded configuration.
	 * 
	 * @throws ConfigurationNotFoundException If the configuration file associated to a name does not exist.
	 */
	protected List<IBorder> getConfigurations(String... args) {
		List<IBorder> configurations = new ArrayList<IBorder>();
		String current = "";
		PERSISTENCE.set(null);
		try {
			for (String name : args) {
				current = name;
				PERSISTENCE.load(name);
				configurations.add(PERSISTENCE.get());
			}
		} catch (FileNotFoundException e) {
			throw new ConfigurationNotFoundException(current);
		}
		return configurations;
	}

	/**
	 * Get the border configuration in the game configuration associated to each name present in the given array <code>args</code>.
	 * 
	 * @param args The array that contains the border configuration name to get from the game configuration.
	 * @return A list that contains registered border configurations.
	 * 
	 * @throws BorderConfigurationNotRegisteredException If there is any registered configuration for a name.
	 * @see #get()
	 */
	protected List<IBorder> getConfigurationsFromGameConfiguration(String... args) {
		List<IBorder> configurations = new ArrayList<IBorder>();
		for (String name : args) {
			Optional<IBorder> optConf = get().getBorder(name);
			if (!optConf.isPresent())
				throw new BorderConfigurationNotRegisteredException(get(), name);
			configurations.add(optConf.get());
		}
		return configurations;
	}

	/**
	 * Get a string to display the name of the registered configuration for the given world. If any border is registered, then none is
	 * displayed.
	 * 
	 * @param world The world used as key to get its border configuration.
	 * @return A string that looks like : <code>WorldName + " : " + borderName</code> if a border is registered.</br>
	 *         <code>WorldName + " : none"</code> if any border is registered.
	 */
	protected String concat(World world) {
		Optional<IBorder> optBorder = get().getBorder(world);
		return WorldManager.getWorldNameNormalised(world) + " : " + (optBorder.isPresent() ? optBorder.get().getName() : " none ");
	}
}
