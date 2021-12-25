package fr.pederobien.minecraft.border.commands.borders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.interfaces.IBorderList;

public class RemoveBordersNode extends BordersNode {

	/**
	 * Creates a node that removes a border from the given list.
	 * 
	 * @param list The list associated to this node.
	 */
	protected RemoveBordersNode(IBorderList list) {
		super(list, "remove", EBordersCode.BORDERS__REMOVE_BORDERS__EXPLANATION, l -> l != null);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		List<IBorder> borders = new ArrayList<IBorder>();

		for (String border : args) {
			Optional<IBorder> optBorder = getList().getBorder(border);
			if (!optBorder.isPresent()) {
				send(eventBuilder(sender, EBordersCode.BORDERS__REMOVE_BORDERS__BORDER_NOT_REGISTERED, border, getList().getName()));
				return false;
			}

			borders.add(optBorder.get());
		}

		String borderNames = concat(args);

		for (IBorder border : borders)
			getList().remove(border);

		switch (borders.size()) {
		case 0:
			send(eventBuilder(sender, EBordersCode.BORDERS__REMOVE_BORDERS__NO_BORDER_REMOVED, getList().getName()));
			break;
		case 1:
			send(eventBuilder(sender, EBordersCode.BORDERS__REMOVE_BORDERS__ONE_BORDER_REMOVED, borderNames, getList().getName()));
			break;
		default:
			send(eventBuilder(sender, EBordersCode.BORDERS__REMOVE_BORDERS__SEVERAL_BORDERS_REMOVED, borderNames, getList().getName()));
			break;
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> alreadyMentionnedBorder = asList(args);
		return filter(getList().stream().map(conf -> conf.getName()).filter(name -> !alreadyMentionnedBorder.contains(name)), args);
	}
}
