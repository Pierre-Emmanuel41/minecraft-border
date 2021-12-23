package fr.pederobien.minecraft.border.editions;

import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraft.border.commands.configurations.GameBorderConfigurationEditionFactory;
import fr.pederobien.minecraft.border.interfaces.IGameBorderConfiguration;
import fr.pederobien.minecraftdictionary.interfaces.IMinecraftMessageCode;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractGameConfigurationParent;
import fr.pederobien.minecraftgameplateform.interfaces.element.persistence.IMinecraftPersistence;

public abstract class AbstractGameBorderConfigurationParent<T extends IGameBorderConfiguration> extends AbstractGameConfigurationParent<T> {

	public AbstractGameBorderConfigurationParent(String label, IMinecraftMessageCode explanation, Plugin plugin, IMinecraftPersistence<T> persistence) {
		super(label, explanation, plugin, persistence);

		addEdition(GameBorderConfigurationEditionFactory.bordersEdition());
	}
}
