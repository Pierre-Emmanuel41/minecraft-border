package fr.pederobien.minecraft.border.commands.border;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.function.Supplier;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.impl.EBorderCode;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.game.impl.DisplayHelper;
import fr.pederobien.minecraft.game.impl.EGameCode;
import fr.pederobien.minecraft.platform.impl.EPlatformCode;

public class MoveTimeBorderNode extends BorderNode {

	/**
	 * Creates a node that modifies the move time of the given border.
	 * 
	 * @param border The border associated to this node.
	 */
	protected MoveTimeBorderNode(Supplier<IBorder> border) {
		super(border, "moveTime", EBorderCode.BORDER__MOVE_TIME_BORDER__EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		LocalTime moveTime;
		try {
			moveTime = LocalTime.parse(args[0]);
		} catch (IndexOutOfBoundsException e) {
			send(eventBuilder(sender, EBorderCode.BORDER__MOVE_TIME_BORDER__TIME_IS_MISSING, getBorder().getName()));
			return false;
		} catch (DateTimeParseException e) {
			send(eventBuilder(sender, EGameCode.BAD_FORMAT, getMessage(sender, EPlatformCode.TIME_FORMAT__COMPLETION)));
			return false;
		}

		getBorder().setMoveTime(moveTime);
		if (moveTime.equals(LocalTime.MIN))
			sendSuccessful(sender, EBorderCode.BORDER__MOVE_TIME_BORDER__INSTANTLY_MOVE, getBorder().getName());
		else {
			String moveTimeFormat = DisplayHelper.toString(getBorder().getMoveTime(), false);
			sendSuccessful(sender, EBorderCode.BORDER__MOVE_TIME_BORDER__MOVE_TIME_UPDATED, getBorder(), moveTimeFormat, getBorder().getSpeed());
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
