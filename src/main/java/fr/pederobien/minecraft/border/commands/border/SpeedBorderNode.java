package fr.pederobien.minecraft.border.commands.border;

import java.util.List;

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
	protected SpeedBorderNode(IBorder border) {
		super(border, "speed", EBorderCode.BORDER__SPEED_BORDER__EXPLANATION, b -> b != null);
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
			send(eventBuilder(sender, EGameCode.BAD_FORMAT, EBorderCode.BORDER__INTEGER__BAD_FORMAT));
			return false;
		}

		getBorder().setSpeed(speed);
		String moveTime = DisplayHelper.toString(getBorder().getMoveTime(), false);
		send(eventBuilder(sender, EBorderCode.BORDER__SPEED_BORDER__SPEED_UPATED, getBorder().getName(), speed, moveTime));
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
