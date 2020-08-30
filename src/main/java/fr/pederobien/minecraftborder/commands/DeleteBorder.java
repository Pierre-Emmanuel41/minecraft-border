package fr.pederobien.minecraftborder.commands;

import org.bukkit.command.CommandSender;

import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.commands.common.CommonDelete;

public class DeleteBorder extends CommonDelete<IBorderConfiguration> {

	protected DeleteBorder() {
		super(EBorderMessageCode.DELETE_BORDER__EXPLANATION);
	}

	@Override
	protected void onDidNotDelete(CommandSender sender, String name) {
		sendMessageToSender(sender, EBorderMessageCode.DELETE_BORDER__DID_NOT_DELETED, name);
	}

	@Override
	protected void onDeleted(CommandSender sender, String name) {
		sendMessageToSender(sender, EBorderMessageCode.DELETE_BORDER__BORDER_DELETED, name);
	}

	@Override
	protected void onNameIsMissing(CommandSender sender) {
		sendMessageToSender(sender, EBorderMessageCode.DELETE_BORDER__NAME_IS_MISSING);
	}
}
