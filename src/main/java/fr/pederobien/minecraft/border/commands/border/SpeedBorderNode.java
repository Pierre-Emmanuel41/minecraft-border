package fr.pederobien.minecraft.border.commands.border;

import java.util.List;
import java.util.function.Supplier;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.impl.EBorderCode;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.game.impl.DisplayHelper;
import fr.pederobien.minecraft.game.impl.EGameCode;

public class SpeedBorderNode extends BorderNode {

	/***
	 * Creates a node that modifies the speed of the given border.
	 * 
	 * @param border The border associated to this node.
	 */
	protected SpeedBorderNode(Supplier<IBorder> border) {
		super(border, "speed", EBorderCode.BORDER__SPEED_BORDER__EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		double speed;
		try {
			if ((speed = Double.parseDouble(args[0].replace(",", "."))) <= 0) {
				send(eventBuilder(sender, EBorderCode.BORDER__SPEED_BORDER__SPEED_IS_NEGATIVE, getBorder().getName()));
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			send(eventBuilder(sender, EBorderCode.BORDER__SPEED_BORDER__SPEED_IS_MISSING, getBorder().getName()));
			return false;
		} catch (NumberFormatException e) {
			send(eventBuilder(sender, EGameCode.BAD_FORMAT, getMessage(sender, EBorderCode.BORDER__STRICTLY_POSITIVE_DOUBLE__BAD_FORMAT)));
			return false;
		}

		getBorder().getSpeed().set(speed);
		sendSuccessful(sender, EBorderCode.BORDER__SPEED_BORDER__SPEED_UPATED, getBorder().getName(), speed, DisplayHelper.toString(getBorder().getMoveTime(), false));
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		switch (args.length) {
		case 1:
			return check(args[0], e -> isNotStrictDouble(e), asList(getMessage(sender, EBorderCode.BORDER__SPEED_BORDER__COMPLETION)));
		default:
			return emptyList();
		}
	}
}
