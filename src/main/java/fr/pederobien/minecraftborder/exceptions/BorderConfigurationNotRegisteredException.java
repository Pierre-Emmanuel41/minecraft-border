package fr.pederobien.minecraftborder.exceptions;

import fr.pederobien.minecraftgameplateform.exceptions.configurations.GameConfigurationException;
import fr.pederobien.minecraftgameplateform.interfaces.element.IGameConfiguration;

public class BorderConfigurationNotRegisteredException extends GameConfigurationException {
	private static final long serialVersionUID = 1L;
	private String notRegisteredBorderConfigurationName;

	public BorderConfigurationNotRegisteredException(IGameConfiguration gameConfiguration, String notRegisteredBorderConfigurationName) {
		super(gameConfiguration);
		this.notRegisteredBorderConfigurationName = notRegisteredBorderConfigurationName;
	}

	@Override
	protected String getInternalMessage() {
		return "The configuration " + getNotRegisteredBorderConfigurationName() + " is not registered";
	}

	/**
	 * @return The name of the not registered border configuration.
	 */
	public String getNotRegisteredBorderConfigurationName() {
		return notRegisteredBorderConfigurationName;
	}
}
