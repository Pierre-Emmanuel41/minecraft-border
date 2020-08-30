package fr.pederobien.minecraftborder.commands;

import fr.pederobien.minecraftborder.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.interfaces.editions.IMapPersistenceEdition;

public class BorderEditionsFactory {

	/**
	 * @return An edition to create world border configuration.
	 */
	public static IMapPersistenceEdition<IBorderConfiguration> newBorder() {
		return new NewBorder();
	}

	/**
	 * @return An edition to display current border's characteristics.
	 */
	public static IMapPersistenceEdition<IBorderConfiguration> currentBorder() {
		return new DetailsBorder();
	}

	/**
	 * @return An edition to load a world border configuration.
	 */
	public static IMapPersistenceEdition<IBorderConfiguration> loadBorder() {
		return new LoadBorder();
	}

	/**
	 * @return An edition to delete a border's file.
	 */
	public static IMapPersistenceEdition<IBorderConfiguration> deleteBorder() {
		return new DeleteBorder();
	}

	/**
	 * @return An edition to display each existing border's name.
	 */
	public static IMapPersistenceEdition<IBorderConfiguration> listBorder() {
		return new ListBorder();
	}

	/**
	 * @return An edition to rename a border.
	 */
	public static IMapPersistenceEdition<IBorderConfiguration> renameBorder() {
		return new RenameBorder();
	}

	/**
	 * @return An edition to save a border.
	 */
	public static IMapPersistenceEdition<IBorderConfiguration> saveBorder() {
		return new SaveBorder();
	}

	/**
	 * @return An edition to set the world in which the border is applied.
	 */
	public static IMapPersistenceEdition<IBorderConfiguration> worldBorder() {
		return new WorldBorder();
	}

	/**
	 * @return An edition to set the border's center.
	 */
	public static IMapPersistenceEdition<IBorderConfiguration> centerBorder() {
		return new CenterBorder();
	}

	/**
	 * @return An edition to set the initial border diameter.
	 */
	public static IMapPersistenceEdition<IBorderConfiguration> initialBorderDiameter() {
		return new InitialBorderDiameter();
	}

	/**
	 * @return An edition to set the final border diameter.
	 */
	public static IMapPersistenceEdition<IBorderConfiguration> finalBorderDiameter() {
		return new FinalBorderDiameter();
	}

	/**
	 * @return An edition to set the border's speed.
	 */
	public static IMapPersistenceEdition<IBorderConfiguration> speedBorder() {
		return new SpeedBorder();
	}

	/**
	 * @return An edition to set the time after which the border moves from its initial diameter to its final diameter.
	 */
	public static IMapPersistenceEdition<IBorderConfiguration> startTimeBorder() {
		return new StartTimeBorder();
	}

	/**
	 * @return An edition to set the time it take to move the border from its initial diameter to its final diameter.
	 */
	public static IMapPersistenceEdition<IBorderConfiguration> moveTimeBorder() {
		return new MoveTimeBorder();
	}
}
