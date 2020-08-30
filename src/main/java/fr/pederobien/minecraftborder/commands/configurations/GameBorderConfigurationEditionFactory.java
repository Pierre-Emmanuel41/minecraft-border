package fr.pederobien.minecraftborder.commands.configurations;

import fr.pederobien.minecraftborder.interfaces.IGameBorderConfiguration;
import fr.pederobien.minecraftgameplateform.interfaces.editions.IMapPersistenceEdition;

public class GameBorderConfigurationEditionFactory {

	/**
	 * @return An edition to manage borders for a game border configuration.
	 */
	public static <T extends IGameBorderConfiguration> IMapPersistenceEdition<T> bordersEdition() {
		return new CommonBorders<T>();
	}
}
