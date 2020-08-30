package fr.pederobien.minecraftborder.commands;

import org.bukkit.command.CommandSender;

import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.commands.common.CommonList;

public class ListBorder extends CommonList<IBorderConfiguration> {

	protected ListBorder() {
		super(EBorderMessageCode.LIST_BORDER__EXPLANATION);
	}

	@Override
	protected void onNoElement(CommandSender sender) {
		sendMessageToSender(sender, EBorderMessageCode.LIST_BORDER__NO_ELEMENT);
	}

	@Override
	protected void onOneElement(CommandSender sender, String name) {
		sendMessageToSender(sender, EBorderMessageCode.LIST_BORDER__ONE_ELEMENT, name);
	}

	@Override
	protected void onSeveralElement(CommandSender sender, String names) {
		sendMessageToSender(sender, EBorderMessageCode.LIST_BORDER__SEVERAL_ELEMENTS, names);
	}
}
