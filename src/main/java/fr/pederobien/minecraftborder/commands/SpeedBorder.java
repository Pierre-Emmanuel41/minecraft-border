package fr.pederobien.minecraftborder.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraftborder.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.dictionary.ECommonMessageCode;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractLabelEdition;

public class SpeedBorder extends AbstractLabelEdition<IBorderConfiguration> {

	protected SpeedBorder() {
		super(EBorderLabel.SPEED, EBorderMessageCode.SPEED_BORDER__EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {
			double speed = Double.parseDouble(args[0]);
			if (speed <= 0) {
				sendMessageToSender(sender, EBorderMessageCode.SPEED_BORDER__SPEED_IS_NEGATIVE);
				return false;
			}
			get().setBorderSpeed(speed);
			sendMessageToSender(sender, EBorderMessageCode.SPEED_BORDER__SPEED_DEFINED, get().getName(), get().getBorderSpeed(), toString(get().getMoveTime(), false));
			return true;
		} catch (IndexOutOfBoundsException e) {
			sendMessageToSender(sender, EBorderMessageCode.SPEED_BORDER__SPEED_IS_MISSING);
			return false;
		} catch (NumberFormatException e) {
			sendMessageToSender(sender, ECommonMessageCode.COMMON_BAD_DOUBLE_FORMAT);
			return false;
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		switch (args.length) {
		case 1:
			return check(args[0], e -> isNotStrictDouble(e), Arrays.asList(getMessageFromDictionary(sender, EBorderMessageCode.SPEED_BORDER__ON_TAB_COMPLETE)));
		default:
			return emptyList();
		}
	}
}
