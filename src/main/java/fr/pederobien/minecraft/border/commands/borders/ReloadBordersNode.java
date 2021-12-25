package fr.pederobien.minecraft.border.commands.borders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.interfaces.IBorderList;
import fr.pederobien.minecraft.border.persistence.BorderPersistence;
import fr.pederobien.minecraft.game.platform.impl.PlatformPersistence;

public class ReloadBordersNode extends BordersNode {
	private PlatformPersistence<IBorder> persistence;

	/**
	 * Creates a node that reload the characteristics of borders in the given list.
	 * 
	 * @param list The list associated to this node.
	 */
	protected ReloadBordersNode(IBorderList list) {
		super(list, "reload", EBordersCode.BORDERS__RELOAD_BORDERS__EXPLANATION, l -> l != null);
		persistence = new BorderPersistence().getPersistence();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		List<IBorder> borders = new ArrayList<IBorder>();

		List<String> existingBorders = persistence.list();
		for (String border : args) {
			Optional<IBorder> optBorder = getList().getBorder(border);
			if (!optBorder.isPresent()) {
				send(eventBuilder(sender, EBordersCode.BORDERS__RELOAD_BORDERS__BORDER_NOT_REGISTERED, border, getList().getName()));
				return false;
			}

			if (!existingBorders.contains(border)) {
				send(eventBuilder(sender, EBordersCode.BORDERS__RELOAD_BORDERS__NO_BORDER_FILE, border));
				return false;
			}

			borders.add(optBorder.get());
		}

		String borderNames = concat(args);

		for (IBorder border : borders)
			border.reload();

		switch (borders.size()) {
		case 0:
			send(eventBuilder(sender, EBordersCode.BORDERS__RELOAD_BORDERS__NO_BORDER_RELOADED).build());
			break;
		case 1:
			send(eventBuilder(sender, EBordersCode.BORDERS__RELOAD_BORDERS__ONE_BORDER_RELOADED, getList().getName(), borderNames));
			break;
		default:
			send(eventBuilder(sender, EBordersCode.BORDERS__RELOAD_BORDERS__SEVERAL_BORDERS_RELOADED, getList().getName(), borderNames));
			break;
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		return filter(getList().stream().map(conf -> conf.getName()), args);
	}
}
