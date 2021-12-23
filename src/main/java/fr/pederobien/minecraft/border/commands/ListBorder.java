package fr.pederobien.minecraft.border.commands;

import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.commands.common.CommonList;

public class ListBorder extends CommonList<IBorderConfiguration> {

	protected ListBorder() {
		super(EBorderMessageCode.LIST_BORDER__EXPLANATION);
	}

	@Override
	protected void onNoElement(CommandSender sender) {
		sendSynchro(sender, EBorderMessageCode.LIST_BORDER__NO_ELEMENT);
	}

	@Override
	protected void onOneElement(CommandSender sender, String name) {
		sendSynchro(sender, EBorderMessageCode.LIST_BORDER__ONE_ELEMENT, name);
	}

	@Override
	protected void onSeveralElement(CommandSender sender, String names) {
		sendSynchro(sender, EBorderMessageCode.LIST_BORDER__SEVERAL_ELEMENTS, names);
	}
}
