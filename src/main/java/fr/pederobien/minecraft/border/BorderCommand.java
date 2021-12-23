package fr.pederobien.minecraft.border;

import org.bukkit.plugin.java.JavaPlugin;

import fr.pederobien.minecraft.border.commands.BorderParent;
import fr.pederobien.minecraft.border.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.commands.AbstractParentCommand;

public class BorderCommand extends AbstractParentCommand<IBorderConfiguration> {

	public BorderCommand(JavaPlugin plugin) {
		super(plugin, new BorderParent(plugin));
	}
}
