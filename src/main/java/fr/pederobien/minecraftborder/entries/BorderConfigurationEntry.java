package fr.pederobien.minecraftborder.entries;

import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.entries.PlateformEntry;

public abstract class BorderConfigurationEntry extends PlateformEntry {
	private IBorderConfiguration configuration;

	/**
	 * Create an entry to display informations about the given configuration.
	 * 
	 * @param score         The line number of this entry.
	 * @param configuration The configuration used to get informations to display.
	 */
	protected BorderConfigurationEntry(int score, IBorderConfiguration configuration) {
		super(score);
		this.configuration = configuration;
	}

	/**
	 * @return The border configuration associated to this entry.
	 */
	public IBorderConfiguration getConfiguration() {
		return configuration;
	}
}
