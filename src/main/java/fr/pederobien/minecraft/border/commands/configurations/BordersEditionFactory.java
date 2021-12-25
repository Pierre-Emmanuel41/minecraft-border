package fr.pederobien.minecraft.border.commands.configurations;

import fr.pederobien.minecraft.border.interfaces.IBorderList;
import fr.pederobien.minecraftgameplateform.interfaces.editions.IMapPersistenceEdition;

public class BordersEditionFactory {

	/**
	 * @return An edition to add border configurations to a game configuration.
	 */
	public static <T extends IBorderList> IMapPersistenceEdition<T> addBorders() {
		return new AddBorders<T>();
	}

	/**
	 * @return An edition to remove border configurations from a game configuration.
	 */
	public static <T extends IBorderList> IMapPersistenceEdition<T> removeBorders() {
		return new RemoveBorders<T>();
	}

	/**
	 * @return An edition to display the name of each registered borders for a game configuration.
	 */
	public static <T extends IBorderList> IMapPersistenceEdition<T> listBorders() {
		return new ListBorders<T>();
	}

	/**
	 * @return An edition to display characteristics of registered borders.
	 */
	public static <T extends IBorderList> IMapPersistenceEdition<T> detailsBorders() {
		return new DetailsBorders<T>();
	}

	/**
	 * @return An edition to reload registered borders for a game configuration.
	 */
	public static <T extends IBorderList> IMapPersistenceEdition<T> reloadBorders() {
		return new ReloadBorders<T>();
	}
}
