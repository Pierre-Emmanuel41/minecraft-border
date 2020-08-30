package fr.pederobien.minecraftborder;

import org.bukkit.plugin.java.JavaPlugin;

import fr.pederobien.minecraftborder.commands.BorderParent;
import fr.pederobien.minecraftgameplateform.commands.AbstractParentCommand;

public class BorderCommand extends AbstractParentCommand<IBorderConfiguration> {

	public BorderCommand(JavaPlugin plugin) {
		super(plugin, new BorderParent(plugin));
	}
}
