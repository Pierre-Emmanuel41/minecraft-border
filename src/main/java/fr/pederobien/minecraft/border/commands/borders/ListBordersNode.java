package fr.pederobien.minecraft.border.commands.borders;

import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Supplier;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.interfaces.IBorderList;
import fr.pederobien.minecraft.managers.WorldManager;

public class ListBordersNode extends BordersNode {

	/**
	 * Creates a node that displays each border of the given list.
	 * 
	 * @param list The list associated to this node.
	 */
	protected ListBordersNode(Supplier<IBorderList> list) {
		super(list, "list", EBordersCode.BORDERS__LIST_BORDERS__EXPLANATION, l -> l != null);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String none = getMessage(sender, EBordersCode.BORDERS_NONE);
		StringJoiner joiner = new StringJoiner("\n");

		Optional<IBorder> optional = getList().getBorder(WorldManager.OVERWORLD);
		joiner.add(String.format("%s: %s", WorldManager.OVERWORLD_NAME, optional.isPresent() ? optional.get().getName() : none));

		optional = getList().getBorder(WorldManager.NETHER_WORLD);
		joiner.add(String.format("%s: %s", WorldManager.NETHER_WORLD_NAME, optional.isPresent() ? optional.get().getName() : none));

		optional = getList().getBorder(WorldManager.END_WORLD);
		joiner.add(String.format("%s: %s", WorldManager.END_WORLD_NAME, optional.isPresent() ? optional.get().getName() : none));

		sendSuccessful(sender, EBordersCode.BORDERS__LIST_BORDERS__ALL_REGISTERED_BORDERS, joiner);
		return true;
	}
}
