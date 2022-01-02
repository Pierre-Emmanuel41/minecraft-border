package fr.pederobien.minecraft.border.commands.borders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.commands.border.DetailsBorderNode;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.interfaces.IBorderList;

public class DetailsBordersNode extends BordersNode {
	private DetailsBorderNode detailsNode;

	/**
	 * Creates a node that display the characteristics of one or several borders from the given list.
	 * 
	 * @param list        The list associated to this node.
	 * @param detailsNode The node to get the details of a border.
	 */
	protected DetailsBordersNode(IBorderList list, DetailsBorderNode detailsNode) {
		super(list, "details", EBordersCode.BORDERS__DETAILS__EXPLANATION, l -> l != null);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		List<IBorder> borders = args.length == 0 ? getList().toList() : new ArrayList<IBorder>();

		for (String border : args) {
			Optional<IBorder> optBorder = getList().getBorder(border);
			if (!optBorder.isPresent()) {
				send(eventBuilder(sender, EBordersCode.BORDERS__DETAILS_BORDERS__BORDER_NOT_REGISTERED, getList().getName(), border));
				return false;
			}

			borders.add(optBorder.get());
		}

		sender.sendMessage(getDetails(sender, getList()));
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> alreadyMentionned = asList(args);
		return filter(getList().stream().map(border -> border.getName()).filter(name -> !alreadyMentionned.contains(name)), args);
	}

	/**
	 * Get the details of each border from the border list. Each parameter name are translated according to the sender nationality.
	 * 
	 * @param sender  The sender used to translate parameter name.
	 * @param borders The list of borders.
	 * 
	 * @return The border details.
	 */
	public String getDetails(CommandSender sender, IBorderList borders) {
		StringJoiner joiner = new StringJoiner("-------------\n", DEFAULT_PREFIX, DEFAULT_SUFFIX);

		for (IBorder border : borders.toList())
			joiner.add(detailsNode.getDetails(sender, border));

		return joiner.toString();
	}
}
