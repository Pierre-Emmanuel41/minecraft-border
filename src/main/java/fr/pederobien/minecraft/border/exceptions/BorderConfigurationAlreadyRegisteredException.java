package fr.pederobien.minecraft.border.exceptions;

import fr.pederobien.minecraft.border.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.exceptions.configurations.GameConfigurationException;
import fr.pederobien.minecraftgameplateform.interfaces.element.IGameConfiguration;

public class BorderConfigurationAlreadyRegisteredException extends GameConfigurationException {
	private static final long serialVersionUID = 1L;
	private IBorderConfiguration borderConfiguration;

	public BorderConfigurationAlreadyRegisteredException(IGameConfiguration gameConfiguration, IBorderConfiguration borderConfiguration) {
		super(gameConfiguration);
		this.borderConfiguration = borderConfiguration;
	}

	@Override
	protected String getInternalMessage() {
		return "The configuration " + getBorderConfiguration().getName() + " is already registered for this configuration (" + getGameConfiguration().getName() + ")";
	}

	/**
	 * @return The already registered border configuration.
	 */
	public IBorderConfiguration getBorderConfiguration() {
		return borderConfiguration;
	}
}
