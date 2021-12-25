package fr.pederobien.minecraft.border.commands.border;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.impl.EBorderCode;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.game.impl.DisplayHelper;
import fr.pederobien.minecraft.game.platform.impl.EPlatformCode;

public class MoveTimeBorderNode extends BorderNode {

	/**
	 * Creates a node that modifies the move time of the given border.
	 * 
	 * @param border The border associated to this node.
	 */
	protected MoveTimeBorderNode(IBorder border) {
		super(border, "moveTime", EBorderCode.BORDER__MOVE_TIME_BORDER__EXPLANATION, b -> b != null);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		LocalTime moveTime;
		try {
			moveTime = LocalTime.parse(args[0]);
		} catch (IndexOutOfBoundsException e) {
			return false;
		} catch (DateTimeParseException e) {
			return false;
		}

		getBorder().setMoveTime(moveTime);
		if (moveTime.equals(LocalTime.MIN))
			send(eventBuilder(sender, EBorderCode.BORDER__MOVE_TIME_BORDER__INSTANTLY_MOVE, getBorder().getName()));
		else {
			String moveTimeFormat = DisplayHelper.toString(getBorder().getMoveTime(), false);
			send(eventBuilder(sender, EBorderCode.BORDER__MOVE_TIME_BORDER__MOVE_TIME_UPDATED, getBorder(), moveTimeFormat, getBorder().getSpeed()));
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		switch (args.length) {
		case 1:
			return asList(getMessage(sender, EPlatformCode.TIME_FORMAT__COMPLETION));
		default:
			return emptyList();
		}
	}
}
