package fr.pederobien.minecraft.border.commands.borders;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.interfaces.IBorderList;
import fr.pederobien.minecraft.dictionary.impl.MinecraftMessageEvent.MinecraftMessageEventBuilder;
import fr.pederobien.minecraft.managers.EColor;
import fr.pederobien.minecraft.managers.WorldManager;

public class ListBordersNode extends BordersNode {

	/**
	 * Creates a node that displays each border of the given list.
	 * 
	 * @param list The list associated to this node.
	 */
	protected ListBordersNode(IBorderList list) {
		super(list, "list", EBordersCode.BORDERS__LIST_BORDERS__EXPLANATION, l -> l != null);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String none = getMessage(sender, EBordersCode.BORDERS_NONE);
		if (args.length == 0) {
			StringJoiner joiner = new StringJoiner("\n");

			Optional<IBorder> optional = getList().getBorder(WorldManager.OVERWORLD);
			joiner.add(String.format("%s: %s", WorldManager.OVERWORLD_NAME, optional.isPresent() ? optional.get().getName() : none));

			optional = getList().getBorder(WorldManager.NETHER_WORLD);
			joiner.add(String.format("%s: %s", WorldManager.NETHER_WORLD_NAME, optional.isPresent() ? optional.get().getName() : none));

			optional = getList().getBorder(WorldManager.END_WORLD);
			joiner.add(String.format("%s: %s", WorldManager.END_WORLD_NAME, optional.isPresent() ? optional.get().getName() : none));

			MinecraftMessageEventBuilder builder = eventBuilder(sender, EBordersCode.BORDERS__LIST_BORDERS__ALL_REGISTERED_BORDERS);
			send(builder.withPrefix(DEFAULT_PREFIX, EColor.GREEN).withSuffix(DEFAULT_SUFFIX, EColor.GREEN).build(joiner));
			return true;
		}

		String name = args[0];
		World world = WorldManager.getWorld(name);
		if (world == null) {
			send(eventBuilder(sender, EBordersCode.BORDERS__LIST_BORDERS__WORLD_NOT_FOUND, name));
			return false;
		}

		Optional<IBorder> optional = getList().getBorder(WorldManager.OVERWORLD);
		String borderName = String.format("%s: %s", WorldManager.OVERWORLD_NAME, optional.isPresent() ? optional.get().getName() : none);

		send(eventBuilder(sender, EBordersCode.BORDERS__LIST_BORDERS__REGISTERED_BORDER_FOR_WORLD, name, borderName));
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		switch (args.length) {
		case 1:
			return filter(WorldManager.getWorldNormalisedNames().stream(), args);
		default:
			return emptyList();
		}
	}
}
