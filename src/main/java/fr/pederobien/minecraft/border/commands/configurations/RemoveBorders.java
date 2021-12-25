package fr.pederobien.minecraft.border.commands.configurations;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.exceptions.BorderConfigurationNotRegisteredException;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.interfaces.IBorderList;

public class RemoveBorders<T extends IBorderList> extends AbstractGameBorderConfigurationEdition<T> {

	protected RemoveBorders() {
		super(EBordersLabel.REMOVE, EBordersMessageCode.REMOVE_BORDERS__EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		List<IBorder> configurations;
		try {
			configurations = getConfigurationsFromGameConfiguration(args);
			get().remove(configurations);
		} catch (BorderConfigurationNotRegisteredException e) {
			sendSynchro(sender, EBordersMessageCode.REMOVE_BORDERS__BORDER_NOT_REGISTERED, e.getNotRegisteredBorderConfigurationName(), get().getName());
			return false;
		}

		switch (configurations.size()) {
		case 0:
			sendSynchro(sender, EBordersMessageCode.REMOVE_BORDERS__NO_BORDER_REMOVED, get().getName());
			break;
		case 1:
			sendSynchro(sender, EBordersMessageCode.REMOVE_BORDERS__ONE_BORDER_REMOVED, concatNames(configurations), get().getName());
			break;
		default:
			sendSynchro(sender, EBordersMessageCode.REMOVE_BORDERS__SEVERAL_BORDERS_REMOVED, concatNames(configurations), get().getName());
			break;
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> alreadyMentionnedConfiguration = Arrays.asList(args);
		return filter(get().getBorders().stream().map(conf -> conf.getName()).filter(name -> !alreadyMentionnedConfiguration.contains(name)), args);
	}
}
