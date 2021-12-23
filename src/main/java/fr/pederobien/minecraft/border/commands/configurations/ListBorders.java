package fr.pederobien.minecraft.border.commands.configurations;

import java.util.List;
import java.util.StringJoiner;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.interfaces.IGameBorderConfiguration;
import fr.pederobien.minecraftgameplateform.dictionary.ECommonMessageCode;
import fr.pederobien.minecraftmanagers.WorldManager;

public class ListBorders<T extends IGameBorderConfiguration> extends AbstractGameBorderConfigurationEdition<T> {

	protected ListBorders() {
		super(EBordersLabel.LIST, EBordersMessageCode.LIST_BORDERS__EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0) {
			StringJoiner joiner = new StringJoiner("\n");
			joiner.add(concat(WorldManager.OVERWORLD));
			joiner.add(concat(WorldManager.NETHER_WORLD));
			joiner.add(concat(WorldManager.END_WORLD));
			sendSynchro(sender, EBordersMessageCode.LIST_BORDERS__ALL_REGISTERED_BORDERS, joiner);
			return true;
		}

		String name = args[0];
		World world = WorldManager.getWorld(name);
		if (world == null) {
			sendSynchro(sender, ECommonMessageCode.COMMON_WORLD_DOES_NOT_EXIST, name);
			return false;
		}
		sendSynchro(sender, EBordersMessageCode.LIST_BORDERS__REGISTERED_BORDER_FOR_WORLD, concat(world));
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		switch (args.length) {
		case 1:
			return filter(WorldManager.getWorldNormalisedNames().stream(), args);
		default:
			return emptyList();
		}
	}
}
