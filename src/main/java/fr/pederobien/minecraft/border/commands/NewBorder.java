package fr.pederobien.minecraft.border.commands;

import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.impl.BorderConfiguration;
import fr.pederobien.minecraft.border.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.commands.common.CommonNew;
import fr.pederobien.minecraftgameplateform.commands.common.ECommonLabel;
import fr.pederobien.minecraftgameplateform.interfaces.element.ILabel;

public class NewBorder extends CommonNew<IBorderConfiguration> {

	protected NewBorder() {
		super(EBorderMessageCode.NEW_BORDER__EXPLANATION);
	}

	@Override
	protected void onNameAlreadyTaken(CommandSender sender, String name) {
		sendSynchro(sender, EBorderMessageCode.NEW_BORDER__NAME_ALREADY_TAKEN, name);
	}

	@Override
	protected void onNameIsMissing(CommandSender sender) {
		sendSynchro(sender, EBorderMessageCode.NEW_BORDER__NAME_IS_MISSING);
	}

	@Override
	protected IBorderConfiguration create(String name) {
		return new BorderConfiguration(name);
	}

	@Override
	protected void onCreated(CommandSender sender, String name) {
		sendSynchro(sender, EBorderMessageCode.NEW_BORDER__BORDER_CREATED, name);
		setAllAvailable();
	}

	private void setAllAvailable() {
		for (ILabel label : ECommonLabel.values())
			setAvailableLabelEdition(label);
		for (ILabel label : EBorderLabel.values())
			setAvailableLabelEdition(label);
	}
}
