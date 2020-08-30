package fr.pederobien.minecraftborder.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.dictionary.ECommonMessageCode;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractLabelEdition;

public class CenterBorder extends AbstractLabelEdition<IBorderConfiguration> {

	protected CenterBorder() {
		super(EBorderLabel.CENTER, EBorderMessageCode.CENTER_BORDER__EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {
			get().setBorderCenter(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			sendMessageToSender(sender, EBorderMessageCode.CENTER_BORDER__CENTER_DEFINED, get().getBorderCenter().getX(), get().getBorderCenter().getZ());
			return true;
		} catch (IndexOutOfBoundsException e) {
			sendMessageToSender(sender, ECommonMessageCode.COMMON_MISSING_COORDINATES);
			return false;
		} catch (NumberFormatException e) {
			sendMessageToSender(sender, ECommonMessageCode.COMMON_BAD_INTEGER_FORMAT);
			return false;
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		switch (args.length) {
		case 1:
			return check(args[0], e -> isNotStrictInt(e), Arrays.asList("<X> <Z>"));
		case 2:
			return check(args[1], e -> isNotStrictInt(e), check(args[0], e -> isStrictInt(e), Arrays.asList("<Z>")));
		default:
			return emptyList();
		}
	}
}
