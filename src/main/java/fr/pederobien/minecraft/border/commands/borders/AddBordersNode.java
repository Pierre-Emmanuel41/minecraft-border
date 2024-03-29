package fr.pederobien.minecraft.border.commands.borders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.interfaces.IBorderList;
import fr.pederobien.minecraft.border.persistence.BorderPersistence;
import fr.pederobien.minecraft.platform.interfaces.IPlatformPersistence;

public class AddBordersNode extends BordersNode {
	private IPlatformPersistence<IBorder> persistence;

	/**
	 * Creates a node that adds borders to the given list.
	 * 
	 * @param list The list of borders associated to this node.
	 */
	protected AddBordersNode(Supplier<IBorderList> list) {
		super(list, "add", EBordersCode.BORDERS__ADD_BORDERS__EXPLANATION, l -> l != null);
		this.persistence = new BorderPersistence().getPersistence();
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> alreadyMentionned = asList(args);
		List<String> existingBorders = persistence.list();
		Predicate<String> filter = name -> !(alreadyMentionned.contains(name) || getList().getBorder(name).isPresent());
		return filter(existingBorders.stream().filter(filter), args);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		List<IBorder> borders = new ArrayList<IBorder>();

		List<String> existingBorders = persistence.list();
		for (String border : args) {
			if (!existingBorders.contains(border)) {
				send(eventBuilder(sender, EBordersCode.BORDERS__ADD_BORDERS__BORDER_DOES_NOT_EXIST, border, getList().getName()));
				return false;
			}

			Optional<IBorder> optBorder = getList().getBorder(border);
			if (optBorder.isPresent()) {
				send(eventBuilder(sender, EBordersCode.BORDERS__ADD_BORDERS__BORDER_ALREADY_REGISTERED, border, getList().getName()));
				return false;
			}

			if (!persistence.deserialize(border)) {
				send(eventBuilder(sender, EBordersCode.BORDERS__ADD_BORDERS__FAIL_TO_LOAD, border));
				return false;
			}

			borders.add(persistence.get());
		}

		String borderNames = concat(args);

		for (IBorder border : borders)
			getList().add(border);

		switch (borders.size()) {
		case 0:
			sendSuccessful(sender, EBordersCode.BORDERS__ADD_BORDERS__NO_BORDER_ADDED, getList().getName());
			break;
		case 1:
			sendSuccessful(sender, EBordersCode.BORDERS__ADD_BORDERS__ONE_BORDER_ADDED, borderNames, getList().getName());
			break;
		default:
			sendSuccessful(sender, EBordersCode.BORDERS__ADD_BORDERS__SEVERAL_BORDERS_ADDED, borderNames, getList().getName());
			break;
		}
		return true;
	}
}
