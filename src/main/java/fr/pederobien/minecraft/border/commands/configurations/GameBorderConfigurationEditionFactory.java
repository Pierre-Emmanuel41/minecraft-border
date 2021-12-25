package fr.pederobien.minecraft.border.commands.configurations;

import fr.pederobien.minecraft.border.interfaces.IBorderList;
import fr.pederobien.minecraftgameplateform.interfaces.editions.IMapPersistenceEdition;

public class GameBorderConfigurationEditionFactory {

	/**
	 * @return An edition to manage borders for a game border configuration.
	 */
	public static <T extends IBorderList> IMapPersistenceEdition<T> bordersEdition() {
		return new CommonBorders<T>();
	}
}
