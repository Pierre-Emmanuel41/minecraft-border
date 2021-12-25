package fr.pederobien.minecraft.border.commands.border;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.commands.EBorderCode;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.game.impl.DisplayHelper;
import fr.pederobien.minecraft.game.impl.EGameCode;
import fr.pederobien.minecraft.game.platform.impl.EPlatformCode;

public class StartTimeBorderNode extends BorderNode {

	/**
	 * Creates a node that modifies the time after which the border moves from its initial to final diameter.
	 * 
	 * @param border The border associated to this node.
	 */
	protected StartTimeBorderNode(IBorder border) {
		super(border, "startTime", EBorderCode.BORDER__START_TIME_BORDER__EXPLANATION, b -> b != null);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		LocalTime startTime;
		try {
			startTime = LocalTime.parse(args[0]);
		} catch (IndexOutOfBoundsException e) {
			return false;
		} catch (DateTimeParseException e) {
			send(eventBuilder(sender, EGameCode.BAD_FORMAT, EPlatformCode.TIME_FORMAT__COMPLETION));
			return false;
		}

		getBorder().setStartTime(startTime);

		if (getBorder().getStartTime().equals(LocalTime.MIN))
			send(eventBuilder(sender, EBorderCode.BORDER__START_TIME_BORDER__BORDER_MOVES_AT_THE_BEGINNING, getBorder().getName()));
		else {
			List<Object> arguments = asList(getBorder().getName(), DisplayHelper.toString(getBorder().getStartTime(), false));
			send(eventBuilder(sender, EBorderCode.BORDER__START_TIME_BORDER__START_TIME_UPDATED, arguments));
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
