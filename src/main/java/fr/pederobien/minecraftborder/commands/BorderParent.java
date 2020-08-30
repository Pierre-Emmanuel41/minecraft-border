package fr.pederobien.minecraftborder.commands;

import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftborder.persistence.BorderPersistence;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractParentPersistenceEdition;

public class BorderParent extends AbstractParentPersistenceEdition<IBorderConfiguration> {

	public BorderParent(Plugin plugin) {
		super("border", EBorderMessageCode.BORDER__EXPLANATION, plugin, BorderPersistence.getInstance());

		addEdition(BorderEditionsFactory.newBorder().setModifiable(false));
		addEdition(BorderEditionsFactory.currentBorder());
		addEdition(BorderEditionsFactory.loadBorder().setModifiable(false));
		addEdition(BorderEditionsFactory.deleteBorder().setModifiable(false));
		addEdition(BorderEditionsFactory.listBorder().setModifiable(false));
		addEdition(BorderEditionsFactory.renameBorder());
		addEdition(BorderEditionsFactory.saveBorder());
		addEdition(BorderEditionsFactory.worldBorder());
		addEdition(BorderEditionsFactory.centerBorder());
		addEdition(BorderEditionsFactory.initialBorderDiameter());
		addEdition(BorderEditionsFactory.finalBorderDiameter());
		addEdition(BorderEditionsFactory.speedBorder());
		addEdition(BorderEditionsFactory.startTimeBorder());
		addEdition(BorderEditionsFactory.moveTimeBorder());
	}
}
