package fr.pederobien.minecraft.border.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.dictionary.ECommonMessageCode;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractLabelEdition;

public class InitialBorderDiameter extends AbstractLabelEdition<IBorderConfiguration> {

	protected InitialBorderDiameter() {
		super(EBorderLabel.INITIAL_DIAMETER, EBorderMessageCode.INITIAL_BORDER_DIAMETER__EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {
			int diameter = Integer.parseInt(args[0]);
			if (diameter <= 0) {
				sendSynchro(sender, EBorderMessageCode.INITIAL_BORDER_DIAMETER__NEGATIVE_DIAMETER, get().getName());
				return false;
			}

			get().setInitialBorderDiameter(diameter);
			sendSynchro(sender, EBorderMessageCode.INITIAL_BORDER_DIAMETER__DIAMETER_DEFINED, get().getName(), get().getInitialBorderDiameter());
			return true;
		} catch (IndexOutOfBoundsException e) {
			sendSynchro(sender, EBorderMessageCode.INITIAL_BORDER_DIAMETER__DIAMETER_IS_MISSING, get().getName());
			return false;
		} catch (NumberFormatException e) {
			sendSynchro(sender, ECommonMessageCode.COMMON_BAD_INTEGER_FORMAT);
			return false;
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		switch (args.length) {
		case 1:
			return check(args[0], e -> isNotStrictInt(e), asList(getMessage(sender, EBorderMessageCode.INITIAL_BORDER_DIAMETER__ON_TAB_COMPLETE)));
		default:
			return emptyList();
		}
	}
}
