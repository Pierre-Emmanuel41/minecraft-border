package fr.pederobien.minecraft.border.commands.border;

import java.util.List;
import java.util.function.Supplier;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.impl.EBorderCode;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.managers.WorldManager;

public class WorldBorderNode extends BorderNode {

	/**
	 * Creates a node the set the world associated to the given border.
	 * 
	 * @param border The border associated to this node.
	 */
	protected WorldBorderNode(Supplier<IBorder> border) {
		super(border, "world", EBorderCode.BORDER__WORLD_BORDER__EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String name;
		try {
			name = args[0];
		} catch (IndexOutOfBoundsException e) {
			send(eventBuilder(sender, EBorderCode.BORDER__WORLD_BORDER__WORLD_NAME_IS_MISSING).build());
			return false;
		}

		World world = WorldManager.getWorld(name);
		if (world == null) {
			send(eventBuilder(sender, EBorderCode.BORDER__WORLD_BORDER__WORLD_NOT_FOUND, name));
			return false;
		}

		getBorder().setWorld(world);
		sendSuccessful(sender, EBorderCode.BORDER__WORLD_BORDER__WORLD_UPDATED, getBorder().getName(), name);
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		switch (args.length) {
		case 1:
			return filter(WorldManager.getWorldNormalisedNames().stream(), args[0]);
		default:
			return emptyList();
		}
	}
}
