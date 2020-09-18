package fr.pederobien.minecraftborder.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.dictionary.ECommonMessageCode;
import fr.pederobien.minecraftgameplateform.exceptions.worldstructure.WorldNotFoundException;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractLabelEdition;
import fr.pederobien.minecraftmanagers.WorldManager;

public class WorldBorder extends AbstractLabelEdition<IBorderConfiguration> {

	protected WorldBorder() {
		super(EBorderLabel.WORLD, EBorderMessageCode.WORLD_BORDER__EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String name = "";
		try {
			name = args[0];
			get().setWorld(name);
			sendSynchro(sender, EBorderMessageCode.WORLD_BORDER__WORLD_DEFINED, get().getName(), WorldManager.getWorldNameNormalised(get().getWorld()));
			return true;
		} catch (IndexOutOfBoundsException e) {
			sendSynchro(sender, EBorderMessageCode.WORLD_BORDER__WORLD_NAME_IS_MISSING);
			return false;
		} catch (WorldNotFoundException e) {
			sendSynchro(sender, ECommonMessageCode.COMMON_WORLD_DOES_NOT_EXIST, name);
			return false;
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		switch (args.length) {
		case 1:
			return filter(WorldManager.getWorldNormalisedNames().stream(), args[0]);
		default:
			return emptyList();
		}
	}
}
