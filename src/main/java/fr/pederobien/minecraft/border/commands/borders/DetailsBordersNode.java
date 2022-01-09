package fr.pederobien.minecraft.border.commands.borders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Supplier;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.commands.border.DetailsBorderNode;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.interfaces.IBorderList;
import fr.pederobien.minecraft.managers.EColor;

public class DetailsBordersNode extends BordersNode {
	private static final String BORDER_SEPARATOR = "\n-------------\n";
	private DetailsBorderNode detailsNode;

	/**
	 * Creates a node that display the characteristics of one or several borders from the given list.
	 * 
	 * @param list        The list associated to this node.
	 * @param detailsNode The node to get the details of a border.
	 */
	protected DetailsBordersNode(Supplier<IBorderList> list, DetailsBorderNode detailsNode) {
		super(list, "details", EBordersCode.BORDERS__DETAILS__EXPLANATION, l -> l != null);
		this.detailsNode = detailsNode;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		List<IBorder> borders = args.length == 0 ? getList().toList() : new ArrayList<IBorder>();

		for (String border : args) {
			Optional<IBorder> optBorder = getList().getBorder(border);
			if (!optBorder.isPresent()) {
				send(eventBuilder(sender, EBordersCode.BORDERS__DETAILS_BORDERS__BORDER_NOT_FOUND, getList().getName(), border));
				return false;
			}

			borders.add(optBorder.get());
		}

		String details = getDetails(sender, borders).replace(BORDER_SEPARATOR, EColor.WHITE.getInColor(BORDER_SEPARATOR, EColor.GOLD));
		switch (borders.size()) {
		case 0:
			sendSuccessful(sender, EBordersCode.BORDERS__DETAILS_BORDERS__NO_ELEMENT, getList().getName());
			break;
		case 1:
			sendSuccessful(sender, EBordersCode.BORDERS__DETAILS_BORDERS__ONE_ELEMENT, getList().getName(), details);
			break;
		default:
			sendSuccessful(sender, EBordersCode.BORDERS__DETAILS_BORDERS__SEVERAL_ELEMENTS, getList().getName(), details);
			break;
		}
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
	public String getDetails(CommandSender sender, List<IBorder> borders) {
		StringJoiner joiner = new StringJoiner(BORDER_SEPARATOR);

		for (IBorder border : borders)
			joiner.add(detailsNode.getDetails(sender, border));

		return joiner.toString();
	}
}
