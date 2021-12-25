package fr.pederobien.minecraft.border.commands.configurations;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.exceptions.BorderConfigurationNotRegisteredException;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.interfaces.IBorderList;
import fr.pederobien.minecraftgameplateform.exceptions.configurations.ConfigurationNotFoundException;

public class ReloadBorders<T extends IBorderList> extends AbstractGameBorderConfigurationEdition<T> {

	protected ReloadBorders() {
		super(EBordersLabel.RELOAD, EBordersMessageCode.RELOAD_BORDERS__EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		List<IBorder> removed, reload;
		try {
			removed = getConfigurationsFromGameConfiguration(args);
			get().remove(removed);
			reload = getConfigurations(args);
			for (IBorder border : reload)
				get().add(border);
		} catch (BorderConfigurationNotRegisteredException e) {
			sendSynchro(sender, EBordersMessageCode.RELOAD_BORDERS__BORDER_NOT_REGISTERED, e.getNotRegisteredBorderConfigurationName(), get().getName());
			return false;
		} catch (ConfigurationNotFoundException e) {
			sendSynchro(sender, EBordersMessageCode.RELOAD_BORDERS__BORDER_DOES_NOT_EXIST, e.getConfigurationName());
			return false;
		}

		switch (reload.size()) {
		case 0:
			sendSynchro(sender, EBordersMessageCode.RELOAD_BORDERS__ANY_BORDER_RELOADED);
			break;
		case 1:
			sendSynchro(sender, EBordersMessageCode.RELOAD_BORDERS__ONE_BORDER_RELOADED, args[0]);
			break;
		default:
			sendSynchro(sender, EBordersMessageCode.RELOAD_BORDERS__SEVERAL_BORDERS_RELOADED, concat(args));
			break;
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		return filter(get().getBorders().stream().map(conf -> conf.getName()), args);
	}
}
