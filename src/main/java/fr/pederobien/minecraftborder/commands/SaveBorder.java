package fr.pederobien.minecraftborder.commands;

import org.bukkit.command.CommandSender;

import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.commands.common.CommonSave;

public class SaveBorder extends CommonSave<IBorderConfiguration> {

	protected SaveBorder() {
		super(EBorderMessageCode.SAVE_BORDER__EXPLANATION);
	}

	@Override
	protected void onSave(CommandSender sender, String name) {
		sendSynchro(sender, EBorderMessageCode.SAVE_BORDER__BORDER_SAVED, name);
	}
}
