package fr.pederobien.minecraft.border.commands.border;

import java.util.List;
import java.util.function.Supplier;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.game.impl.EGameCode;

public class InitialDiameterBorderNode extends BorderNode {

	/**
	 * Creates a node that modifies the initial diameter of the given border.
	 * 
	 * @param border The border associated to this node.
	 */
	protected InitialDiameterBorderNode(Supplier<IBorder> border) {
		super(border, "initialDiameter", EBorderCode.BORDER__INITIAL_BORDER_DIAMETER__EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		int diameter;
		try {
			if ((diameter = Integer.parseInt(args[0])) <= 0) {
				send(eventBuilder(sender, EBorderCode.BORDER__INITIAL_BORDER_DIAMETER__NEGATIVE_DIAMETER, getBorder().getName()));
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			send(eventBuilder(sender, EBorderCode.BORDER__INITIAL_BORDER_DIAMETER__DIAMETER_IS_MISSING, getBorder().getName()));
			return false;
		} catch (NumberFormatException e) {
			send(eventBuilder(sender, EGameCode.BAD_FORMAT, getMessage(sender, EBorderCode.BORDER__STRICTLY_POSITIVE_INTEGER__BAD_FORMAT)));
			return false;
		}

		getBorder().getInitialDiameter().set(diameter);
		sendSuccessful(sender, EBorderCode.BORDER__INITIAL_BORDER_DIAMETER__DIAMETER_UPDATED, getBorder().getName(), getBorder().getInitialDiameter());
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		switch (args.length) {
		case 1:
			return check(args[0], e -> isNotStrictInt(e), asList(getMessage(sender, EBorderCode.BORDER__INITIAL_BORDER_DIAMETER__COMPLETION)));
		default:
			return emptyList();
		}
	}
}
