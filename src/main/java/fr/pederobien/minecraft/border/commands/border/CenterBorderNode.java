package fr.pederobien.minecraft.border.commands.border;

import java.util.List;
import java.util.function.Supplier;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.impl.EBorderCode;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.game.impl.DisplayHelper;
import fr.pederobien.minecraft.game.impl.EGameCode;
import fr.pederobien.minecraft.managers.WorldManager;

public class CenterBorderNode extends BorderNode {

	/**
	 * Creates a node that modifies the center of the given border.
	 * 
	 * @param border The border associated to this node.
	 */
	protected CenterBorderNode(Supplier<IBorder> border) {
		super(border, "center", EBorderCode.BORDER__CENTER_BORDER__EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		int xCoord;
		try {
			xCoord = Integer.parseInt(args[0]);
		} catch (IndexOutOfBoundsException e) {
			send(eventBuilder(sender, EBorderCode.BORDER__CENTER_BORDER__X_IS_MISSING, getBorder().getName()));
			return false;
		} catch (NumberFormatException e) {
			send(eventBuilder(sender, EGameCode.BAD_FORMAT, getMessage(sender, EBorderCode.BORDER__INTEGER__BAD_FORMAT)));
			return false;
		}

		int yCoord;
		try {
			yCoord = Integer.parseInt(args[1]);
			if (yCoord < 0) {
				send(eventBuilder(sender, EBorderCode.BORDER__CENTER_BORDER__Y_IS_NEGATIVE, getBorder().getName()));
				return false;
			}
			if (yCoord > 256) {
				send(eventBuilder(sender, EBorderCode.BORDER__CENTER_BORDER__Y_IS_GREATER_THAN_256, getBorder().getName()));
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			send(eventBuilder(sender, EBorderCode.BORDER__CENTER_BORDER__Y_IS_MISSING, getBorder().getName()));
			return false;
		} catch (NumberFormatException e) {
			send(eventBuilder(sender, EGameCode.BAD_FORMAT, getMessage(sender, EBorderCode.BORDER__INTEGER__BAD_FORMAT)));
			return false;
		}

		int zCoord;
		try {
			zCoord = Integer.parseInt(args[2]);
		} catch (IndexOutOfBoundsException e) {
			send(eventBuilder(sender, EBorderCode.BORDER__CENTER_BORDER__Z_IS_MISSING, getBorder().getName()));
			return false;
		} catch (NumberFormatException e) {
			send(eventBuilder(sender, EGameCode.BAD_FORMAT, getMessage(sender, EBorderCode.BORDER__INTEGER__BAD_FORMAT)));
			return false;
		}

		getBorder().getCenter().set(WorldManager.getBlockAt(getBorder().getWorld().get(), xCoord, yCoord, zCoord));
		sendSuccessful(sender, EBorderCode.BORDER__CENTER_BORDER__CENTER_UPDATED, getBorder().getName(), DisplayHelper.toString(getBorder().getCenter().get()));
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		switch (args.length) {
		case 1:
			return check(args[0], e -> isNotStrictInt(e), asList("<X> <Y> <Z>"));
		case 2:
			return check(args[1], e -> isNotStrictInt(e), check(args[0], e -> isStrictInt(e), asList("<Y> <Z>")));
		case 3:
			return check(args[2], e -> isNotStrictInt(e), check(args[1], e -> isStrictInt(e), asList("<Z>")));
		default:
			return emptyList();
		}
	}
}
