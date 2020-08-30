package fr.pederobien.minecraftborder.commands.configurations;

import fr.pederobien.minecraftborder.interfaces.IGameBorderConfiguration;
import fr.pederobien.minecraftgameplateform.commands.configurations.AbstractGameConfigurationEdition;

public class CommonBorders<T extends IGameBorderConfiguration> extends AbstractGameConfigurationEdition<T> {

	protected CommonBorders() {
		super(EBordersLabel.BORDERS, EBordersMessageCode.BORDERS__EXPLANATION);
		addEdition(BordersEditionFactory.addBorders());
		addEdition(BordersEditionFactory.removeBorders());
		addEdition(BordersEditionFactory.listBorders());
		addEdition(BordersEditionFactory.detailsBorders());
		addEdition(BordersEditionFactory.reloadBorders());
	}
}
