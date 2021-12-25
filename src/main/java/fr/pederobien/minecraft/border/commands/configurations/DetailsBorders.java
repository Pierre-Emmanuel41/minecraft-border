package fr.pederobien.minecraft.border.commands.configurations;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.exceptions.BorderConfigurationNotRegisteredException;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.interfaces.IBorderList;

public class DetailsBorders<T extends IBorderList> extends AbstractGameBorderConfigurationEdition<T> {

	protected DetailsBorders() {
		super(EBordersLabel.DETAILS, EBordersMessageCode.DETAILS__EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String delimiter = "\n----------------------------------------------------\n";
		StringJoiner joiner = new StringJoiner(delimiter, delimiter, delimiter);
		if (args.length == 0) {
			for (IBorder configuration : get().getBorders())
				joiner.add(configuration.toString());
			sendSynchro(sender, EBordersMessageCode.DETAILS_BORDERS__DETAILS_ON_ALL_REGISTERED_BORDERS, get().getName(), joiner);
			return true;
		}
		List<IBorder> configurations = null;
		try {
			configurations = getConfigurationsFromGameConfiguration(args);
			for (IBorder configuration : configurations)
				joiner.add(configuration.toString());
		} catch (BorderConfigurationNotRegisteredException e) {
			sendSynchro(sender, EBordersMessageCode.DETAILS_BORDERS__BORDER_NOT_REGISTERED, e.getNotRegisteredBorderConfigurationName(), get().getName());
		}

		sendSynchro(sender, EBordersMessageCode.DETAILS_BORDERS__DETAILS_ON_SOME_BORDERS, concatNames(configurations), joiner);
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		return filter(get().getBorders().stream().map(conf -> conf.getName()).filter(conf -> !Arrays.asList(args).contains(conf)), args);
	}
}
