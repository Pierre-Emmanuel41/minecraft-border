package fr.pederobien.minecraftborder.commands;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.dictionary.ECommonMessageCode;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractLabelEdition;

public class StartTimeBorder extends AbstractLabelEdition<IBorderConfiguration> {

	protected StartTimeBorder() {
		super(EBorderLabel.START_TIME, EBorderMessageCode.START_TIME_BORDER__EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {
			get().setStartTime(LocalTime.parse(args[0]));
			if (get().getStartTime().getHour() == 0 && get().getStartTime().getMinute() == 0 && get().getStartTime().getSecond() == 0) {
				sendMessageToSender(sender, EBorderMessageCode.START_TIME_BORDER__BORDER_MOVES_AT_THE_BEGINNING, get().getName());
				return true;
			}
		} catch (IndexOutOfBoundsException e) {
			sendMessageToSender(sender, EBorderMessageCode.START_TIME_BORDER__TIME_IS_MISSING);
			return false;
		} catch (DateTimeParseException e) {
			sendMessageToSender(sender, ECommonMessageCode.COMMON_BAD_TIME_FORMAT);
			return false;
		}

		sendMessageToSender(sender, EBorderMessageCode.START_TIME_BORDER__START_TIME_DEFINED, get().getName(), toString(get().getStartTime(), false));
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		switch (args.length) {
		case 1:
			return Arrays.asList(getMessageFromDictionary(sender, ECommonMessageCode.COMMON_TIME_TAB_COMPLETE));
		default:
			return emptyList();
		}
	}
}
