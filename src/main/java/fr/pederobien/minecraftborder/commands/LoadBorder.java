package fr.pederobien.minecraftborder.commands;

import org.bukkit.command.CommandSender;

import fr.pederobien.minecraftborder.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.commands.common.CommonLoad;
import fr.pederobien.minecraftgameplateform.commands.common.ECommonLabel;
import fr.pederobien.minecraftgameplateform.interfaces.element.ILabel;

public class LoadBorder extends CommonLoad<IBorderConfiguration> {

	protected LoadBorder() {
		super(EBorderMessageCode.LOAD_BORDER__EXPLANATION);
	}

	@Override
	protected void onStyleLoaded(CommandSender sender, String name) {
		sendMessageToSender(sender, EBorderMessageCode.LOAD_BORDER__BORDER_LOADED, name);
		setAllAvailable();
	}

	@Override
	protected void onNameIsMissing(CommandSender sender) {
		sendMessageToSender(sender, EBorderMessageCode.LOAD_BORDER__NAME_IS_MISSING);
	}

	private void setAllAvailable() {
		for (ILabel label : ECommonLabel.values())
			setAvailableLabelEdition(label);
		for (ILabel label : EBorderLabel.values())
			setAvailableLabelEdition(label);
	}
}
