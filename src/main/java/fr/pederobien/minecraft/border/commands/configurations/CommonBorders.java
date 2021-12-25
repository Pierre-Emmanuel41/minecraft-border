package fr.pederobien.minecraft.border.commands.configurations;

import fr.pederobien.minecraft.border.interfaces.IBorderList;
import fr.pederobien.minecraftgameplateform.commands.configurations.AbstractGameConfigurationEdition;

public class CommonBorders<T extends IBorderList> extends AbstractGameConfigurationEdition<T> {

	protected CommonBorders() {
		super(EBordersLabel.BORDERS, EBordersMessageCode.BORDERS__EXPLANATION);
		addEdition(BordersEditionFactory.addBorders());
		addEdition(BordersEditionFactory.removeBorders());
		addEdition(BordersEditionFactory.listBorders());
		addEdition(BordersEditionFactory.detailsBorders());
		addEdition(BordersEditionFactory.reloadBorders());
	}
}
